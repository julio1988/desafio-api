package com.uds.desafio.api.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.uds.desafio.api.model.Produto;
import com.uds.desafio.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto atualizar(UUID id, Produto produto) {
		Produto produtoSalvo = buscarProdutoPorId(id);
		BeanUtils.copyProperties(produto, produtoSalvo, "id");
		return produtoRepository.save(produtoSalvo);
	}

	public Produto buscarProdutoPorId(UUID id) {
		Produto produtoSalvo = produtoRepository.findOne(id);
		if (produtoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return produtoSalvo;
	}

}
