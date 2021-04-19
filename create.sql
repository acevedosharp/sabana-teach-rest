create table user (id integer not null auto_increment, email varchar(255) not null, first_name varchar(255) not null, last_name varchar(255) not null, password_hash varchar(255) not null, role varchar(255) not null, primary key (id)) engine=InnoDB
alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
