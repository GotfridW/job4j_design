create table departments(
id serial primary key,
name varchar
);

create table employees(
id serial primary key,
name varchar,
dept_id int references departments(id)
);

insert into departments(name) values ('strategy'), ('marketing'), ('finance'), ('HR'), ('technology');
insert into employees(name, dept_id) values ('Frensis', 1);
insert into employees(name, dept_id) values ('Sarah', 3);
insert into employees(name, dept_id) values ('Murphy', 2);
insert into employees(name, dept_id) values ('Nancy', 4);
insert into employees(name, dept_id) values ('Andrew', 1);
insert into employees(name, dept_id) values ('William', 3);
insert into employees(name, dept_id) values ('Ann', 2);
insert into employees(name, dept_id) values ('Daniel', 4);
insert into employees(name, dept_id) values ('Alan', 4);
insert into employees(name, dept_id) values ('Eric', null);

select d.id, d.name, e.name 
from departments d
left join employees e on e.dept_id = d.id
order by d.id asc;

select d.id, d.name, e.name
from departments d
right join employees e on e.dept_id = d.id
order by d.id asc;

select d.id, d.name, e.name
from departments d
full join employees e on e.dept_id = d.id
order by d.id asc;

select d.id, d.name, e.name
from departments d
cross join employees e
order by d.id asc;

select d.id, d.name
from departments d
left join employees e on e.dept_id = d.id
where e.dept_id is null;

select e.name, d.name
from employees e
left join departments d on d.id = e.dept_id
order by d.id asc;

select e.name, d.name
from departments d
right join employees e on d.id = e.dept_id
order by d.id asc;