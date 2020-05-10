package com.wmx.thymeleafapp.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wmx.thymeleafapp.mapper.DeptMapper;
import com.wmx.thymeleafapp.pojo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/10 11:24
 */
@RestController
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
}
