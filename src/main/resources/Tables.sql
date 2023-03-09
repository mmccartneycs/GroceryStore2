--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables

drop table if exists cart;
drop table if exists product;
drop table if exists users;
create table users (
    account_id int primary key AUTO_INCREMENT,
    email varchar(255) unique,
    password varchar(255)
);
create table product (
    upc int primary key,
    price double,
    name varchar(255),
    tag varchar(255)
);
create table cart(
    account_id int not null,
    upc int,
    cart_id int primary key AUTO_INCREMENT, --not null
    foreign key (account_id) references users(account_id),
    foreign key (upc) references product(upc),
    quantity int
);
INSERT INTO product (upc, price, name, tag) VALUES
    (4011, 0.13, 'banana', 'fruit'), (3080, 2.49, 'avocado', 'fruit'), (4260, 6.73, 'coconut', 'fruit'), (3115, 2.31, 'peach', 'fruit'),
    (3082, 1.79, 'broccoli', 'vegetable'), (4062, 2.21, 'cucumber', 'vegetable'), (4061, 2.99, 'lettuce', 'vegetable'), (4089, 1.99, 'radish', 'vegetable'),
    (4924, 3.49, 'almonds', 'nuts'), (3105, 3.39, 'cashews', 'nuts'), (4930, 2.69, 'peanuts', 'nuts'), (4939, 4.19, 'pistachio', 'nuts'),
    (3062, 1.99, 'bay leaves', 'herbs'), (4888, 2.39, 'chives', 'herbs'), (3475, 2.59, 'mint', 'herbs'), (4904, 2.89, 'sage', 'herbs'),
    (2029, 4.49, 'oreos', 'snack'), (2405, 1.99, 'hershey bar', 'snack'), (2033, 3.99, 'doritos', 'snack'), (3425, 1.79, 'lifesavers gummies', 'snack');
INSERT INTO users (email, password) VALUES
    ('firstcustomer@gmail.com', 'genericpassword'), ('pinkPanther@hotmail.com', 'sneaky');
INSERT INTO cart (account_id, upc, quantity) VALUES
    (1, 3080, 1), (1, 3115, 3), (1, 3105, 1), (1, 2033, 2)
    (2, 4924, 2), (2, 4904, 1), (2, 2405, 6), (2, 4260, 1), (2, 4888, 2);
