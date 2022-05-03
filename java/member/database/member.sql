show tables;

create table member (
	idx 	int not null auto_increment,/*회원 고유번호*/
	mid		varchar(20) not null,		/*회원 아이디(중복불가)*/
	pwd 	varchar(100) not null,		/*비밀번호(암호화)*/
	nickName varchar(20) not null,		/*별명(중복불가)*/
	name	varchar(20) not null,		/*회원 성명*/
	gender	varchar(5) 	default '남자',	/*성별*/
	birthday datetime	default now(),	/*생일*/
	tel		varchar(15),				/*전화번호(010-1234-5678)*/
	address varchar(50),				/*주소*/
	email	varchar(50) not null,		/*이메일(중복불가, 아이디/비밀번호 분실시 필요)-형식 체크할 것*/
	homepage varchar(50),				/*홈페이지(블로그) 주소*/
	job		varchar(20),				/*직업*/
	hobby	varchar(60),				/*취미*/
	photo	varchar(100) default 'noimage.jpg', /*회원사진*/
	content	text,						/*자기소개*/
	userInfo char(6) default '공개',		/*회원정보 공개여부(공개/비공개)*/
	userDel	char(2) default 'NO',		/*회원탈퇴신청여부(OK:탈퇴신청한 회원,NO:회원)*/	
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