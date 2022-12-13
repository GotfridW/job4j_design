create table products (
id serial primary key,
name varchar(50),
producer varchar(50),
count integer default 0,
price integer
);

create table history_of_price (
id serial primary key,
name varchar(50),
price integer,
date timestamp
);

create or replace function tax_func1()
returns trigger as
$$
BEGIN
update products
set price = price + price * 0.2
where id = (select id from inserted);
return new;
END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_1
after insert on products
referencing new table as inserted
for each statement execute procedure tax_func1();

create or replace function tax_func2()
returns trigger as $$
BEGIN
new.price = new.price + (new.price * 0.18);
return new;
END;
$$ LANGUAGE 'plpgsql';

create trigger tax_trigger_2
before insert on products
for each row execute procedure tax_func2();

create or replace function update_price_history()
returns trigger as $$
BEGIN
insert into history_of_price (name, price, date) 
values (new.name, new.price, now());
return new;
END;
$$ LANGUAGE 'plpgsql';

create trigger price_history_trigger
after insert on products
for each row execute procedure update_price_history(); 

insert into products (name, producer, count, price) values ('Acoustic guitar', 'Yamaha', 3, 300);
insert into products (name, producer, count, price) values ('Flute', 'Jupiter', 6, 150);
insert into products (name, producer, count, price) values ('Synthesizer', 'Casio', 2, 380);


