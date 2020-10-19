/* Tabla usuarios */
INSERT INTO USUARIOS (id, name, username, password, email, create_at, enabled) VALUES (1, 'admin', 'admin', '$2a$10$.e/6FPwqUazEMZKsucrF3.SYJ4NXYvMFWmRMjphxbdmnaFLdS6AXK', 'admin@correo.com', NOW(), 1);
INSERT INTO USUARIOS (id, name, username, password, email, create_at, enabled) VALUES (2, 'Andres', 'andres', '$2a$10$XeO0S2DqCK7C6uvL/N57Herx9PJxLqebcP6dqDeJHmjKbd0Qb2l2G', 'andres@correo.com', NOW(), 1);
INSERT INTO USUARIOS (id, name, username, password, email, create_at, enabled) VALUES (3, 'Angelito Guay ;)', 'angel', '$2a$10$G.O7vFPnECoSbXHRQTaE0O4GtEPrBy66qmzQIYGHgfUKM2XLhYVw.', 'angelitoguay@correo.com', NOW(), 1);
INSERT INTO USUARIOS (id, name, username, password, email, create_at, enabled) VALUES (4, 'Maria guay xDD', 'maria', '$2a$10$e5MXNZEKElYF.gQPyWyN4.BtAqqnjN3cnxkRyfZDmyTz9zos7NlGm', 'mariaguay@correo.com', NOW(), 1);

/* Tabla twits */

INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (1, '2020-1-1', 'La comida esta muy rica!', 1);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (2, '2020-1-2 22:01:00', 'La comida de mi novio esta super rica :D', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (3, '2020-1-3 22:02:00', 'Quiero un pony, ¿Alguien me lo regala?', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (4, '2020-1-4 22:03:00', 'Tener edad para comprar el pan, y que te denuncien por no llevar mascarilla XD', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (5, '2020-1-5 22:04:00', 'Erase una vez... Once upon a time...', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (6, '2020-1-6 22:05:00', 'La comida de mi novio esta super rica :D', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (7, '2020-1-7 22:06:00', 'Quiero un pony, ¿Alguien me lo regala?', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (8, '2020-1-8 22:07:00', 'Tener edad para comprar el pan, y que te denuncien por no llevar mascarilla XD', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (9, '2020-1-9 22:08:00', 'Erase una vez... Once upon a time...', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (10, '2020-1-10 22:09:00', 'La comida de mi novio esta super rica :D', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (11, '2020-1-11 22:10:00', 'Quiero un pony, ¿Alguien me lo regala?', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (12, '2020-1-12 22:11:00', 'Tener edad para comprar el pan, y que te denuncien por no llevar mascarilla XD', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (13, '2020-1-13 22:12:00', 'Erase una vez... Once upon a time...', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (14, '2020-1-14 22:13:00', 'La comida de mi novio esta super rica :D', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (15, '2020-1-15 22:14:00', 'Quiero un pony, ¿Alguien me lo regala?', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (16, '2020-1-16 22:15:00', 'Tener edad para comprar el pan, y que te denuncien por no llevar mascarilla XD', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (17, '2020-1-17 22:16:00', 'Erase una vez... Once upon a time...', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (18, '2020-1-18 22:17:00', 'La comida de mi novio esta super rica :D', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (19, '2020-1-19 22:18:00', 'Quiero un pony, ¿Alguien me lo regala?', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (20, '2020-1-20 22:19:00', 'Tener edad para comprar el pan, y que te denuncien por no llevar mascarilla XD', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (21, '2020-1-21 22:20:00', 'Erase una vez... Once upon a time...', 2);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (22, '2020-1-6', 'Me gustan los macarrones :·3', 3);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (23, '2020-1-7', 'Menudo vicio le estoy metiendo al WoW', 3);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (24, '2020-1-8', 'Me he terminado la historia de Genshin Impact en 4 dias!', 3);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (25, '2020-1-9', 'Estoy a punto de dominar el mundo muahahahahaha', 3);
INSERT INTO twits (id, create_at, texto, usuario_id) VALUES (26, '2019-1-10', 'Soy el twit numero 26 :D', 3);

/* Tabla authorities */

INSERT INTO authorities (id, user_id, authority) VALUES (1, 1, 'ROLE_ADMIN');
INSERT INTO authorities (id, user_id, authority) VALUES (2, 1, 'ROLE_USER');
INSERT INTO authorities (id, user_id, authority) VALUES (3, 2, 'ROLE_USER');
INSERT INTO authorities (id, user_id, authority) VALUES (4, 3, 'ROLE_USER');
INSERT INTO authorities (id, user_id, authority) VALUES (5, 4, 'ROLE_USER');

/* Tabla usuario_sigue_a */
/* usuario 1 sigue a 2, 3 */
INSERT INTO usuario_sigue_a (usuario_id, sigue_a_id) VALUES (1, 2);
INSERT INTO usuario_sigue_a (usuario_id, sigue_a_id) VALUES (1, 3);
/* usuario 2 sigue a 1 */
INSERT INTO usuario_sigue_a (usuario_id, sigue_a_id) VALUES (2, 1);
/* usuario 3 sigue a 2 */
INSERT INTO usuario_sigue_a (usuario_id, sigue_a_id) VALUES (3, 2);