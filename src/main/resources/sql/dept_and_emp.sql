
drop table if exists emp;
drop table if exists dept;
-- ----------------------------
create table dept (
  deptno int not null auto_increment COMMENT ''部门编号'' ,
  dname varchar(14) default null COMMENT ''部门名称'',
  loc varchar(13) default null COMMENT ''部门所在位置'',
  primary key (deptno)
) engine=innodb default charset=utf8;


insert into dept values (1, ''accounting'', ''new york'');
insert into dept values (2, ''research'', ''dallas'');
insert into dept values (3, ''sales'', ''chicago'');
insert into dept values (4, ''operations'', ''boston '');
-- ----------------------------
create table emp (
  empno int not null auto_increment COMMENT ''雇员编号'',
  ename varchar(10) default null COMMENT  ''雇员姓名'',
  job varchar(9) default null COMMENT  ''雇员职位'',
  mgr int(4) default null COMMENT  ''雇员对应的领导的编号'',
  hiredate date default null COMMENT  ''雇佣日期'',
  sal float(7,2) default null COMMENT ''薪水'',
  comm float(7,2) default null COMMENT ''奖金或补助'',
  deptno int(2) default null COMMENT  ''所属部门编号'',
  primary key (empno),
  key emp_fk_dept (deptno),
  constraint emp_fk_dept foreign key (deptno) references dept (deptno)
) engine=innodb default charset=utf8;

insert into emp values (1, ''smith'', ''clerk'', ''7902'', ''1992-05-05'', ''800.00'', null, ''2'');
insert into emp values (2, ''张三'', ''salesman'', ''7698'', ''1993-08-15'', ''1600.00'', ''300.00'', ''3'');
insert into emp values (3, ''ward'', ''salesman'', ''7698'', ''1988-12-22'', ''1250.00'', ''500.00'', ''3'');
insert into emp values (4, ''jones'', ''manager'', ''7839'', ''1989-11-22'', ''2975.00'', null, ''2'');
insert into emp values (5, ''martin'', ''salesman'', ''7698'', ''1990-02-23'', ''1250.00'', ''1400.00'', ''3'');
insert into emp values (6, ''blake'', ''manager'', ''7839'', ''1981-02-25'', ''2850.00'', null, ''3'');
insert into emp values (7, ''clark'', ''manager'', ''7839'', ''1995-04-14'', ''2450.00'', null, ''1'');
insert into emp values (8, ''scott'', ''analyst'', ''7566'', ''1981-10-17'', ''3000.00'', null, ''2'');
insert into emp values (9, ''king'', ''president'', null, ''1981-11-08'', ''5000.00'', null, ''1'');
insert into emp values (10, ''turner'', ''salesman'', ''7698'', ''1983-12-02'', ''1500.00'', ''0.00'', ''3'');
insert into emp values (11, ''adams'', ''clerk'', ''7788'', ''1982-04-03'', ''1100.00'', null, ''2'');
insert into emp values (12, ''james'', ''clerk'', ''7698'', ''1985-07-03'', ''950.00'', null, ''3'');
insert into emp values (13, ''李四'', ''analyst'', ''7566'', ''1988-05-16'', ''3000.00'', null, ''2'');
insert into emp values (14, ''miller'', ''clerk'', ''7782'', ''1999-06-06'', ''1300.00'', null, ''1'');