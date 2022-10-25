INSERT INTO USER(name, email, password) VALUES('Aluno', 'aluno@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');

INSERT INTO COURSE(name, category) VALUES('Sistema da Informação', 'Back-end');
INSERT INTO COURSE(name, category) VALUES('Ciências da Computação', 'Front-end');

INSERT INTO REGISTRATION(title, message, status, user_id, course_id) VALUES('Pessoa 1', 'Registrado com sucesso', 'REGISTRADO', 1, 1);
INSERT INTO REGISTRATION(title, message, status, user_id, course_id) VALUES('Pessoa 2', 'Naõ registrado', 'NAO_REGISTRADO', 1, 1);
INSERT INTO REGISTRATION(title, message, status, user_id, course_id) VALUES('Pessoa 3', 'Em andamento',  'EM_ANDAMENTO', 1, 2);