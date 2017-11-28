package com.uds.desafio.api.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uds.desafio.api.model.Produto;
import com.uds.desafio.api.repository.filter.ProdutoFilter;


public interface ProdutoRepositoryQuery {

	Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable);
	Produto buscarProdutoPeloCodigo(Long codigo);
}
