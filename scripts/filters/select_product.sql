select * from product p
join type t on t.id = p.type_id
where t.name = 'Сыр';

select * from product p
where p.name like '%Мороженое%';

select * from product p
where p.expired_date < current_date;

select * from product
where price = (select max(price) from product);

select t.name, count(*) as "Number of products"
from product p
join type t on t.id = p.type_id
group by t.name;

select * from product p
join type t on t.id = p.type_id
where t.name = 'Сыр' or t.name = 'Молоко';

select t.name as "Product name", count(p.id) as "Number of products"
from type t join product p on t.id = p.type_id
group by t.name
having count(p.id) < 10;

select p.name, t.name from product p
join type t on t.id = p.type_id;