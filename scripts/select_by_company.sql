CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'Coca-Cola');
insert into company (id, name) values (2, 'Microsoft');
insert into company (id, name) values (3, 'IBM');
insert into company (id, name) values (4, 'Toyota');
insert into company (id, name) values (5, 'Oracle');
insert into company (id, name) values (6, 'Nokia');

insert into person (id, name, company_id) values (1, 'Alex', 1);
insert into person (id, name, company_id) values (2, 'Bob', 1);
insert into person (id, name, company_id) values (3, 'Carl', 1);
insert into person (id, name, company_id) values (4, 'Isaac', 1);
insert into person (id, name, company_id) values (5, 'Bill', 2);
insert into person (id, name, company_id) values (6, 'George', 2);
insert into person (id, name, company_id) values (7, 'Harry', 3);
insert into person (id, name, company_id) values (8, 'Jim', 3);
insert into person (id, name, company_id) values (9, 'Sara', 3);
insert into person (id, name, company_id) values (10, 'Lara', 3);
insert into person (id, name, company_id) values (11, 'Nick', 4);
insert into person (id, name, company_id) values (12, 'Helen', 4);
insert into person (id, name, company_id) values (13, 'Jane', 5);
insert into person (id, name, company_id) values (14, 'William', 5);
insert into person (id, name, company_id) values (15, 'Richard', 5);
insert into person (id, name, company_id) values (16, 'Sandra', 6);
insert into person (id, name, company_id) values (17, 'Ian', 6);
insert into person (id, name, company_id) values (18, 'Victor', 6);


-- Имена всех person, не состоящих в компании с id=5

select p.name as employee_name, c.name as company
from person p join company c
on c.id = p.company_id
where c.id != 5;


-- Название(я) компании(й) с максимальным количеством персонала

select c.name as company, count(*) as staff_quantity
from company c
join person p on p.company_id = c.id
group by c.name
having count(*) = (select max(staff_number)
from (select count(company_id) staff_number
from person
group by company_id
) as cnt);