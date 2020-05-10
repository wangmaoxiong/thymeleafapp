package com.wmx.thymeleafapp.mapper;


import com.wmx.thymeleafapp.pojo.Dept;

import java.util.List;
import java.util.Map;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/10 9:50
 * {@link Dept} 对应的 Mapper 接口.
 * 无论是注解版还是配置版，都需要要为 Mapper 接口指定 @Mapper 注解，或者使用 @MapperScan 扫描所有 Mapper 接口
 * 当使用 @MapperScan 扫描 Mapper 接口时，接口上 @Mapper 注解可以不写.
 */
public interface DeptMapper {

    /**
     * 根据 id 查询
     */
    Dept findDeptById(Integer deptno);

    /**
     * 查询所有
     */
    List<Dept> findAllDepts();

    /**
     * 查询指定部门的信息，以及部门下的所有人员信息.
     * @param deptno
     * @return
     */
    List<Map<String, Object>> findDeptEmp(Integer deptno);

    /**
     * 根据 id 删除
     */
    Integer deleteDeptById(Integer deptno);

    /**
     * 添加
     *
     * @param dept
     * @return
     */
    Integer addDept(Dept dept);

    /**
     * 修改
     *
     * @param dept
     * @return
     */
    Integer updateDept(Dept dept);
}
