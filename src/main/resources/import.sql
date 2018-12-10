insert into tbl_perfil (descricao) values ('recepcao');
insert into tbl_perfil (descricao) values ('cliente');	
insert into tbl_perfil (descricao) values ('veterinario');
insert into tbl_perfil (descricao) values ('ADMIN');

insert into tbl_permissao (id, url) values (1, '/clientes/**');
insert into tbl_permissao (id, url) values (2, '/usuarios/**');
insert into tbl_permissao (id, url) values (3, '/prontuario/**');
insert into tbl_permissao (id, url) values (4, '/animais/**');
insert into tbl_permissao (id, url) values (5, '/config/**');
insert into tbl_permissao (id, url) values (6, '/integration/mappings');
insert into tbl_permissao (id, url) values (7, '/agendamento/**');

insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('recepcao', 1);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('recepcao', 4);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('veterinario', 1);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('veterinario', 2);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('veterinario', 3);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('veterinario', 4);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('veterinario', 5);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('ADMIN', 1);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('ADMIN', 2);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('ADMIN', 3);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('ADMIN', 4);
insert into tbl_perfil_permissoes (perfis_descricao, permissoes_id) values ('ADMIN', 5);

insert into tbl_usuario (username, email, password) values ('vetweb', 'tecnologiaamr@gmail.com', '8idxMVxgdswZlsuLyr9Skw==');
insert into tbl_usuario (username, email, password) values ('recepcao', 'recepcao@vetweb.com', 'zEjsMlsw93bJOg6XHN0k/Q==');

insert into tbl_usuario_perfis (usuarios_username, perfis_descricao) values ('vetweb', 'veterinario');
insert into tbl_usuario_perfis (usuarios_username, perfis_descricao) values ('vetweb', 'ADMIN');
insert into tbl_usuario_perfis (usuarios_username, perfis_descricao) values ('recepcao', 'recepcao');