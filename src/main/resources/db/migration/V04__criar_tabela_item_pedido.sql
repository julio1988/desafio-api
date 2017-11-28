CREATE TABLE item_pedido (
	id UUID PRIMARY KEY,
	pedido_id UUID NOT NULL,
    produto_id UUID NOT NULL,
	quantidade NUMERIC(10,2) NOT NULL,
	preco_unitario NUMERIC(10,2) NOT NULL,
	perc_desconto NUMERIC(10,2) NOT NULL,
	total NUMERIC(10,2) NOT NULL,
	FOREIGN KEY (pedido_id) REFERENCES pedido(id),
	FOREIGN KEY (produto_id) REFERENCES produto(id)
);
