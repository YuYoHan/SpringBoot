create database study01;
use study01;

create table test_member (
	id bigint primary key auto_increment,
    member_name varchar(300)
);

insert into test_member (member_name) values ("apple");
select * from test_member;
drop table  test_member;

create table user(
    userId varchar(300) primary key,
    userPw varchar(300) not null,
    userName varchar(300) not null
);