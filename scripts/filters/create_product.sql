create table type(
id serial primary key,
name varchar(255)
);

create table product(
id serial primary key,
name varchar(255),
type_id int references type(id),
expired_date date,
price float
);

insert into type(name) values ('Сыр'), ('Молоко'), ('Колбасные изделия'), ('Соки');

insert into product(name, type_id, expired_date, price) values ('Сыр Гауда', 1, '2022-11-10', 450);
insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2022-12-25', 150);
insert into product(name, type_id, expired_date, price) values ('Молоко 2,5%', 2, '2022-10-28', 90);
insert into product(name, type_id, expired_date, price) values ('Кефир', 2, '2022-11-12', 110);
insert into product(name, type_id, expired_date, price) values ('Мороженое Пломбир', 2, '2022-11-10', 45);
insert into product(name, type_id, expired_date, price) values ('Мороженое шоколадное', 2, '2022-11-29', 56);
insert into product(name, type_id, expired_date, price) values ('Колбаса Докторская', 3, '2022-10-31', 160);
insert into product(name, type_id, expired_date, price) values ('Сок апельсиновый', 4, '2023-02-14', 80);