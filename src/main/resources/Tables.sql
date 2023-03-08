--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables

drop table if exists product;
drop table if exists users;
drop table if exists cart;
create table users (
    account_id int primary key AUTO_INCREMENT,
    email varchar(255) unique,
    password varchar(255)
);
create table product (
    upc int primary key,
    price double,
    name varchar(255),
    description text(2500)
);
create table cart(
    account_id int not null,
    upc int,
    cart_id int primary key not null,
    foreign key (account_id) references users(account_id),
    foreign key (upc) references product(upc),
    quantity int
);

