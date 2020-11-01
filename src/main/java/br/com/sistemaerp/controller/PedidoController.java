package br.com.sistemaerp.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemaerp.model.Pedido;
import br.com.sistemaerp.service.PedidoService;


/**
 * 
 * @author JOAO HENRIQUE FREITAS
 *
 */

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@GetMapping("/page")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "dataCadastro") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "pesquisa", defaultValue = "") String pesquisa) 
	{
		return ResponseEntity.ok().body(service.findPage(page, linesPerPage, orderBy, direction, pesquisa));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void save(@RequestBody Pedido pedido) {
		
		this.service.salvar(pedido);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable("id") UUID id, @RequestBody Pedido pedido) {
		pedido.setId(id);
		this.service.salvar(pedido);
	}
	@PutMapping("/fecharVenda/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void finalizar(@PathVariable("id") UUID id, @RequestBody Pedido pedido) {
		pedido.setId(id);
		this.service.salvar(pedido);
	}

}
