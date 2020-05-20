package com.wmx.thymeleafapp.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wmx.thymeleafapp.mapper.DeptMapper;
import com.wmx.thymeleafapp.pojo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/10 11:24
 */
@RestController
@ApiIgnore
public class DeptController {
    private static Logger logger = LoggerFactory.getLogger(DeptController.class);
    @Resource
    private DeptMapper deptMapper;

    /**
     * http://localhost:8080/dept/1
     * 根据主键查询
     *
     * @param deptno
     * @return
     */
    @GetMapping("/dept/{deptno}")
    public Dept findPartyById(@PathVariable("deptno") Integer deptno) {
        return deptMapper.findDeptById(deptno);
    }

    /**
     * http://localhost:8080/depts
     * 查询所有
     *
     * @return
     */
    @GetMapping("/depts")
    public List<Dept> findAllDepts() {
        return deptMapper.findAllDepts();
    }

    /**
     * http://localhost:8080/dept/emp
     * 查询指定部门的信息，以及部门下的所有人员信息.
     *
     * @param deptno
     * @return
     */
    @GetMapping("dept/emp")
    public List<Map<String, Object>> findDeptEmp(@RequestParam Integer deptno) {
        List<Map<String, Object>> deptEmp = deptMapper.findDeptEmp(deptno);
        return deptEmp;
    }

    /**
     * 根据主键删除
     * http://localhost:8080/dept/del/4
     *
     * @param deptno
     * @return
     */
    @GetMapping("/dept/del/{deptno}")
    public Object delDeptById(@PathVariable("deptno") Integer deptno) {
        JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
        ObjectNode objectNode = nodeFactory.objectNode();
        try {
            Integer integer = deptMapper.deleteDeptById(deptno);
            objectNode.put("code", 200);
            objectNode.put("msg", "success");
            objectNode.put("data", integer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            objectNode.put("code", 500);
            //因为员工表的外键关联了部门表的主键，所以未设置级联删除，在有员工数据引用被删除部门数据时，会抛如下异常.
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                objectNode.put("msg", "SQL完整性约束冲突异常，可能由外键约束引起.");
            } else {
                objectNode.put("msg", "服务器错误.");
            }
            objectNode.put("data", "");
        }
        return objectNode;
    }

    /**
     * 添加：  http://localhost:8080/dept/add
     * {"dname":"Java开发部","loc":"长沙"}
     *
     * @param dept
     * @return
     */
    @PostMapping("/dept/add")
    public String addDept(@RequestBody Dept dept) {
        Integer addDept = deptMapper.addDept(dept);
        return String.valueOf(addDept);
    }

    /**
     * 修改：  http://localhost:8080/dept/update
     * {"deptno":1,"dname":"IOS开发部","loc":"长沙市"}
     *
     * @param dept
     * @return
     */
    @PostMapping("/dept/update")
    public String updateDept(@RequestBody Dept dept) {
        Integer integer = deptMapper.updateDept(dept);
        return String.valueOf(integer);
    }

}
