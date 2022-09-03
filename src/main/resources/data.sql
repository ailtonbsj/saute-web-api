insert into roles (id, name) values
(1, 'admin'),
(2, 'editor'),
(3, 'viewer');

-- Password: pass
insert into user (id, name, username, password) values
(1, "Admin", "admin", "$2a$10$RJ5v/OlYKXkad2CgEpJRHO5ISf6nzWBKrFHM.IHUbUmqTQ1qpUqV6"),
(2, "Editor", "editor", "$2a$10$RJ5v/OlYKXkad2CgEpJRHO5ISf6nzWBKrFHM.IHUbUmqTQ1qpUqV6"),
(3, "Viewer", "viewer", "$2a$10$RJ5v/OlYKXkad2CgEpJRHO5ISf6nzWBKrFHM.IHUbUmqTQ1qpUqV6");

insert into user_roles values
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(2, 3),
(3, 3);