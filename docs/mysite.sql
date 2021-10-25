-- scheme user
desc user;

-- insert
-- insert into user values(null, '둘리','dooly@gmail.com','1234','male',now());

-- select01
select no, name from user where email= 'dhsj8405@naver.com' and password= '1234';

-- 회원정보 수정을 위한 조회
select no,name,email,gender,password from user where user.no = '2';

-- select02
select * from user;

select * from guestbook;

-- update 
-- update user
--    set name = "둘리",
-- 	   password = "1234",
--        gender = "female"
--  where user.no = 1;
 
 select user.no from user;
 
 -- board list
 select * from board;
 -- user list
 select * from user;
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
 and order_no >= 2;
        
 select * from board;

 

-- board select
select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept, u.no
 from board b,user u
 where b.user_no = u.no
 ORDER BY group_no desc, order_no ASC LIMIT 50,10;
 
 -- board insert
 insert into board values(null, 'test02','test02','1',now(),'2','0','0','1');
 
 -- board update
 update board set title = '수정1', contents ='수정1'
 where user_no = '2'
 and no = '3';
 
 select * from board
 where group_no = '2';
 

 
 -- board view
 select b.title,b.contents, u.no, b.group_no, b.order_no, b.dept
 from board b,user u
 where b.no = '2'
   and b.user_no = u.no;
 
 select * from board b, user u
   where b.user_no = u.no;
   
   -- comment insert
-- board insert group no 
 insert into board 
 select null, 'test댓글','test댓글','3',now(), '2', '1','1','1';

-- delete 
delete from board where no ='16';

select * from guestbook;
select * from board;
select * from user;
-- inform insert
insert into board select null, '삭제된 글','삭제된 글','10','7',now(), '2','2';

-- update hit
update board set hit = hit + 1
where no = '24';

-- list pager
-- select * from(
-- 				select rownum as rnum, floor((rownum-1)/(10+1) as page, count(*) over() as totcnt 
-- 				  from(
-- 						select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept, u.no
-- 						 from board b,user u
-- 						where b.user_no = u.no
-- 						ORDER BY group_no desc, order_no ASC LIMIT 0,10) a
-- 						)
-- 				where page = 3
-- );
-- select * from (
-- 			select A.*, ROWNUM as RNUM,
-- 		   FLOOR((ROWNUM-1)/(10+1) as page,
-- 		   count(*) over() as TOTCNT 
-- 				 from(
-- 							select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept, u.no
-- 							 from board b,user u
-- 							where b.user_no = u.no
-- 							ORDER BY group_no desc, order_no ASC LIMIT 0,10
-- 					) A
-- 				) where page = 1;	
	select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept, u.no
							 from board b,user u
							where b.user_no = u.no
							ORDER BY group_no desc, order_no ASC LIMIT 11,20;


-- board select
select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept, u.no
 from board b,user u
 where b.user_no = u.no
 ORDER BY group_no desc, order_no ASC LIMIT 0,10;
 
-- 총 페이지 수 조회
 
  select ceil(a.rnum/10) as page
	   from (
			select count(*) as rnum 
			from board
		)a;
 select count(*) as rnum 
			from board;
 
select count(*) from board;
 delete * from board,user where user_name = '둘리';
select * from user;
    
select * from (
				select count(*) over()  from (
											select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept, u.no
											 from board b,user u
											where b.user_no = u.no
											ORDER BY group_no desc, order_no ASC LIMIT 0,10
										)
				);
                
-- keyword 찾기기능                
select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept, u.no
 from board b,user u
 where b.user_no = u.no
 ORDER BY group_no desc, order_no ASC LIMIT 10,10;                
 
 
  select ceil(a.rnum/10) as page
	   from (
			select count(*) as rnum 
			from board b,user u
            where b.user_no = u.no
			and b.title like '%d%'
		)a;
 select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept, u.no
 from board b,user u
 where b.user_no = u.no
   and b.title like '%d%'
 ORDER BY group_no desc, order_no ASC LIMIT 10,10;          
 
 
 insert into user values(null,'관리자','admin@mysite.com','1234','male', now(), 'ADMIN');
 alter table user add column role enum('USER', 'ADMIN') not null default 'USER';
 select * from user;
 
 select * from board;
  select a.no,
				  		 a.title,
				  		 a.hit,
				  		 date_format(a.reg_date, '%Y-%m-%d %p %h:%i:%s') as regDate,
				  		 a.depth, 
				  		 b.name as userName,
				  		 a.user_no as userNo
				  	from board a, user b
				   where a.user_no = b.no
				     and (title like '%${keyword }%' or contents like '%${keyword }%')
				order by group_no desc, order_no asc;
		
-- mysite 03 board insert
insert
				  into board
				values ( null,
						 '제목',
						 'ㅎㅇㅎㅇ',
						 now(),
						 0,
						 ( select ifnull( max( group_no ), 0 ) + 1
						     from board a ),
						 1, 
						 0, 
						 1 )    ;               