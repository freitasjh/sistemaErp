package br.com.sistemaerp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.sistemaerp.model.ItemPedido;
import br.com.sistemaerp.model.Pedido;
import br.com.sistemaerp.model.Produto;
import br.com.sistemaerp.repositorio.PedidoRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PedidoRepositoryTest {
	
	@Autowired
	private PedidoRepository repositorio;
	
	
	@Test
	public void whenCreate_thenPersistData() {
		Pedido pedido = new Pedido();
		pedido.setDataCadastro(new Date());
		pedido.setValorTotal(BigDecimal.valueOf(100.00));
		pedido.setSubTotal(BigDecimal.valueOf(100.00));
		pedido.setQuantidadeTotal(BigDecimal.valueOf(2));
		
		ItemPedido item = new ItemPedido();
		item.setProduto(new Produto(null, "Produto 1"));
		item.setQuantidade(BigDecimal.valueOf(1));
		item.setValorUnitario(BigDecimal.valueOf(100.00));
		item.setValorTotal(BigDecimal.valueOf(100.00));
		item.setPedido(pedido);
		ItemPedido item2 = new ItemPedido();
		item2.setProduto(new Produto(null, "Produto 2"));
		item2.setQuantidade(BigDecimal.valueOf(2));
		item2.setValorUnitario(BigDecimal.valueOf(100.00));
		item2.setValorTotal(BigDecimal.valueOf(100.00));
		item2.setPedido(pedido);
		pedido.getListaDeItem().add(item);
		pedido.getListaDeItem().add(item2);
		
		this.repositorio.save(pedido);
		assertThat(pedido.getId()).isNotNull();
		assertThat(pedido.getValorTotal()).isEqualTo(BigDecimal.valueOf(100.00));
	}
	
	@Test
	public void whenDelete_thenRemoveData() {
		Pedido pedido = new Pedido();
		pedido.setDataCadastro(new Date());
		pedido.setValorTotal(BigDecimal.valueOf(100.00));
		pedido.setSubTotal(BigDecimal.valueOf(100.00));
		pedido.setQuantidadeTotal(BigDecimal.valueOf(2));
		this.repositorio.save(pedido);this.repositorio.delete(pedido);
		assertThat(repositorio.findById(pedido.getId())).isEmpty();
	}
}
