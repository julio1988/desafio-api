package com.uds.desafio.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uds.desafio.api.model.ItemPedido;
import com.uds.desafio.api.model.Pedido;
import com.uds.desafio.api.repository.PedidoRepository;
import com.uds.desafio.api.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Pedido lancarRegistroDePedido(Pedido pedido) {
		Long novoNumero = pedidoRepository.gerarNumeroSequencial(pedido);
		pedido.setNumero(novoNumero.intValue());
		if(!pedido.getItensPedido().isEmpty()) {
			for(ItemPedido item : pedido.getItensPedido()) {
				item.setProduto(produtoRepository.findOne(item.getProduto().getId()));
				item.setPedido(pedido);
				item.atualizarValores();
				pedido.setTotal(pedido.getTotal().add(item.getTotal()));
			}
		}
		return pedidoRepository.save(pedido);
	}

}
