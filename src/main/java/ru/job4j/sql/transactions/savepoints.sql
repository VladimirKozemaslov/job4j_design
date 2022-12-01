create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values
('Catfish', 30000, date '1751-03-20'),
('Sea turtle', 200000, date '1823-11-05'),
('Whale', 15000, date '1868-04-15'),
('Rhino', 18000, date '1966-08-11'),
('Elephant', 12000, date '1950-02-21'),
('Wolf', 6000, date '1981-01-09'),
('Rabbit', 1000, date '1830-03-04'),
('Parrot', 11000, null),
('Fox', 1500, null),
('Bear', 5000, date '1498-02-19');

-- скрипт
start transaction;
savepoint first_savepoint;
insert into fauna(name, avg_age, discovery_date) values ('Mouse', 9000, date '1523-07-22');
savepoint second_savepoint;
delete from fauna where name = 'Sea turtle';
update fauna set avg_age = 25000 where name = 'Whale';
rollback to second_savepoint;
rollback to first_savepoint;
commit;