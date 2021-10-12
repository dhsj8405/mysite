-- scheme user
desc user;

-- insert
insert into user values(null, '둘리','dooly@gmail.com','1234','male',now());

-- select01
select no, name from user where email= 'dhsj8405@naver.com' and password= '1234';

-- select02
select * from user;

select * from guestbook;