CREATE TABLE authorities
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users
(
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, password, enabled, authority_id)
values ('root', '$2a$10$EJYq7dzggB0LM2bNnDIun.5oXd/e4UxNUZTjlnZH7Azj9r5ZhPFLe', true,
        (select id from authorities where authority = 'ROLE_ADMIN'));