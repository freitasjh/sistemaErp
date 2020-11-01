package br.com.sistemaerp.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import br.com.sistemaerp.enums.SituacaoVenda;
import br.com.sistemaerp.enums.TipoProduto;
import br.com.sistemaerp.model.Pedido;
import br.com.sistemaerp.model.QPedido;
import br.com.sistemaerp.repositorio.PedidoRepository;
import br.com.sistemaerp.service.exceptions.RegrasException;

@Service
public class PedidoService{
	
	@Autowired
	private PedidoRepository repository;
	
	
	public void salvar(Pedido pedido) {
		pedido.setCodigo(( pedido.getCodigo() == null || pedido.getCodigo().isEmpty() ? this.nextCod() : pedido.getCodigo()));
		pedido.setDataCadastro(pedido.getDataCadastro() == null ? new Date() : pedido.getDataCadastro());
		
		pedido.getListaDeItem().stream().forEach(obj -> obj.setPedido(pedido));
		
		if(pedido.getSituacaoVenda() == SituacaoVenda.FINALIZADO) {
			throw new RegrasException("Pedido não pode ser alterado pois esta finalizado...");
		}
		
		if(pedido.getDescontoValor().doubleValue() > 0.00 && pedido.getValorTotal() == pedido.getSubTotal()) {
			arrumaValorComDesconto(pedido);
		}
		
		this.repository.save(pedido);
	}
	
	public void finalizarVenda(Pedido pedido) {
		if(pedido.getSituacaoVenda() == SituacaoVenda.FINALIZADO && pedido.getDataFinalizado() != null) {
			throw new RegrasException("Pedido não pode ser alterado pois já esta finalizado");
		}
		pedido.setSituacaoVenda(SituacaoVenda.FINALIZADO.getCodigo());
		pedido.setDataFinalizado(new Date());
		
		if(pedido.getDescontoValor().doubleValue() > 0.00 && pedido.getValorTotal() == pedido.getSubTotal()) {
			arrumaValorComDesconto(pedido);
		}
		
		this.repository.save(pedido);
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String pesquisa){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!pesquisa.isEmpty()) {
			builder.and(QPedido.pedido.codigo.like(pesquisa));
		}
		
		return builder.getValue() == null ? repository.findAll(pageRequest) : repository.findAll(builder.getValue(), pageRequest);
	}
	
	public Optional<Pedido> findById(UUID id){
		return repository.findById(id);
	}
	
	private void arrumaValorComDesconto(Pedido pedido) {
		double valorTotalProduto = pedido.getListaDeItem().stream().filter(item -> item.getProduto().getTipoProduto() == TipoProduto.PRODUTO).map(item -> item.getValorTotal()).mapToDouble(BigDecimal::doubleValue).sum();
		double valorTotalServico = pedido.getListaDeItem().stream().filter(item -> item.getProduto().getTipoProduto() == TipoProduto.SERVICO).map(item -> item.getValorTotal()).mapToDouble(BigDecimal::doubleValue).sum();
		pedido.setValorTotal(BigDecimal.valueOf((valorTotalProduto - pedido.getDescontoValor().doubleValue()) + valorTotalServico));
	}
	
	private String nextCod() {
		int ultimo = this.repository.nextCod();
		return String.valueOf(ultimo+1);
	}
}
