# Sistema ERP - Backend

API REST para um Sistema de Gestão Empresarial (ERP), desenvolvida com Java e Spring Boot. O objetivo deste projeto é fornecer uma solução completa para gerenciamento de processos empresariais, incluindo controle de usuários, clientes, fornecedores, produtos, estoque, vendas, compras e financeiro.

## Objetivo

Desenvolver um backend robusto, seguro e escalável, seguindo boas práticas de arquitetura de software, para servir como base de um sistema ERP moderno.

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Security
- JWT
- Spring Data JPA
- PostgreSQL
- Flyway
- Maven
- Docker
- Swagger / OpenAPI
- JUnit 5
- Mockito

## Arquitetura

O projeto seguirá uma arquitetura em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Banco de Dados
```

Organização prevista:

```text
src
└── main
    ├── java
    │   └── br.com.erp
    │       ├── config
    │       ├── controller
    │       ├── dto
    │       ├── entity
    │       ├── exception
    │       ├── mapper
    │       ├── repository
    │       ├── security
    │       ├── service
    │       ├── util
    │       └── validation
    └── resources
        ├── db
        └── application.yml
```

## Funcionalidades

- Autenticação com JWT
- Controle de acesso por perfis
- Cadastro de usuários
- Cadastro de clientes
- Cadastro de fornecedores
- Cadastro de categorias
- Cadastro de produtos
- Controle de estoque
- Controle de compras
- Controle de vendas
- Controle financeiro
- Dashboard gerencial
- Relatórios
- Auditoria de operações

## Módulos

- Autenticação
- Usuários
- Clientes
- Fornecedores
- Categorias
- Produtos
- Estoque
- Compras
- Vendas
- Financeiro
- Dashboard
- Relatórios

## Roadmap

- [ ] Configuração inicial do projeto
- [ ] Configuração do banco de dados
- [ ] Autenticação JWT
- [ ] Controle de usuários
- [ ] Clientes
- [ ] Fornecedores
- [ ] Categorias
- [ ] Produtos
- [ ] Estoque
- [ ] Compras
- [ ] Vendas
- [ ] Financeiro
- [ ] Dashboard
- [ ] Relatórios
- [ ] Testes automatizados
- [ ] Docker
- [ ] Deploy

## Documentação

A documentação da API será disponibilizada via Swagger.

```text
http://localhost:8080/swagger-ui/index.html
```

## Contribuição

Este projeto foi desenvolvido para fins de estudo e construção de portfólio. Sugestões e melhorias são bem-vindas.

## Licença

Este projeto está licenciado sob a licença MIT.
