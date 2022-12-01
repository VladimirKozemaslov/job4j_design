create table items(
    id serial primary key,
    name varchar(30),
    description varchar(1000),
    price integer
);

insert into
items(name, description, price)
values
('Black bread', 'Borodinsky bread with cumin', 58),
('Lemonade', 'Buratino, 1,5 L', 43),
('Meat dumplings', 'Miratorg 800g', 205),
('Carrot', 'Uncleaned, 1 kg', 36),
('Twix Max', 'Delicious taste, double size', 78),
('Cabbage', 'White', 51),
('Pickles', '0.8 L', 74);

