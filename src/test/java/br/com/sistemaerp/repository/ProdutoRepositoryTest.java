package br.com.sistemaerp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.sistemaerp.model.Produto;
import br.com.sistemaerp.repositorio.ProdutoRepositorio;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepositorio repositorio;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void whenCreate_thenPersistData() {
		Produto produto = new Produto();
		produto.setDescricao("Produto de Teste 1");
		produto.setValorAtacado(BigDecimal.valueOf(10));
		produto.setValorVarejo(BigDecimal.valueOf(10));
		this.repositorio.save(produto);
		assertThat(produto.getId()).isNotNull();
		assertThat(produto.getDescricao()).isEqualTo("Produto de Teste 1");
	}
	
	@Test
	public void whenDelete_thenRemoveData() {
		Produto produto = new Produto();
		produto.setDescricao("Produto de Teste 1");
		produto.setValorAtacado(BigDecimal.valueOf(10));
		produto.setValorVarejo(BigDecimal.valueOf(10));
		this.repositorio.save(produto);
		this.repositorio.delete(produto);
		assertThat(repositorio.findById(produto.getId())).isEmpty();
	}

}
