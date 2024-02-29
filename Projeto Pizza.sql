create schema pizzaria
 
 create table pizzaria.Produtos
 (
	idProduto int primary key identity(1,1),
	nome varchar(60) not null,
	peco money not null,
	Descricao varchar(250) not null,
	idCategoria int not null,
	foreign key(idCategoria) references pizzaria.Categoria(idCategoria),
 );


  create table pizzaria.Categoria
 (
	idCategoria int primary key identity(1,1),
	tipoCategoria varchar(20) not null
 );

   create table pizzaria.Vendas
 (
	idPedido int primary key identity(1,1),
	idProduto int not null,	
	qtd int not null,
	precoTotal money not null

	foreign key(idProduto) references pizzaria.Produtos(idProduto)
 );