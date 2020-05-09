package com.wmx.thymeleafapp.pojo;

import java.time.LocalDateTime;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/9 15:56
 */
public class Person {

    private Integer pId;
    private String pName;
    private LocalDateTime birthday;
    private Float salary;
    private String summary;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
