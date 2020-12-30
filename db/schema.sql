create table accident
(
    id   serial primary key,
    name varchar
);

insert into accident (name)
values ('acc1'),
       ('acc2'),
       ('acc3');
