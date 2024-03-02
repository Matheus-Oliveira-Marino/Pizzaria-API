## Pizzaria I AM CRUD

## Descrição
- O projeto Pizzaria CRUD é um sistema de gerenciamento de vendas de uma pizzaria, permitindo o cadastro, consulta, atualização e exclusão de produtos e vendas. Ele foi desenvolvido em Java utilizando o paradigma de programação orientada a objetos e uma arquitetura `MVC` (Model-View-Controller).
<br>
## Funcionalidades 

O projeto oferece as seguintes funcionalidades: 

### 1. _Cadastro de Produtos e Categorias_
- `Cadastro de Produtos e Categorias:` Permite o registro de novos produtos, incluindo 'nome', 'preço', 'descrição' e 'categoria'.

- `Classificação de Categorias:` Permite a seleção de categorias pré-definidas para classificar os produtos.

<br>
### 2. _Registro de Vendas_

- `Registro de Vendas:` Permite registrar vendas de produtos, especificando o 'produto' vendido, a 'quantidade' e o 'preço total'.

- `Atualização de Vendas:` Permite a atualização dos detalhes de vendas, como o 'produto' vendido, a 'quantidade' e o 'preço total'.

<br>
### 3. _Visualização e Gerenciamento_

- `Visualização de Produtos:` Permite visualizar todos os produtos cadastrados, incluindo seus detalhes como 'nome', 'preço', 'descrição' e 'categoria'.

- `Visualização de Vendas:` Permite visualizar todas as vendas registradas, mostrando detalhes como 'produto vendido', 'quantidade' e 'preço total'.

### 4. _Atualização e Exclusão_

- `Atualização de Produtos: ` Permite atualizar as informações de produtos existentes, como 'nome', 'preço', 'descrição' e 'categoria'.

- `Exclusão de Produtos: ` Permite excluir produtos cadastrados do sistema.


- `Exclusão de Vendas: ` Permite excluir vendas registradas do sistema.

## Tecnologias Utilizadas

- [ ] `Java`: Linguagem de programação utilizada para o desenvolvimento da lógica do sistema.

- [ ] `Swing`: Framework gráfico utilizado para a criação da interface do usuário (GUI).

- [ ] `JDBC`: API do Java para conexão e execução de consultas em bancos de dados SQL.

- [ ] `SQL Server`: Banco de dados relacional utilizado para armazenar os dados da aplicação.

## Estrutura do Projeto

O projeto está organizado em pacotes que seguem uma estrutura `MVC`, separando as responsabilidades de modelo, visão e controle:

- [ ] `bd`: Contém as classes responsáveis pela conexão com o banco de dados e manipulação dos dados.:

- [ ] `bd.core`: Classes principais para manipulação de banco de dados.

- [ ] `bd.daos:` Classes que representam os DAOs (Data Access Objects), responsáveis por realizar operações no banco de dados.

- [ ] `gui`: Contém as classes que representam a 
interface gráfica do usuário.

- [ ] `telasemframes`: Classes que representam as telas e frames da interface gráfica.

- [ ] `model`: Classes que representam os modelos de dados do sistema.

## Gerenciamento de Dependências

A visualização `JAVA PROJECTS` permite que você gerencie suas dependências. Mais detalhes podem ser encontrados [aqui](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Como Executar

1. Certifique-se de ter o `Java JDK` instalado em seu sistema. Link para [download](https://www.oracle.com/java/technologies/downloads/).

    1.1 Instalação __*Windows 10*__: [Link do tutorial](https://www.youtube.com/watch?v=AUL--F5Wdh8)

    1.2 Instalação __*Windows 11*__: [Link do tutorial](https://www.youtube.com/watch?v=krGadRGdESQ).

    1.3 Instalação __*Linux 22.04 LTS*__: [Link do tutorial](https://www.youtube.com/watch?v=vVrIDJ--GOA).

2. Importe o projeto em sua IDE preferida (por exemplo, Eclipse, IntelliJ IDEA) e instale a biblioteca `sqljdbc4.jar` como dependência.

    2.1 Adicionando arquivo `.jar` no VScode: [link](https://www.youtube.com/watch?v=3Qm54znQX2E&list=PLDyYFpL8k5MyC7auUCOF-S8YGM8tGaoSw&index=1)

    2.2 Adicionando  arquivo `.jar` no IntelliJ: [link]()

3. Configure a conexão com o banco de dados __*SQL Server*__ no arquivo `BDSQLServer.java.`

4. Execute o programa a partir da classe principal `PizzariaTela.java`.

## Licença

Este projeto está licenciado sob a [MIT LICENSE](https://github.com/Matheus-Oliveira-Marino/Pizzaria-API/blob/d15dc3f0452245adbe4d4b22e7ac1c3af5576893/LICENSE). Consulte o arquivo LICENSE.md para obter detalhes.

