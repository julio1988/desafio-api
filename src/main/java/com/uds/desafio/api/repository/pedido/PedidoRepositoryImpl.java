package com.uds.desafio.api.repository.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.uds.desafio.api.model.Pedido;
import com.uds.desafio.api.repository.filter.PedidoFilter;

public class PedidoRepositoryImpl implements PedidoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Pedido> filtrar(PedidoFilter pedidoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);

		Predicate[] predicates = criarRestricoes(pedidoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Pedido> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(pedidoFilter));
	}

	private Predicate[] criarRestricoes(PedidoFilter pedidoFilter, CriteriaBuilder builder, Root<Pedido> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (pedidoFilter.getNumero() != null) {
			predicates.add(builder.equal(root.get("numero"), pedidoFilter.getNumero()));
		}

		if (pedidoFilter.getEmissao() != null) {
			predicates.add(builder.equal(root.get("emissao"), pedidoFilter.getEmissao()));
		}

		if (!StringUtils.isEmpty(pedidoFilter.getCliente())) {
			predicates.add(builder.like(root.join("cliente").get("nome"), "%" + pedidoFilter.getCliente() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Pedido> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(PedidoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pedido> root = criteria.from(Pedido.class);

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	@Override
	public Long gerarNumeroSequencial(Pedido pedido) {
		TypedQuery<Long> query = manager.createQuery("Select count(numero) + 1 from Pedido", Long.class);
		Long novoNumero = query.getSingleResult();
		novoNumero = novoNumero != null ? novoNumero : Long.valueOf(1);
		return novoNumero;
	}
}
