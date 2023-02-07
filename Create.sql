use db;

-- drop table Account;
-- drop table Post_Status;
-- drop table Token;

-- create table Token(
--     content varchar(255),
--     expired_date Date,
--     primary key(content)
-- );

create table Account(
	id varchar(255),
-- 	id varchar(255) not null 
    name varchar(255),
    token varchar(255),
--     status enum('ACTIVE','DELETED','DEACTIVATED','SUSPENDED'),
    status int,
    primary key(id)
--     foreign key(token) references Token (content)
);


create table Post_Status(
    token_content varchar(255),
    post_token_status varchar(255),
    primary key(token_content)
);

