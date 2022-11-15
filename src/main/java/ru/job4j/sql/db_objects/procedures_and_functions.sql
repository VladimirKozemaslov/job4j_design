create table products2 (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure delete_data(p_count integer)
language 'plpgsql'
as $$
    BEGIN
    delete from products2 where count <= p_count;
    END
$$;

create or replace function f_delete_data(p_count integer)
returns integer
language 'plpgsql'
as
$$
    declare
        v_cnt integer;
    begin
        select into v_cnt count(1) from products2 where count <= p_count;
        delete from products2 where count <= p_count;
        return v_cnt;
    end;
$$;

insert into products2(name, producer, count, price)
values
('Iphone', 'Apple', 5, 30000),
('Redmi 10', 'Xiaomi', 11, 17000),
('Samurai', 'ZTE', 7, 15000);

call delete_data(10);

select f_delete_data(11);