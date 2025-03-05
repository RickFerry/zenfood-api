create table itens_pedido (
   id bigint not null auto_increment,
    observacao varchar(255),
    preco_total decimal(19,2),
    preco_unitario decimal(19,2),
    quantidade integer,
    pedido_id bigint not null,
    produto_id bigint not null,
    primary key (id)
);

create table pedidos (
   id bigint not null auto_increment,
    data_cancelamento timestamp,
    data_confirmacao timestamp,
    data_criacao timestamp,
    data_entrega timestamp,
    bairro varchar(255),
    cep varchar(255),
    complemento varchar(255),
    logradouro varchar(255),
    numero varchar(255),
    status varchar(255),
    subtotal decimal(19,2),
    taxa_frete decimal(19,2),
    valor_total decimal(19,2),
    cliente_id bigint not null,
    cidade_id bigint,
    forma_pagamento_id bigint not null,
    restaurante_id bigint not null,
    primary key (id)
);

alter table itens_pedido
   add constraint FK42mycompce3b7yt3l6ukdwsxy
   foreign key (pedido_id)
   references pedidos (id);

alter table itens_pedido
   add constraint FKxytdlekpdaobqphujy9bmuhl
   foreign key (produto_id)
   references produtos (id);

alter table pedidos
   add constraint FK6dtctern9votxnaydg7g2uifb
   foreign key (cliente_id)
   references usuarios (id);

alter table pedidos
   add constraint FK5gixn3m0yt8wagkilvtfsp148
   foreign key (cidade_id)
   references cidades (id);

alter table pedidos
   add constraint FKmaivwga0val3dceem887sihbt
   foreign key (forma_pagamento_id)
   references formas_pagamento (id);

alter table pedidos
   add constraint FKf3mf88pcxawf3nt06400qmqn3
   foreign key (restaurante_id)
   references restaurantes (id);