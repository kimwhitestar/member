show tables;

/*
	프로젝트 작업순서...
	1. 업무분석
	2. 요구사항설계(요구사항명세서)
	3. 데이타베이스(테이블/필드) 설계
	4. vo선언
	5. DAO선언
		: JDBC 라이브러리를 web-inf/lib폴더에 복사해 넣는다
		: Connection/PreparedStatement/ResultSet객체생성
	6. JSP,Servlet 연결
		: View작업 진행하면서,Service와 DAO객체를 활용한다
	7. 테스팅(요구사항과 매칭되는 체크) - 메뉴얼...
	8. 배포
 */
create table login (
	idx int not null auto_increment primary key, /* 고유번호 */
	mid varchar(20) not null, /* 아이디 */
	pwd varchar(20) not null, /* 비밀번호 */
	name varchar(20) not null, /* 회원 성명 */
	point int default 100, /* 포인트(가입시:100포인트) */
	lastDate datetime default now(), /* 최종 접속일자 */
	vCount int default 0 /* 개별 방문자의 방문카운트 누적 */
);

desc login;
drop table login;

insert into login /*(idx, mid, pwd, point, lastDate, vCount)*/
values ( default, 'admin', '1234', '관리자', default, default, default );
insert into login /*(idx, mid, pwd, point, lastDate, vCount)*/
values ( default, 'hkd1234', '1234', '홍길동', default, default, default );
insert into login /*(idx, mid, pwd, point, lastDate, vCount)*/
values ( default, 'kms1234', '1234', '김말숙', default, default, default );

select * from login;

--delete from login where mid = 'kms1234';
--delete from login;
select * from login where mid = 'kms1234';
