insert into cozinhas (nome) values ('Brasileira');
insert into cozinhas (nome) values ('Japonesa');
insert into cozinhas (nome) values ('Italiana');
insert into cozinhas (nome) values ('Chinesa');
insert into cozinhas (nome) values ('Mexicana');

insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ('Restaurante 1', 10.0, true, true, now(), now(), 1);
insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ('Restaurante 2', 10.0, true, true, now(), now(), 2);
insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ('Restaurante 3', 10.0, true, true, now(), now(), 3);
insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ('Restaurante 4', 10.0, true, true, now(), now(), 4);
insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id) values ('Restaurante 5', 10.0, true, true, now(), now(), 5);

insert into formas_pagamento (descricao) values ('Dinheiro');
insert into formas_pagamento (descricao) values ('Cartão de crédito');
insert into formas_pagamento (descricao) values ('Cartão de débito');
insert into formas_pagamento (descricao) values ('Vale refeição');
insert into formas_pagamento (descricao) values ('Vale alimentação');
insert into formas_pagamento (descricao) values ('Pix');

insert into estados (nome) values ('São Paulo');
insert into estados (nome) values ('Rio de Janeiro');
insert into estados (nome) values ('Minas Gerais');
insert into estados (nome) values ('Bahia');
insert into estados (nome) values ('Ceará');

insert into cidades (nome, estado_id) values ('Campinas', 1);
insert into cidades (nome, estado_id) values ('São Paulo', 1);
insert into cidades (nome, estado_id) values ('Rio de Janeiro', 2);
insert into cidades (nome, estado_id) values ('Belo Horizonte', 3);
insert into cidades (nome, estado_id) values ('Salvador', 4);
insert into cidades (nome, estado_id) values ('Fortaleza', 5);