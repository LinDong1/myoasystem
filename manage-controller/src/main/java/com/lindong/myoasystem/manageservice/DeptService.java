package com.lindong.myoasystem.manageservice;

import com.lindong.myoasystem.pojo.Dept;
import com.lindong.myoasystem.pojo.Employee;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.util.List;

public interface DeptService {

    /**
     * 添加部门（dept）
     * @param dept
     * @return
     */
    int deptAdd(Dept dept);


    /**
     * 按主键（deptno）查询
     *
     * @param deptno
     * @return
     */
    Dept selectByDeptNo(Integer deptno);

    /**
     * 查询所有
     * @return
     */
    List<Dept> selectAllDepts();

    /**
     * 更新部门
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 根据主键删除部门
     * @param deptno
     * @return
     */
    int deleteDeptByDeptNo(Integer deptno) throws MySQLIntegrityConstraintViolationException;
}
