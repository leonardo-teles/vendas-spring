CREATE TABLE clientes(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100),
	cpf VARCHAR(11)
);

CREATE TABLE produtos(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(100),
	preco_unitario NUMERIC(20,2)
);

CREATE TABLE pedidos(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	data_pedido TIMESTAMP,
	total NUMERIC(20,2),
	status VARCHAR(20),
	cliente_id INTEGER REFERENCES clientes(id)
);

CREATE TABLE itens_pedido(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	quantidade INTEGER,
	pedido_id INTEGER REFERENCES pedidos(id),
	produto_id INTEGER REFERENCES produtos(id)
);