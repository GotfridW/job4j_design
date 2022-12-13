create table students (
id serial primary key,
name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');

create table authors (
id serial primary key,
name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');
insert into authors (name) values ('Константин Симонов');

create table books (
id serial primary key,
name varchar(200),
author_id int references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);
insert into books (name, author_id) values ('Живые и Мертвые', 3);

create table orders (
id serial primary key,
active boolean default true,
book_id int references books(id),
student_id int references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (active, book_id, student_id) values (false, 5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);
insert into orders (book_id, student_id) values (6, 2);

create view show_student_having_exact_book_and_orders_number
as select s.name as student_name, count(*) as books_number
from students as s
join orders o on o.student_id = s.id
join books b on b.id = o.book_id
where o.active is true and s.id = (select s.id from students s
join orders o on o.student_id = s.id where o.book_id = 6)
group by (s.name);

select * from show_student_having_exact_book_and_orders_number;
