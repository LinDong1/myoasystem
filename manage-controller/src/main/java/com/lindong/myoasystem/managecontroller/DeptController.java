package com.lindong.myoasystem.managecontroller;

import com.lindong.myoasystem.manageservice.DeptService;
import com.lindong.myoasystem.pojo.Dept;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     *  添加部门
     * @param dept
     * @return
     */
    @RequestMapping("goDeptAdd")
    @ResponseBody
    public String deptAdd(Dept dept){
        int i = deptService.deptAdd(dept);
        if (i==1){
            return "添加成功";
        }
        return "添加失败";
    }

    /**
     * 校验部门编号（deptno）
     */
    @RequestMapping("checkDeptNo")
    @ResponseBody
    public String checkDeptNo(Integer deptNo){
        Dept dept = deptService.selectByDeptNo(deptNo);
        if (dept == null) {
            return "可以使用";
        }
        return "该编号已存在";
    }

    /**
     * 部门管理--展示所有部门
     * @param session
     * @return
     */
    @RequestMapping("showDeptList")
    public String deptList(HttpSession session){

        List<Dept> depts = deptService.selectAllDepts();

        session.setAttribute("depts",depts);
        return "deptList";

    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @RequestMapping("updateDept")
    @ResponseBody
    public String updateDept(Dept dept){

        int i = deptService.updateDept(dept);
        if (i==1){
            return "修改成功";
        }

        return "修改失败";
    }

    @RequestMapping("/deleteDept/{deptno}")
    @ResponseBody
    public String deleteDept(@PathVariable Integer deptno){
        int i = 0;
        try {
            i = deptService.deleteDeptByDeptNo(deptno);
        } catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return "当前部门下有员工,不能删除,如果要删除,必须先删除此部门下所有的员工";
        }
        if (i==1){
            return "删除成功";
        }

        return "删除失败";
    }

}
