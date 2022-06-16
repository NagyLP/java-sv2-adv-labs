create table photo (id bigint not null auto_increment, description varchar(255), name_of_photographer varchar(255), photo_year integer not null, primary key (id));

create table photo_additional_info (photo_id bigint not null, additional_info varchar(255));

alter table photo_additional_info add constraint FK1iayvcqw2fs7tq9jsq5l5qx13 foreign key (photo_id) references photo (id);