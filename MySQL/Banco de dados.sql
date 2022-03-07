create database db_magalu;

use db_magalu;

create table tb_product(
	id bigint auto_increment primary key,
    nome varchar(20) not null,
    price decimal(8,2) not null
);

insert into tb_product (nome, price) values ("Kit Cerveja Stella", 37.90);
insert into tb_product (nome, price) values ("Sofá Retrátil", 949.99);
insert into tb_product(nome, price) values ("Fogão Cooktop", 275.99);

create table tb_category(
	id bigint auto_increment primary key,
    nome varchar(20) not null
);

insert into tb_category (nome) values ("Eletrodomésticos");
insert into tb_category (nome) values ("Bebidas");
insert into tb_category (nome) values ("Móveis");

create table tb_product_category(
	id bigint auto_increment primary key,
	productId bigint not null,
	categoryId bigint not null,
    
    foreign key (productId) references tb_product (id),
    foreign key (categoryId) references tb_category (id)
);

insert into tb_product_category (productId, categoryId) values (1, 2);
insert into tb_product_category (productId, categoryId) values (2, 3);
insert into tb_product_category (productId, categoryId) values (3, 1);

select * from tb_product where price > 100;

update tb_product set nome = "Guarda Roupa", price = 530.01 where id = 2;

delete from tb_product_category where id = '3';
delete from tb_product where id = '3';
