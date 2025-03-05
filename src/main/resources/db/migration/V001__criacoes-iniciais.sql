create table cidades (
   id bigint not null auto_increment,
    nome varchar(255),
    estado_id bigint,
    primary key (id)
);

create table cozinhas (
   id bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
);

create table estados (
   id bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
);

create table formas_pagamento (
   id bigint not null auto_increment,
    descricao varchar(255),
    primary key (id)
);

create table grupos (
   id bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
);

create table grupos_permissoes (
   grupo_id bigint not null,
    permissao_id bigint not null,
    primary key (grupo_id, permissao_id)
);

create table permissoes (
   id bigint not null auto_increment,
    descricao varchar(255),
    nome varchar(255),
    primary key (id)
);

create table produtos (
   id bigint not null auto_increment,
    ativo boolean,
    descricao varchar(255),
    nome varchar(255),
    preco decimal(19,2),
    restaurante_id bigint,
    primary key (id)
);

create table restaurantes (
   id bigint not null auto_increment,
    aberto boolean,
    ativo boolean,
    data_atualizacao timestamp,
    data_cadastro timestamp,
    bairro varchar(255),
    cep varchar(255),
    complemento varchar(255),
    logradouro varchar(255),
    numero varchar(255),
    nome varchar(255),
    taxa_frete decimal(19,2),
    cozinha_id bigint,
    cidade_id bigint,
    primary key (id)
);

create table restaurantes_formas_pagamento (
   restaurante_id bigint not null,
    forma_pagamento_id bigint not null,
    primary key (restaurante_id, forma_pagamento_id)
);

create table usuarios (
   id bigint not null auto_increment,
    data_cadastro timestamp,
    email varchar(255),
    nome varchar(255),
    senha varchar(255),
    primary key (id)
);

create table usuarios_grupos (
   usuario_id bigint not null,
    grupo_id bigint not null,
    primary key (usuario_id, grupo_id)
);

alter table cidades
   add constraint FKdt0b3ronwpi1upsrhaeq6r69n
   foreign key (estado_id)
   references estados (id);

alter table grupos_permissoes
   add constraint FKfqmdfklkug3iagcjvpmytb1fn
   foreign key (permissao_id)
   references permissoes (id);

alter table grupos_permissoes
   add constraint FKbon4g13b7en00ty174t12ijc7
   foreign key (grupo_id)
   references grupos (id);

alter table produtos
   add constraint FKsl3jhd8nhd103c5nm6yocnnkx
   foreign key (restaurante_id)
   references restaurantes (id);

alter table restaurantes
   add constraint FKgdtjhnl3atr87y5s0tcnr2i5q
   foreign key (cozinha_id)
   references cozinhas (id);

alter table restaurantes
   add constraint FKlycva4y731k9g2dfw97qnyxxs
   foreign key (cidade_id)
   references cidades (id);

alter table restaurantes_formas_pagamento
   add constraint FKm917uvu83vf1dr9f1gex7opjs
   foreign key (forma_pagamento_id)
   references formas_pagamento (id);

alter table restaurantes_formas_pagamento
   add constraint FKdcf58nl2v0qpuhbtk4hvdeleu
   foreign key (restaurante_id)
   references restaurantes (id);

alter table usuarios_grupos
   add constraint FKdh3qddmbja2u6ioqbpdscom1t
   foreign key (grupo_id)
   references grupos (id);

alter table usuarios_grupos
   add constraint FKm3qhj4o3gwf1l0qub0kddvldd
   foreign key (usuario_id)
   references usuarios (id);