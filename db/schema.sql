create table accident
(
    id   serial primary key,
    name varchar
);

insert into accident_type (name)
values ('Две машины'),
       ('Машина и человек'),
       ('Машина и велосипед');

insert into accident_rule (name)
values ('Rule1'),
       ('Rule2'),
       ('Rule3');
