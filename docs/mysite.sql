-- scheme user
desc user;

-- insert
insert into user values(null, '둘리','dooly@gmail.com','1234','male',now());

-- select01
select no, name from user where email= 'dhsj8405@naver.com' and password= '1234';

-- 회원정보 수정을 위한 조회
select no,name,email,gender,password from user where user.no = '2';

-- select02
select * from user;

select * from guestbook;

-- update 
update user
   set name = "update",
	   password = "1234",
       gender = "female"
 where user.no = 1;
 
 select user.no from user;
 