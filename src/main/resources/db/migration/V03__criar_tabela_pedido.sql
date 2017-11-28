CREATE TABLE pedido (
	id UUID PRIMARY KEY,
	numero SERIAL NOT NULL,
	emissao DATE NOT NULL,
	cliente_id UUID NOT NULL,
	total NUMERIC(10,2) NOT NULL,
	FOREIGN KEY (cliente_id) REFERENCES pessoa(id)
);