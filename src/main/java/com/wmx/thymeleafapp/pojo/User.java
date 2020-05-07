package com.wmx.thymeleafapp.pojo;

import java.util.Date;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/7 10:57
 */
public class User {
    private Integer id;
    private String name;
    private Float salary;
    private String education;
    private Date birthday;

    public User() {
    }

    public User(Integer id, String name, Float salary, String education, Date birthday) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.education = education;
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
}
