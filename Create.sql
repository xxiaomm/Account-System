use db;

-- drop table Account;
-- drop table Pos_Status;

create table Account(
	id varchar(255),
-- 	id varchar(255) not null 
    name varchar(255),
    token varchar(255),
--     status enum('ACTIVE','DELETED','DEACTIVATED','SUSPENDED'),
    status int,
    primary key(id)
);


create table Pos_Status(
    token_content varchar(255),
    pos_token_status varchar(255),
    primary key(token_content)
);

