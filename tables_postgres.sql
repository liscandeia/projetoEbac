
create table tb_cliente(
	id bigint,
	nome varchar(50),
	codigo varchar(50),
	constraint pk_id_cliente primary key(id) 
);
create sequence sq_cliente
start 1
increment 1
owned by tb_cliente.id;

create table tb_produto(
	id bigint,
	nome varchar(50),
	codigo varchar(50),
	valor double precision,
	constraint pk_id_produto primary key(id) 
);

create sequence sq_produto
start 1
increment 1
owned by tb_produto.id;

