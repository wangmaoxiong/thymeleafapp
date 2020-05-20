package com.wmx.thymeleafapp.controller;

import com.wmx.thymeleafapp.mapper.PersonMapper;
import com.wmx.thymeleafapp.pojo.Person;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/9 16:13
 */
@RestController
public class PersonController {
    @Resource
    private PersonMapper personMapper;

    /**
     * 根据用户 id 查询----直接将结果返回给用户页面
     * http://localhost:8080/person/1
     *
     * @param pId
     * @return
     */
    @ApiOperation(value = "根据用户 id 查询", notes = "直接将结果返回给用户页面")
    @ApiImplicitParam(name = "pId", value = "用户 id", dataType = "Integer", required = true, paramType = "query")
    @GetMapping("/person/{pId}")
    public Person findPersonById(@PathVariable("pId") Integer pId) {
        Person person = personMapper.findPersonById(pId);
        return person;
    }

    /**
     * 查询所有用户----直接将结果返回给用户页面
     * http://localhost:8080/person
     *
     * @return
     */
    @ApiOperation(value = "查询所有用户")
    @GetMapping("/person")
    public List<Person> findAllPersons() {
        List<Person> personList = personMapper.findAllPersons();
        return personList;
    }

    /**
     * 添加用户： http://localhost:8080/person/add
     * {"pName":"张无忌","salary":9999.99,"birthday":"1897-09-09T12:00:11","summary":"武学奇才"}
     *
     * @param person
     * @return
     */
    @ApiOperation(value = "添加用户", notes = "根据用户POJO对象进行添加")
    @ApiImplicitParam(name = "person", value = "用户POJO对象", dataType = "Person", required = true, paramType = "Body")
    @PostMapping("/person/add")
    public String addPerson(@RequestBody Person person) {
        Integer integer = personMapper.addPerson(person);
        return String.valueOf(integer);
    }


    /**
     * 修改用户：http://localhost:8080/person/update
     * {"pId":1,"pName":"张无忌","salary":9999.99,"birthday":"1897-09-09T12:00:11","summary":"武学奇才"}
     *
     * @param person
     * @return
     */
    @ApiOperation(value = "修改用户", notes = "根据用户POJO对象进行修改")
    @ApiImplicitParam(name = "person", value = "用户POJO对象", dataType = "Person", required = true, paramType = "Body")
    @PostMapping("/person/update")
    public String updatePerson(@RequestBody Person person) {
        Integer integer = personMapper.updatePerson(person);
        return String.valueOf(integer);
    }

    /**
     * 删除用户:    http://localhost:8080/person/del/2
     *
     * @param pId
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "根据用户 id 进行删除")
    @ApiImplicitParam(name = "pId", value = "用户 id", dataType = "Integer", required = true, paramType = "Query")
    @GetMapping("/person/del/{pId}")
    public String deletePerson(@PathVariable("pId") Integer pId) {
        Integer integer = personMapper.deletePersonById(pId);
        return String.valueOf(integer);
    }
}
