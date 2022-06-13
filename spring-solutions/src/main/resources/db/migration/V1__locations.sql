create table locations
(id bigint not null auto_increment PRIMARY KEY,
location_name varchar(255),
loc_lat double precision,
loc_lon double precision
);
insert into locations (location_name) values ('John Doe');
insert into locations (location_name) values ('Jack Doe');