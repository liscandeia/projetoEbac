# Sistema de Gerenciamento de Vendas Online

Este é um projeto Java que implementa um sistema de gerenciamento de vendas online, com foco na persistência de dados utilizando o PostgreSQL como banco de dados. O sistema inclui funcionalidades para cadastro, busca, alteração e exclusão de clientes e produtos.

## Requisitos

- JDK 8 ou superior
- PostgreSQL
- Driver JDBC PostgreSQL

## Configuração do Banco de Dados

1. Instale o PostgreSQL em sua máquina, se ainda não estiver instalado.
2. Crie um banco de dados chamado `vendas_online`.
3. Crie as tabelas necessárias usando o script SQL fornecido na pasta `database-scripts`.

## Configuração do Projeto

1. Clone ou faça o download do projeto para o seu ambiente de desenvolvimento.
2. Importe o projeto em sua IDE Java preferida.
3. Certifique-se de que o driver JDBC do PostgreSQL esteja incluído no classpath do projeto.

## Utilização do Projeto

### Classes DAO

O projeto contém as seguintes classes DAO:

- `ClienteDao`: Responsável por manipular os dados dos clientes no banco de dados.
- `ProdutoDao`: Responsável por manipular os dados dos produtos no banco de dados.

### Testes Unitários

O projeto inclui testes unitários para as classes DAO:

- `ClienteTest`: Testa as operações CRUD para a entidade Cliente.
- `ProdutoTest`: Testa as operações CRUD para a entidade Produto.
- `AllTests`: Suite de testes que executa todos os testes unitários.

### Execução dos Testes

Para executar os testes unitários, basta executar a classe `AllTests` como um JUnit Test.

### Configuração da Conexão com o Banco de Dados

A classe `ConnectionFactory` é responsável por configurar a conexão com o banco de dados PostgreSQL. Certifique-se de que as informações de URL, usuário e senha estejam corretas para o seu ambiente.

### Encerramento de Conexões

As conexões com o banco de dados são gerenciadas de forma adequada utilizando o padrão de design Factory e fechadas de forma apropriada após o uso, para evitar vazamentos de recursos.

## Contribuições

Contribuições são bem-vindas! Se você identificar bugs, melhorias ou novos recursos que podem ser implementados, sinta-se à vontade para abrir uma issue ou enviar um pull request.

