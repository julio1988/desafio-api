package com.uds.desafio.api.repository.produto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.uds.desafio.api.model.Produto;
import com.uds.desafio.api.repository.filter.ProdutoFilter;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);

		Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Produto> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(produtoFilter));
	}

	private Predicate[] criarRestricoes(ProdutoFilter produtoFilter, CriteriaBuilder builder, Root<Produto> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (produtoFilter.getCodigo() != null) {
			predicates.add(builder.equal(root.get("codigo"), produtoFilter.getCodigo()));
		}

		if (!StringUtils.isEmpty(produtoFilter.getNome())) {
			predicates.add(
					builder.like(builder.lower(root.get("nome")), "%" + produtoFilter.getNome().toLowerCase() + "%"));
		}
		
		if (produtoFilter.getPreco() != null) {
			predicates.add(builder.equal(root.get("preco"), produtoFilter.getPreco()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Produto> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(ProdutoFilter produtoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Produto> root = criteria.from(Produto.class);

		Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	@Override
	public Produto buscarProdutoPeloCodigo(Long codigo) {
		Produto recuperado = (Produto) manager.createQuery(" From Produto where codigo = :cod")
				.setParameter("cod", codigo).getSingleResult();
		if (recuperado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return recuperado;
	}

}
