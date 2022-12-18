create database  contacts_book;

create table if not exists contacts(
                id serial not null primary key ,
                first_name varchar(50) not null ,
                last_name varchar(50) not null ,
                email varchar(50)not null ,
                phone_number bigint not null
);

INSERT INTO contacts (first_name, last_name, email, phone_number)
VALUES ('Igor','Shpura','shpura@gmail.com',0982468587);
INSERT INTO contacts (first_name, last_name, email, phone_number)
VALUES ('Sergey','Kaunenko','kaunenko@gmail.com',0982234352);
INSERT INTO contacts (first_name, last_name, email, phone_number)
VALUES ('Nadya','Shpura','nadya@gmail.com',098453453);
