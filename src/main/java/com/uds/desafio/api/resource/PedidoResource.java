package com.uds.desafio.api.resource;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uds.desafio.api.event.RecursoCriadoEvent;
import com.uds.desafio.api.model.Pedido;
import com.uds.desafio.api.repository.PedidoRepository;
import com.uds.desafio.api.repository.filter.PedidoFilter;
import com.uds.desafio.api.service.PedidoService;


@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Pedido> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
		return pedidoRepository.filtrar(pedidoFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido, HttpServletResponse response) {
		Pedido pedidoSalvo = pedidoService.lancarRegistroDePedido(pedido);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pedidoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable UUID id) {
		pedidoRepository.delete(id);
	}
	
}
