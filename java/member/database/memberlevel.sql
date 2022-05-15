--drop table member;
create table memberlevel (
	level		int(1) not null primary key,/* 1:준회원, 2:정회원, 3:우수회원 (4:운영자) 0:관리자 */
	levelName	varchar(10) not null, 		/* 1:준회원, 2:정회원, 3:우수회원 (4:운영자) 0:관리자 */
	createDate	datetime default now(), 	/* 회원레벨 등록일 */
	deleteDate	datetime,					/* 회원레벨 삭제일 */
	levelDel 	varchar(2) default 'NO' 	/* 회원레벨삭제여부 */
);

--delete from member;
insert into memberlevel values (0, '관리자', default, null, default);
insert into memberlevel values (1, '준회원', default, null, default);
insert into memberlevel values (2, '정회원', default, null, default);
insert into memberlevel values (3, '우수회원', default, null, default);
insert into memberlevel values (4, '운영자', default, null, default);
select * from memberlevel;