insert into cozinhas (nome) values ('Brasileira');
insert into cozinhas (nome) values ('Japonesa');
insert into cozinhas (nome) values ('Italiana');
insert into cozinhas (nome) values ('Chinesa');
insert into cozinhas (nome) values ('Mexicana');


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

insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) values ('Restaurante 1', 10.0, true, true, now(), now(), 1, '13050-255', 'Rua Floriano Peixoto', '105', 'Apto 202', 'Centro', 1);
insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) values ('Restaurante 2', 15.0, true, true, now(), now(), 2, '13050-255', 'Rua Floriano Peixoto', '105', 'Apto 202', 'Centro', 2);
insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) values ('Restaurante 3', 20.0, true, true, now(), now(), 3, '13050-255', 'Rua Floriano Peixoto', '105', 'Apto 202', 'Centro', 3);
insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) values ('Restaurante 4', 25.0, true, true, now(), now(), 4, '13050-255', 'Rua Floriano Peixoto', '105', 'Apto 202', 'Centro', 4);
insert into restaurantes (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) values ('Restaurante 5', 30.0, true, true, now(), now(), 5, '13050-255', 'Rua Floriano Peixoto', '105', 'Apto 202', 'Centro', 5);

insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (1, 1);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (1, 2);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (1, 3);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (1, 4);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (1, 5);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (1, 6);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (2, 1);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (2, 2);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (2, 3);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (2, 4);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (2, 5);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (2, 6);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (3, 1);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (3, 2);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (3, 3);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (3, 4);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (3, 5);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (3, 6);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (4, 1);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (4, 2);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (4, 3);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (4, 4);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (4, 5);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (4, 6);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (5, 1);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (5, 2);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (5, 3);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (5, 4);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (5, 5);
insert into restaurantes_formas_pagamento (restaurante_id, forma_pagamento_id) values (5, 6);