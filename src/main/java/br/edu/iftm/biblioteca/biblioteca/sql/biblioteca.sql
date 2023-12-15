create database biblioteca;
use biblioteca;

create table usuario (
    nome_usuario varchar (200) primary key,
    nome_completo varchar (200),
    cpf_usuario varchar (15),
    email varchar(200),
    telefone varchar (20),
    senha varchar(200)
);

create table unidade (
	id_unidade int auto_increment primary key,
    nome_unidade varchar (100),
    endereco varchar (255)
);

insert into unidade() values
	(null, 'Unidade Udi Centro', 'R. Blanche Galassi, 150 - Altamira'),
    (null, 'Unidade Rural', 'Fazenda Sobradinho s/nº, Zona Rural'),
    (null, 'Unidade Uberaba', 'Av. Edilson Lamartine Mendes, 300 - Parque das Americas');

create table tb_role (
   id integer auto_increment,
   nome varchar(255) not null,
   primary key(id)
);

insert into tb_role(nome) values ('ADMIN');
insert into tb_role(nome) values ('USER');

create table tb_role_user (
   nome_usuario varchar(255) not null,
   role_id integer not null,
   primary key(nome_usuario,role_id),
   foreign key(nome_usuario) references usuario(nome_usuario),
   foreign key(role_id) references tb_role(id)
);


create table livro (
	id_livro int auto_increment primary key,
    nome_livro varchar (255),
    autor varchar (255),
    disponibilidade varchar(12)
);

insert into livro() values
 	(null, 'O Pequeno Principe', 'Antoine de Saint-Exupéry', 'Disponivel'),
    (null, 'Harry Potter e a Pedra Filosofal', 'JK Rolling', 'Disponivel'),
    (null, 'Harry Potter e o Cálice de Fogo', 'JK Rolling', 'Disponivel');


create table emprestimo (
	id_emprestimo int auto_increment primary key,
    data_retirada date,
    data_devolucao date,
    status varchar (100),
    id_livro int,
    foreign key (id_livro) references Livro (id_livro)
);

create table livro_emprestimo (
	id_livro_emprestimo int auto_increment primary key,
    id_livro int,
    id_emprestimo int,
	foreign key (id_livro) references Livro (id_livro),
    foreign key (id_emprestimo) references Emprestimo (id_emprestimo)
);

insert into tb_role_user(nome_usuario,role_id) values ('jessica',1);
insert into tb_role_user(nome_usuario,role_id) values ('fernanda',2);