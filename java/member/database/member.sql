show tables;
--drop table member;
create table member (
	idx 	int not null auto_increment,/*회원 고유번호*/
	mid		varchar(20) not null,		/*회원 아이디(중복불가)*/
	pwd 	varchar(100) not null,		/*비밀번호(암호화)*/
	nickName varchar(20) not null,		/*별명(중복불가)*/
	name	varchar(20) not null,		/*회원 성명*/
	gender	varchar(5) not null default '남자',	/*성별*/
	birthday datetime default now(),	/*생일*/
	tel		varchar(15),				/*전화번호(010-1234-1234)*/
	address varchar(50),				/*주소*/
	email	varchar(50) not null,		/*이메일(중복불가, 아이디/비밀번호 분실시 필요)-형식 체크할 것*/
	homepage varchar(50),				/*홈페이지(블로그) 주소*/
	job		varchar(20),				/*직업*/
	hobby	varchar(60),				/*취미*/
	photo	varchar(100) default 'noimage.jpg', /*회원사진*/
	content	text,						/*자기소개*/
	userInfo char(6) not null default '공개',		/*회원정보 공개여부(공개/비공개)*/
	userDel	char(2) not null default 'NO',		/*회원탈퇴신청여부(OK:탈퇴신청한 회원,NO:회원)*/	
	point	int default 100,			/*회원가입포인트(최초100, 방문시마다 1포인트 증가) */
	level	int default 1, 				/*1:준회원, 2:정회원, 3:우수회원 (4:운영자) 0:관리자 */
	visitCnt	int default 0,			/*방문횟수*/
	startDate	datetime default now(), /*최초가입일*/
	lastDate	datetime default now(), /*마지막접속일*/
	todayCnt	int default 0,			/*오늘 방문한 횟수*/

	primary key(idx, mid)
);

insert into member values (default, 'admin', '1234', '관리자', '박김이', default, default, '010-1234-1234', '충북 청주시 서원구 장전로51', 
'admin@abc.com', 'abc.com/admin', '프리랜서', '독서/음악/영화', default, '관리자입니다', default, default, default, default, default, default, default, default );
insert into member values (default, 'pkl1', '1234', '박김이1', '박김이', default, default, '02-123-1234', '', 
'pkl1@nambu.co.kr', 'nambu.co.kr/pkl1', '사무원', '독서/수영', default, '박김이입니다', default, default, default, default, default, default, default, default );
insert into member values (default, 'pkl1', '1234', '박김이2', '박김이', default, default, '042-123-1234', '서울특별시 강남구 남부터미널로1', 
'pkl2@nambu.co.kr', 'nambu.co.kr/pkl2', '사무원', '독서/수영', default, '박김이입니다', default, default, default, default, default, default, default, default );
insert into member values (default, 'pkl1', '1234', '박김이3', '박김이', default, default, '052-1234-1234', '', 
'pkl3@nambu.co.kr', 'nambu.co.kr/pkl3', '사무원', '독서/수영', default, '박김이입니다', default, default, default, default, default, default, default, default );
insert into member values (default, 'pkl1', '1234', '박김이4', '박김이', default, default, '032-1234-1234', '', 
'pkl4@nambu.co.kr', 'nambu.co.kr/pkl4', '사무원', '독서/수영', default, '박김이입니다', default, default, default, default, default, default, default, default );
insert into member values (default, 'pkl1', '1234', '박김이5', '박김이', default, default, '02-1234-1234', '서울특별시 강남구 남부터미널로1', 
'pkl5@nambu.co.kr', 'nambu.co.kr/pkl5', '사무원', '독서/수영', default, '박김이입니다', default, default, default, default, default, default, default, default );

select * from member;
select *, timestampdiff(day, lastDate, now()) as overDaysUserDel from member order by idx desc;

/*
--datediff 함수 : 날짜 차이
--datediff(날짜1, 날짜2) : 날짜1 - 날짜2

select datediff('2022-04-25', '2022-03-01');
--단위 : second, minute, hour, day, month, year
--timestampdiff 함수 : 날짜,시간 차이
--timestampdiff(단위, 날짜1, 날짜2) : 날짜2(2022-04-25 12:31:50) - 날짜1(2022-03-01 00:00:00)
select timestampdiff(day, '2022-03-01 10:10:10', '2022-04-25 12:31:50');
select 
	(select timestampdiff(year, '2022-03-01 10:10:10', '2022-04-25 12:31:50')) year,
	(select timestampdiff(month, '2022-03-01 10:10:10', '2022-04-25 12:31:50')) month,
	(select timestampdiff(day, '2022-03-01 10:10:10', '2022-04-25 12:31:50')) day,
	(select timestampdiff(minute, '2022-03-01 10:10:10', '2022-04-25 12:31:50')) minute,
	(select timestampdiff(second, '2022-03-01 10:10:10', '2022-04-25 12:31:50')) second
*/
