package com.uds.desafio.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	@JsonIgnore
	private Pedido pedido;

	@NotNull
	@JoinColumn(name = "produto_id")
	@ManyToOne
	private Produto produto;

	@NotNull
	@DecimalMin("1")
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal quantidade;

	@DecimalMin("1")
	@Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
	private BigDecimal precoUnitario;

	@NotNull
	@DecimalMin("0")
	@Column(name = "perc_desconto", precision = 10, scale = 2, nullable = false)
	private BigDecimal percentualDesconto;

	@NotNull
	@DecimalMin("1")
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal total;

	public ItemPedido() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		if (produto != null) {
			setPrecoUnitario(produto.getPreco());
		}
		this.produto = produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void atualizarValores() {
		if (quantidade != null && precoUnitario != null && percentualDesconto != null) {
			BigDecimal total = quantidade.multiply(precoUnitario);
			if(percentualDesconto.compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal fator = percentualDesconto.divide(new BigDecimal(100));  
			    BigDecimal desconto = total.multiply(fator); 
			    total = total.subtract(desconto);
			}
			setTotal(total);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
