CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into customers (first_name, last_name, age, country) values ('Peter', 'Jackson', 43, 'USA');
insert into customers (first_name, last_name, age, country) values ('John', 'Smith', 31, 'UK');
insert into customers (first_name, last_name, age, country) values ('Sophia', 'Blanchet', 26, 'France');
insert into customers (first_name, last_name, age, country) values ('Dmitry', 'Fedorov', 17, 'Russia');
insert into customers(first_name, last_name, age, country) values ('Agatha', 'Christi', 36, 'Germany');
insert into customers(first_name, last_name, age, country) values ('Will', 'Smith', 52, 'USA');

insert into orders (amount, customer_id) values (850, 3), (1400, 1), (2100, 3), (900, 4), (3500, 2);

select * from customers c
where c.id not in (select c2.id
from customers c2 join orders o
on o.customer_id = c2.id
);  