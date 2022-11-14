create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies,
    engine_id int references car_engines,
    transmission_id int references car_transmissions
);

insert into car_bodies(name) values
('Sedan'),
('Liftback'),
('Hatchback'),
('Universal');

insert into car_engines(name) values
('V8'),
('Opposite'),
('Linear'),
('Rotary');

insert into car_transmissions(name) values
('Manual'),
('Automatical'),
('Sequental'),
('Variator');

insert into cars(name, body_id, engine_id, transmission_id) values
('Skoda Octavia', 2, 3, 1),
('Ford Mustang', 1, 1, 1),
('Ford Raptor', null, 1, 1),
('Subaru Forester', 4, 2, 4),
('Mazda RX-7', 1, 4, 1),
('Toyota Corolla', 1, 3, 4),
('Mercedes GT-R', 1, 2, 1),
('Skoda Superb', 1, 3, 2),
('BMW X6', null, null, 2),
('Audi A4', 1, 2, 1);

select cb.* from
car_bodies cb left join cars c
    on cb.id = c.body_id
where c.body_id is null;

select ce.* from
car_engines ce left join cars c
    on ce.id = c.engine_id
where c.engine_id is null;

select ct.* from
car_transmissions ct left join cars c
    on ct.id = c.transmission_id
where c.transmission_id is null;

select c.name, cb.name as body, ce.name as engine, ct.name as transmission from
cars c left join car_bodies cb
    on cb.id = c.body_id
left join car_engines ce
    on ce.id = c.engine_id
left join car_transmissions ct
    on ct.id = c.transmission_id;