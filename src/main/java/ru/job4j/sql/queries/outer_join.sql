create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    dep_id int references departments(id)
);

insert into
departments(name)
values
('Mechanics'),
('Electrics'),
('Transport'),
('Financial'),
('Security');

select * from departments;

insert into
employees(name, dep_id)
values
('Freddy Cruger', 1),
('Leather face', 3),
('Saw', 5),
('Dracula', 5);

select * from employees;

select *
from
departments d left join employees e
    on d.id = e.dep_id;

select *
from
departments d right join employees e
    on d.id = e.dep_id;

select *
from
departments d full join employees e
    on d.id = e.dep_id;

select *
from
departments d cross join employees e;

select d.*
from
departments d left join employees e
    on d.id = e.dep_id
where e.id is null;

select *
from
departments d left join employees e
    on d.id = e.dep_id
where e.id is not null;

select *
from
departments d right join employees e
    on d.id = e.dep_id;

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(1)
);

insert into
teens(name, gender)
values
('Bill', 'M'),
('John', 'M'),
('Derek', 'M'),
('Alex', 'M'),
('Sarah', 'F'),
('Wiky', 'F'),
('Ginger', 'F'),
('Ivanka', 'F'),
('Melania', 'F'),
('Sesilia', 'F');

select M.name boy, F.name girl  from
(select * from teens where gender = 'M') as M
cross join
(select * from teens where gender = 'F') as F;