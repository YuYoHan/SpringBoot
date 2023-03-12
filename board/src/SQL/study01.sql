use study01;

create table user(
    userId varchar(300) primary key ,
    userPw varchar(300) not null ,
    userName varchar(300) not null
);

create table board(
    boardNum bigint primary key auto_increment,
    boardTitle varchar(300) not null ,
    boardContents varchar(6000) ,
    userId varchar(300),
    regDate datetime default now(),
    updateDate datetime default now(),
    constraint user_id_fk foreign key (userId) references user(userId)
);

create table spring_reply(
    replyNum bigint primary key auto_increment,
    userId varchar(300),
    replyContents varchar(4000) not null,
    regDate datetime default now(),
    updateDate datetime default now(),
    boardNum bigint,
    constraint reply_id_fk foreign key (userId) references user(userId),
    constraint board_id_fk foreign key (userId) references user(userId)
);


select * from board;
select * from user;
drop table board;

insert into board (boardTitle, boardContents, userId)
values	('테스트 제목1', 'apple이 작성한 테스트 내용1', 'apple'),
          ('테스트 제목2', 'banana이 작성한 테스트 내용2', 'banana'),
          ('테스트 제목3', 'cheery이 작성한 테스트 내용3', 'cheery'),
          ('테스트 제목4', 'durian이 작성한 테스트 내용4', 'durian');

insert into user
values ("cheery", "zxzz12", "김체리"),
       ("durian","zxzz12", "듀리안");
insert into board (boardTitle, boardContents, userId) (select boardTitle, boardContents,userId from board);