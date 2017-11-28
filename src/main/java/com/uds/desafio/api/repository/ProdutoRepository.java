package com.uds.desafio.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uds.desafio.api.model.Produto;
import com.uds.desafio.api.repository.produto.ProdutoRepositoryQuery;

public interface ProdutoRepository extends JpaRepository<Produto, UUID>, ProdutoRepositoryQuery {

}
