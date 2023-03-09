--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables
drop table if exists reservation;
drop table if exists account;

CREATE TABLE account (
    account_id int primary key auto_increment,
    username varchar(255) unique,
    password varchar(255)
);


create table reservation (
    reservation_id int primary key auto_increment,
    user_id int,
    number_guest int,
    date_reservation date,
    foreign key (user_id) references account(account_id)
    );