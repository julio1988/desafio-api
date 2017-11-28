CREATE TABLE produto (
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	codigo SERIAL NOT NULL,
	nome VARCHAR(255) NOT NULL,
	preco NUMERIC(10,2) NOT NULL,
	CONSTRAINT UK_PRODUTO_CODIGO UNIQUE (codigo),
	CONSTRAINT UK_PRODUTO_NOME UNIQUE (nome)
);

INSERT INTO produto (codigo, nome, preco) values (1, 'Impressora HP Laser', 695.63 );
INSERT INTO produto (codigo, nome, preco) values (2, 'Câmera Digital Sony DSC-H400 20.1 MP', 1254.93);
INSERT INTO produto (codigo, nome, preco) values (3, 'Console PS4 500GB Hits Bundle', 1988.23);
INSERT INTO produto (codigo, nome, preco) values (4, 'Console Xbox One 500gb', 1500.00);
INSERT INTO produto (codigo, nome, preco) values (5, 'Smartphone LG Q6 Plus Dual Chip Android 7.0 Tela 5.5 Full Hd', 1452.30);
INSERT INTO produto (codigo, nome, preco) values (6, 'Mouse Gamer G300s 2.500 DPI PC - Logitech', 56.50);
INSERT INTO produto (codigo, nome, preco) values (7, 'Kit Gamer C/ Teclado + Mouse + Headset Spider Fortrek', 152.20);
INSERT INTO produto (codigo, nome, preco) values (8, 'Headset Gamer Man O war Overwatch Tournament Com Microfone - Razer', 120.00);
INSERT INTO produto (codigo, nome, preco) values (9, 'Monitor SE2216H LCD LED Full HD 21,5 - Dell', 754.00);
INSERT INTO produto (codigo, nome, preco) values (10,'HD Externo Portátil WD Elements 1TB USB 3.0', 240.00);