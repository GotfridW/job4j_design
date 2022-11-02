create table capital(
id serial primary key,
name varchar(255),
population int
);

create table country(
id serial primary key,
name varchar(255),
region varchar(255),
language varchar(255),
capital_id int references capital(id)
);

insert into capital(name, population) values('Rome', 2870000);
insert into capital(name, population) values('Berlin', 3640000);
insert into capital(name, population) values('London', 8980000);
insert into capital(name, population) values('Amsterdam', 907000);
insert into capital(name, population) values('Beijing', 21540000);

insert into country(name, region, language, capital_id) 
values('United Kingdom', 'Europe', 'English', 3);
insert into country(name, region, language, capital_id) 
values('Germany', 'Europe', 'German', 2);
insert into country(name, region, language, capital_id) 
values('Italy', 'Europe', 'Italian', 1);
insert into country(name, region, language, capital_id) 
values('China', 'Asia', 'Chinese', 5);
insert into country(name, region, language, capital_id) 
values('Netherlands', 'Europe', 'Dutch', 4);

select * from country ctr join capital cap
on ctr.capital_id = cap.id where cap.population < 1000000; 

select ctr.name "Country", ctr.language "Language", cap.name "Capital"
from country ctr join capital cap
on ctr.capital_id = cap.id where ctr.region = 'Asia';

select ctr.name, ctr.region, cap.name, cap.population
from country ctr join capital cap
on ctr.capital_id = cap.id 
where cap.population between 2000000 and 4000000;

  