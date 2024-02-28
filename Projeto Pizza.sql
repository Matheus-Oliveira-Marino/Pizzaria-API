create schema pizzaria
 
 create table pizzaria.Mercadoria
 (
	idMercadoria int primary key identity(1,1),
	Mercadoria varchar(30) not null,
	precoUnitario money not null,
	Descricao varchar(250) not null,
	idCategoria int not null,
	foreign key(idCategoria) references pizzaria.Categoria(idCategoria),
 );


  create table pizzaria.Pedidos
 (
	idPedido int primary key identity(1,1),
	idMercadoria int null,
	PrecoTotal money null,
	idPagamento int  null,
	idCliente int null,
	Quantidade int null,

	foreign key(idPagamento) references pizzaria.Pagamento(idPagamento),
	foreign key(idMercadoria) references pizzaria.Mercadoria(idMercadoria),
	foreign key(idCliente) references pizzaria.Cliente(idCliente)
 );

   create table pizzaria.Pagamento
 (
	idPagamento int primary key identity(1,1),
	Opcao varchar(12) not null unique,	
 );

create table pizzaria.Cliente
(
	idCliente int primary key identity(1,1),
	Telefone varchar(12) not null,
	check(Telefone like '[0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'),
	CEP varchar(9) not null,
	check(CEP like '[0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]'),
	);
	
create table pizzaria.Categoria
(
 idCategoria int not null primary key identity(1,1),
 Categoria varchar(20) not null,
);
 
 select * from pizzaria.Pedidos
 select * from pizzaria.Mercadoria
 select * from pizzaria.Pagamento
 select * from pizzaria.Categoria
 select * from pizzaria.Cliente
  