create schema pizzaria
 
create table pizzaria.Categoria (
    idCategoria int primary key identity(1,1),
    tipoCategoria varchar(20) not null
);

-- Criar a tabela Produtos
create table pizzaria.Produtos 
(
    idProduto int primary key identity(1,1),
    nome varchar(60) not null,
    preco money not null,
    Descricao varchar(250) not null,
    idCategoria int not null,
    foreign key (idCategoria) references pizzaria.Categoria(idCategoria)
);

-- Criar a tabela Vendas
create table pizzaria.Vendas 
(
    idPedido int primary key identity(1,1),
    idProduto int not null,
    qtd int not null,
    precoTotal money not null,
    foreign key (idProduto) references pizzaria.Produtos(idProduto)
);

 select * from pizzaria.produtos
 
 
insert into pizzaria.Categoria (idCategoria, tipoCategoria) values ('1', 'Pizzas');
insert into pizzaria.Categoria (idCategoria, tipoCategoria) values ('2', 'Bebidas');
insert into pizzaria.Categoria (idCategoria, tipoCategoria) values ('3', 'Aperitivos');
insert into pizzaria.Categoria (idCategoria, tipoCategoria) values ('4', 'Sobremesas');
 
 
 drop table pizzaria.Vendas
 drop table pizzaria.Categoria
 drop table pizzaria.Produtos
