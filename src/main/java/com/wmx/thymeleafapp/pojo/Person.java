package com.wmx.thymeleafapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/9 15:56
 */
@ApiModel(value = "Person", description = "用户实体对象")
public class Person {

    @ApiModelProperty(name = "pId", value = "用户 id")
    private Integer pId;
    @ApiModelProperty(name = "pName", value = "用户名称")
    private String pName;
    @ApiModelProperty(name = "birthday", value = "出生日期")
    private LocalDateTime birthday;
    @ApiModelProperty(name = "salary", value = "薪资")
    private Float salary;
    @ApiModelProperty(name = "summary", value = "描述")
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
