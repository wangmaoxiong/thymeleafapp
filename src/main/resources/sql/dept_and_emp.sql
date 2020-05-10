
drop table if exists emp;
drop table if exists dept;
-- ----------------------------
create table dept (
  deptno int(2) not null default '0' COMMENT '部门编号',
  dname varchar(14) default null COMMENT '部门名称',
  loc varchar(13) default null COMMENT '部门所在位置',
  primary key (deptno)
) engine=innodb default charset=utf8;


insert into dept values ('10', 'accounting', 'new york');
insert into dept values ('20', 'research', 'dallas');
insert into dept values ('30', 'sales', 'chicago');
insert into dept values ('40', 'operations', 'boston ');
-- ----------------------------
create table emp (
  empno int(4) not null COMMENT '雇员编号',
  ename varchar(10) default null COMMENT  '雇员姓名',
  job varchar(9) default null COMMENT  '雇员职位',
  mgr int(4) default null COMMENT  '雇员对应的领导的编号',
  hiredate date default null COMMENT  '雇佣日期',
  sal float(7,2) default null COMMENT '薪水',
  comm float(7,2) default null COMMENT '奖金或补助',
  deptno int(2) default null COMMENT  '所属部门编号',
  primary key (empno),
  key emp_fk_dept (deptno),
  constraint emp_fk_dept foreign key (deptno) references dept (deptno)
) engine=innodb default charset=utf8;

insert into emp values ('7369', 'smith', 'clerk', '7902', '1992-05-05', '800.00', null, '20');
insert into emp values ('7499', '张三', 'salesman', '7698', '1993-08-15', '1600.00', '300.00', '30');
insert into emp values ('7521', 'ward', 'salesman', '7698', '1988-12-22', '1250.00', '500.00', '30');
insert into emp values ('7566', 'jones', 'manager', '7839', '1989-11-22', '2975.00', null, '20');
insert into emp values ('7654', 'martin', 'salesman', '7698', '1990-02-23', '1250.00', '1400.00', '30');
insert into emp values ('7698', 'blake', 'manager', '7839', '1981-02-25', '2850.00', null, '30');
insert into emp values ('7782', 'clark', 'manager', '7839', '1995-04-14', '2450.00', null, '10');
insert into emp values ('7788', 'scott', 'analyst', '7566', '1981-10-17', '3000.00', null, '20');
insert into emp values ('7839', 'king', 'president', null, '1981-11-08', '5000.00', null, '10');
insert into emp values ('7844', 'turner', 'salesman', '7698', '1983-12-02', '1500.00', '0.00', '30');
insert into emp values ('7876', 'adams', 'clerk', '7788', '1982-04-03', '1100.00', null, '20');
insert into emp values ('7900', 'james', 'clerk', '7698', '1985-07-03', '950.00', null, '30');
insert into emp values ('7902', '李四', 'analyst', '7566', '1988-05-16', '3000.00', null, '20');
insert into emp values ('7934', 'miller', 'clerk', '7782', '1999-06-06', '1300.00', null, '10');