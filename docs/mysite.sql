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
   set name = "둘리",
	   password = "1234",
       gender = "female"
 where user.no = 1;
 
 select user.no from user;
 
 -- board list
 select * from board;
 
 -- insert
 insert into board values(null, 'test02댓글','test02댓글내용','1',now(),'2','1','1','1');
 
 -- board insert group no 
 insert into board 
 select null, 'test4','test글4','2',now(), g.no  , '0','0','1'
 from(select count(dept)+1 as no
		from board b
	   where dept =0) g;
 
 -- board update order_no 
 update board set order_no = order_no+1
 where group_no = 2
 and order_no >= 1;
 

 -- 
 

-- board select
select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept, u.no
 from board b,user u
 where b.user_no = u.no
 ORDER BY group_no desc, order_no ASC LIMIT 0,10;
 
 -- board insert
 insert into board values(null, 'test02','test02','1',now(),'2','0','0','1');
 
 -- board update
 update board set title = '수정1', contents ='수정1'
 where user_no = '1'
 and no = '3';
 select *
 from board;
 
 
 
 -- board view
 select b.title,b.contents, u.no
 from board b,user u
 where b.no = '2'
   and b.user_no = u.no;
 
 select * from board b, user u
   where b.user_no = u.no
 