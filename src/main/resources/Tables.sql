--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables

create table reservation (
    reservation_id int primary key auto_increment,
    guest_number int,
    date_reservation varChar(100);
    foreign key (guest_number) references user(user_id)
    );