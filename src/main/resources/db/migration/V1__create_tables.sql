-- Criação da tabela de perfis de acesso
CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);


-- Criação da tabela de usuários
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    role_id BIGINT NOT NULL,

    CONSTRAINT fk_usuario_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
);


-- Criação da tabela de clientes
CREATE TABLE clientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf_cnpj VARCHAR(20) UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(150),
    endereco VARCHAR(255),
    cidade VARCHAR(100),
    estado VARCHAR(2)
);


-- Criação da tabela de fornecedores
CREATE TABLE fornecedores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(150),
    contato VARCHAR(100),
    endereco VARCHAR(255)
);


-- Criação da tabela de categorias
CREATE TABLE categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255)
);


-- Criação da tabela de produtos
CREATE TABLE produtos (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(255),
    preco NUMERIC(10,2) NOT NULL,
    custo NUMERIC(10,2),
    quantidade INTEGER NOT NULL DEFAULT 0,
    categoria_id BIGINT NOT NULL,
    fornecedor_id BIGINT,

    CONSTRAINT fk_produto_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES categorias(id),

    CONSTRAINT fk_produto_fornecedor
        FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedores(id)
);


-- Criação da tabela de movimentação de estoque
CREATE TABLE movimentacoes_estoque (
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL,
    quantidade INTEGER NOT NULL,
    data_movimentacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    produto_id BIGINT NOT NULL,

    CONSTRAINT fk_movimentacao_produto
        FOREIGN KEY (produto_id)
        REFERENCES produtos(id)
);


-- Criação da tabela de vendas
CREATE TABLE vendas (
    id BIGSERIAL PRIMARY KEY,
    data_venda TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    valor_total NUMERIC(10,2) NOT NULL,
    cliente_id BIGINT NOT NULL,

    CONSTRAINT fk_venda_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id)
);


-- Itens da venda
CREATE TABLE itens_venda (
    id BIGSERIAL PRIMARY KEY,
    quantidade INTEGER NOT NULL,
    valor_unitario NUMERIC(10,2) NOT NULL,
    venda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,

    CONSTRAINT fk_item_venda
        FOREIGN KEY (venda_id)
        REFERENCES vendas(id),

    CONSTRAINT fk_item_produto
        FOREIGN KEY (produto_id)
        REFERENCES produtos(id)
);


-- Criação da tabela de compras
CREATE TABLE compras (
    id BIGSERIAL PRIMARY KEY,
    data_compra TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    valor_total NUMERIC(10,2) NOT NULL,
    fornecedor_id BIGINT NOT NULL,

    CONSTRAINT fk_compra_fornecedor
        FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedores(id)
);


-- Itens da compra
CREATE TABLE itens_compra (
    id BIGSERIAL PRIMARY KEY,
    quantidade INTEGER NOT NULL,
    valor_unitario NUMERIC(10,2) NOT NULL,
    compra_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,

    CONSTRAINT fk_item_compra
        FOREIGN KEY (compra_id)
        REFERENCES compras(id),

    CONSTRAINT fk_item_compra_produto
        FOREIGN KEY (produto_id)
        REFERENCES produtos(id)
);


-- Inserção dos perfis padrão
INSERT INTO roles (nome)
VALUES 
('ADMIN'),
('GERENTE'),
('VENDEDOR'),
('ESTOQUISTA');
