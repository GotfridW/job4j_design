insert into roles(name) values('Agent');
insert into users(name, role_id) values('John Smith', 1);
insert into rules(name) values('Full access');
insert into roles_rules(role_id, rule_id) values(1, 1);
insert into state(name) values('In progress');
insert into category(name) values('Urgent');
insert into items(item, user_id, state_id, category) values(1, 1, 1, 1);
insert into comments(comment, item_id) values('Double price', 1); 
insert into attachs(attach, item_id) values('D:\requests\Jane_Smith.zip', 1);