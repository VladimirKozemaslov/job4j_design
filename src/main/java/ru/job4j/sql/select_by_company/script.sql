CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values (1, 'Microsoft');
insert into company(id, name) values (2, 'Apple');
insert into company(id, name) values (3, 'UralMash');
insert into company(id, name) values (4, 'Yandex');
insert into company(id, name) values (5, 'Sber');

insert into person(id, name, company_id) values (1, 'Bill Gates', 1);
insert into person(id, name, company_id) values (2, 'Phil Spencer', 1);
insert into person(id, name, company_id) values (3, 'John Voznyak', 2);
insert into person(id, name, company_id) values (4, 'Peter North', 2);
insert into person(id, name, company_id) values (5, 'Andrey Lykov', 3);
insert into person(id, name, company_id) values (6, 'Yevgeny Petrov', 3);
insert into person(id, name, company_id) values (7, 'Petr Ivanov', 4);
insert into person(id, name, company_id) values (8, 'Ivan Petrov', 4);
insert into person(id, name, company_id) values (12, 'Ivan Ivanov', 4);
insert into person(id, name, company_id) values (9, 'German Gref', 5);
insert into person(id, name, company_id) values (10, 'Dmitry Okunev', 5);
insert into person(id, name, company_id) values (11, 'Dmitry Sokolov', 5);

select p.name, c.name
from person p, company c
where p.company_id = c.id and company_id != 5;

select c.name, count(p.*)
from person p, company c
where p.company_id = c.id
group by c.name
having count(p.*) = (
    select max(count) from (select c.name, count(p.*)
    from person p, company c
    where p.company_id = c.id
    group by c.name) as pc);