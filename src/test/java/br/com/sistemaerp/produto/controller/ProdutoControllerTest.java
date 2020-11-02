package br.com.sistemaerp.produto.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;

import br.com.sistemaerp.controller.PedidoController;
import br.com.sistemaerp.controller.ProdutoController;
import br.com.sistemaerp.model.Produto;
import br.com.sistemaerp.service.PedidoService;
import br.com.sistemaerp.service.ProdutoService;
import io.restassured.http.ContentType;

@WebMvcTest
public class ProdutoControllerTest {
	
	@Autowired
	private ProdutoController produtoController;
	@Autowired
	private PedidoController pedidoController;
	
	@MockBean
	private ProdutoService produtoService;
	@MockBean
	private PedidoService pedidoService;
	
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.produtoController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarProduto() {
		List<Produto> listaProduto = new ArrayList<>();
		Page<Produto> pagedResponse = new PageImpl<>(listaProduto);
		when(this.produtoService.findPage(1, 24, "descricao", "asc", "", false)).thenReturn(pagedResponse);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/produto/page")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarFalha_QuandoBuscarProduto() {
		List<Produto> listaProduto = new ArrayList<>();
		Page<Produto> pagedResponse = new PageImpl<>(listaProduto);
		when(this.produtoService.findPage(1, 24, "descricao", "asc", "", false)).thenReturn(pagedResponse);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/produto/page")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
}
