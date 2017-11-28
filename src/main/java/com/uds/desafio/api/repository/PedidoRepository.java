package com.uds.desafio.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uds.desafio.api.model.Pedido;
import com.uds.desafio.api.repository.pedido.PedidoRepositoryQuery;

public interface PedidoRepository extends JpaRepository<Pedido, UUID>, PedidoRepositoryQuery {

}
