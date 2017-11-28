package com.uds.desafio.api.repository.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uds.desafio.api.model.Pedido;
import com.uds.desafio.api.repository.filter.PedidoFilter;

public interface PedidoRepositoryQuery {

	Page<Pedido> filtrar(PedidoFilter pedidoFilter, Pageable pageable);
	Long gerarNumeroSequencial(Pedido pedido);	
	
}
