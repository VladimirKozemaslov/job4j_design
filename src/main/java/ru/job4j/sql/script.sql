create table cars (
	id serial primary key,
	mark varchar(30),
	num integer,
	production_date date
);

insert into cars(mark, num, production_date) values 
('Skoda Octavia A7', 321, to_date('14.09.2014', 'DD.MM.YYYY'));

select * from cars;

update cars set production_date = to_date('25.09.2014', 'DD.MM.YYYY');

select * from cars;

delete from cars;

commit;

