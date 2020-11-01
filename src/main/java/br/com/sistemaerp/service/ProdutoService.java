package br.com.sistemaerp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;

import br.com.sistemaerp.enums.TipoProduto;
import br.com.sistemaerp.model.Produto;
import br.com.sistemaerp.model.QProduto;
import br.com.sistemaerp.repositorio.ProdutoRepositorio;
import br.com.sistemaerp.service.exceptions.DataIntegrityException;
import br.com.sistemaerp.service.exceptions.ObjectNotFoundException;
import br.com.sistemaerp.service.exceptions.RegrasException;

/**
 * 
 * @author JOAO HENRQUE FREITAS
 *
 */
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepositorio repository;
	
	public void salvar(Produto produto) {
		if(produto.getCodigoInterno() == null || produto.getCodigoInterno().isEmpty()) {
			produto.setCodigoInterno(String.valueOf(this.repository.nextCod()+1));
		}
		this.repository.save(produto);
	}
	
	public void deletar(UUID id) {
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não e possivel excluir um produto que foi informado em uma venda");
		}
		
	}
	
	
	public Produto findByCodigo(String codigo) {
		Produto produtoRetornado = repository.findOne(new BooleanBuilder().and(QProduto.produto.codigoInterno.eq(codigo)))
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado nenhum dado..."));
		
		if(!produtoRetornado.isAtivado()) {
			throw new RegrasException("Produto não pode ser retornado pois esta desativado...");
		}
		
		return produtoRetornado;
	}
	
	public Produto findById(UUID id) {
		return repository.findOne(new BooleanBuilder().and(QProduto.produto.id.eq(id)))
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado nenhum dado..."));
	}
	
	
	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String tipoProduto, boolean filtroInativo){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(QProduto.produto.ativado.eq(!filtroInativo));
		
		if(!tipoProduto.isEmpty()) {
			builder.and(QProduto.produto.tipoProduto.eq(TipoProduto.valueOfCodigo(tipoProduto)));
		}
		
		return builder.getValue() == null ? repository.findAll(pageRequest) : repository.findAll(builder.getValue(), pageRequest);
	}
	
	//TESTE DE QUERYDSL
	public List<Produto> findByTipo(String tipo) {
		return Lists.newArrayList(repository.findAll(QProduto.produto.tipoProduto.eq(TipoProduto.SERVICO)));
	}
}
