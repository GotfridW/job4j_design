create table fauna(
id serial primary key,
name text,
avg_age int,
discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Jaguar', 4900, '1758-01-01');
insert into fauna(name, avg_age, discovery_date) values('Turtle', 16400, '1788-01-01');
insert into fauna(name, avg_age, discovery_date) values('Zebra', 9100, '1591-01-01');
insert into fauna(name, avg_age, discovery_date) values('Chocolate frog', 5800, '2020-01-01');
insert into fauna(name, avg_age, discovery_date) values('Giant petrel', 5800, '1905-01-01');
insert into fauna(name, avg_age, discovery_date) values('Bonobo', 14610, '1929-01-01');
insert into fauna(name, avg_age, discovery_date) values('Purple crab', 700, '2012-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';