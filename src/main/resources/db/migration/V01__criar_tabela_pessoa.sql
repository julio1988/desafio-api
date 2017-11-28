CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE pessoa (
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	nome VARCHAR(50) NOT NULL,
	data_nascimento DATE NOT NULL,
	CONSTRAINT UK_PESSOA_NOME UNIQUE (nome)  
);

INSERT INTO pessoa (nome, data_nascimento) values ('João Silva', '1988-06-10');
INSERT INTO pessoa (nome, data_nascimento) values ('Maria Rita', '1989-04-17');
INSERT INTO pessoa (nome, data_nascimento) values ('Pedro Santos', '1974-07-18');
INSERT INTO pessoa (nome, data_nascimento) values ('Ricardo Pereira', '1988-12-08');
INSERT INTO pessoa (nome, data_nascimento) values ('Josué Mariano', '1999-03-01');
INSERT INTO pessoa (nome, data_nascimento) values ('Pedro Barbosa', '1941-06-18');
INSERT INTO pessoa (nome, data_nascimento) values ('Henrique Medeiros', '1963-11-04');
INSERT INTO pessoa (nome, data_nascimento) values ('Carlos Santana', '1955-08-06');
INSERT INTO pessoa (nome, data_nascimento) values ('Leonardo Oliveira', '1953-09-20');
INSERT INTO pessoa (nome, data_nascimento) values ('Isabela Martins', '1974-03-25');
