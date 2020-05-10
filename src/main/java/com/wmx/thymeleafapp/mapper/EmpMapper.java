package com.wmx.thymeleafapp.mapper;


import com.wmx.thymeleafapp.pojo.Emp;

import java.util.List;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/10 9:50
 * {@link Emp} 对应的 Mapper 接口.
 * 无论是注解版还是配置版，都需要要为 Mapper 接口指定 @Mapper 注解，或者使用 @MapperScan 扫描所有 Mapper 接口
 * 当使用 @MapperScan 扫描 Mapper 接口时，接口上 @Mapper 注解可以不写.
 */
public interface EmpMapper {

    /**
     * 根据 id 查询
     */
    Emp findEmpById(Integer empno);

    /**
     * 查询所有
     */
    List<Emp> findAllEmps();

    /**
     * 根据 id 删除
     */
    Integer deleteEmpById(Integer empno);

    /**
     * 添加
     *
     * @param emp
     * @return
     */
    Integer addEmp(Emp emp);

    /**
     * 修改
     *
     * @param emp
     * @return
     */
    Integer updateEmp(Emp emp);
}
