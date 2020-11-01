package br.com.sistemaerp.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaerp.model.Produto;
import br.com.sistemaerp.service.ProdutoService;

/**
 * 
 * @author JOAO HENRIQUE FREITAS
 *
 */
@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	
	@GetMapping(value = "/page")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Page<Produto>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="tipoProduto", defaultValue = "") String tipoProduto,
			@RequestParam(value="filtroInativo", defaultValue = "false") boolean filtroInativo)
	{
		return ResponseEntity.ok().body(service.findPage(page, linesPerPage, orderBy, direction, tipoProduto, filtroInativo));
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Produto> findById(@PathVariable("id") UUID id){
		return ResponseEntity.ok().body(this.service.findById(id));
	}
	
	@GetMapping("/item/{codigo}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Produto> findByCodigo(@PathVariable("codigo") String codigo){
		return ResponseEntity.ok().body(this.service.findByCodigo(codigo));
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void salva(@Valid @RequestBody Produto produto){
		this.service.salvar(produto);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") UUID id, @RequestBody Produto produto ) {
		produto.setId(id);
		this.service.salvar(produto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("id") UUID id) {
		this.service.deletar(id);
	}
}
