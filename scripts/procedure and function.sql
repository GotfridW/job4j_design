create table products (
id serial primary key,
name varchar(50),
producer varchar(50),
count integer default 0,
price integer
);
-- Используется таблица в БД к предыдущему заданию

create or replace procedure delete_data (d_producer varchar)
language 'plpgsql'
as $$
BEGIN
delete from products 
where producer = d_producer;
END;
$$;

call delete_data ('Yamaha');

create or replace function f_delete_data (d_count integer)
returns integer
language 'plpgsql'
as $$
declare
result integer;
begin
select count(*) into result 
from products 
where count < d_count;
delete from products 
where count < d_count;
return result;
end;
$$;

select f_delete_data (4);