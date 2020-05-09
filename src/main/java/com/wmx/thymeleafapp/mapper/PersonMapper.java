package com.wmx.thymeleafapp.mapper;

import com.wmx.thymeleafapp.pojo.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/9 16:02
 * </p>
 * @Mapper 注解表示本类是一个 MyBatis 的 Mapper（映射）.
 * 1、以前 Spring 整合 mybatis 时，一个 POJO 对应 MyBatis一个操作数据库的 xml 文件， xml 文件又指向一个 mapper 接口.
 * 2、现在 Spring Boot 则省略了 xml 文件这一环节，直接将 sql 写在了接口上.
 */
@Mapper
public interface PersonMapper {

    /**
     * 根据用户 id 查询
     *
     * @param pId
     * @return :返回查询结果,不存在时返回 null
     * @Select ：等价于以前 xml 形式时的 <select 标签,sql写法仍然和以前一样
     */
    @Select(value = {"select * from Person where pId=#{pId}"})
    Person findPersonById(Integer pId);

    /**
     * 查询所有用户
     *
     * @return :返回查询结果
     * @Select ：等价于以前 xml 形式时的 <select 标签,sql写法仍然和以前一样
     */
    @Select(value = {"select * from Person"})
    List<Person> findAllPersons();

    /**
     * 根据用户 id 删除用户
     *
     * @return ：返回操作的行数，也可以不返回
     */
    @Delete("delete from Person where pId = #{pId}")
    Integer deletePersonById(Integer pId);

    /**
     * 添加用户
     *
     * @param Person ：因为主键 pId 自增，所以没设值
     * @return
     */
    @Insert("insert into Person(pName,birthday,salary,summary) values(#{pName},#{birthday},#{salary},#{summary})")
    Integer addPerson(Person Person);

    /**
     * 根据用户 pId 修改用户
     *
     * @param Person
     * @return
     */
    @Update("update Person set pName=#{pName},birthday=#{birthday},salary=#{salary},summary=#{summary} where pId=#{pId}")
    Integer updatePerson(Person Person);
}
