package com.wmx.thymeleafapp.pojo;

import java.time.LocalDateTime;

/**
 * 员工表
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/10 9:35
 */
public class Emp {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private LocalDateTime hiredate;
    private Float sal;
    private Float comm;
    private Integer deptno;

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public LocalDateTime getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDateTime hiredate) {
        this.hiredate = hiredate;
    }

    public Float getSal() {
        return sal;
    }

    public void setSal(Float sal) {
        this.sal = sal;
    }

    public Float getComm() {
        return comm;
    }

    public void setComm(Float comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}
