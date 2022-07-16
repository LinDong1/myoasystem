package com.lindong.myoasystem.managecontroller;

import com.github.pagehelper.PageInfo;
import com.lindong.myoasystem.commons.DateFormatUtil;
import com.lindong.myoasystem.manageservice.DeptService;
import com.lindong.myoasystem.manageservice.EmployeeService;
import com.lindong.myoasystem.manageservice.PositionService;
import com.lindong.myoasystem.pojo.Dept;
import com.lindong.myoasystem.pojo.Employee;
import com.lindong.myoasystem.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private PositionService positionService;

    /**
     * 通过empid和密码登录
     *
     * @param empid
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("login")
    public String login(String empid, String password, HttpServletRequest request) {
        Employee employee = employeeService.login(empid, password);
        if (employee != null) {
            request.getSession().setAttribute("employee", employee);
            return "redirect:main";
        }
        request.setAttribute("msg", "用户名或者密码错误，请重新输入");
        return "login";
    }



    /**
     * 退出登录
     */
    @RequestMapping("logout")
    public String logout(HttpSession session) {
//        强制销毁session
        session.invalidate();
        return "login";
    }


    /**
     * 将所有的部门 岗位 直接上级的信息查出来
     * 将结果显示到页面上
     *
     * @param
     * @return
     */
    @RequestMapping("empAddBefore")
    public String empAdd(Model model) {
        List<Dept> depts = deptService.selectAllDepts();
        model.addAttribute("depts", depts);

        List<Position> positions = positionService.selectAllPosition();
        model.addAttribute("positions", positions);

        List<Employee> mgrs = employeeService.selectAllMgr();
        model.addAttribute("mgrs", mgrs);

        return "empAdd";
    }

    /**
     * 根据提交的数据添加employee
     * @param employee
     * @return
     * @throws ParseException
     */
    @RequestMapping("goEmpAdd")
    @ResponseBody
    public String goEmpAdd(Employee employee) throws ParseException {
//        先进行日期处理
        DateFormatUtil.EmployeeDateFormat(employee);

//        初始密码设置
        if (employee.getPassword() == null) {
            employee.setPassword("123456");
        }
        int i = employeeService.addEmployee(employee);
        if (i == 1) {
            return "添加成功";
        }
        return "添加失败！！";
    }

    /**
     * 根据empid查数据库中对应的employee,将结果显示到页面上
     * @param empid
     * @param model
     * @return
     * @throws ParseException
     */
    @GetMapping("goEmpInfo/{empid}")
    public String goEmpInfo(@PathVariable String empid, Model model) throws ParseException {
        Employee emp = employeeService.selectByEmpid(empid);

        DateFormatUtil.EmployeeDateFormat(emp);

        model.addAttribute("emp", emp);

        return "empInfo";
    }

    /**
     * 修改前将所有部门信息，上级信息填入单选框中
     * @param empid
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping("empUpdateBefore/{empid}")
    public String empUpdateBefore(@PathVariable String empid, Model model) throws ParseException {
        Employee emp = employeeService.selectByEmpid(empid);
        DateFormatUtil.EmployeeDateFormat(emp);
        model.addAttribute("emp", emp);

        //所有部门信息
        List<Dept> depts = deptService.selectAllDepts();
        model.addAttribute("depts", depts);
        System.out.println("所有部门信息:"+depts);

        //所有的上级
        List<Employee> mgrs = employeeService.selectAllMgr();
        model.addAttribute("mgrs", mgrs);

        return "empUpdate";
    }


    /**
     * 根据修改后的数据更新数据库中的信息
     * 注意：如果数据库中的数据有某条属性，而提交的需要修改的数据为空，则不改变数据库中的该条属性
     * @param employee
     * @return
     * @throws ParseException
     */
    @RequestMapping("goEmpUpdate")
    @ResponseBody
    public String goEmpUpdate(Employee employee) throws ParseException {
//        先进行日期处理
        DateFormatUtil.EmployeeDateFormat(employee);
        int i = employeeService.updateEmployee(employee);
        if (i == 1) {
            return "修改成功";
        }
        return "修改失败！！";
    }


    @RequestMapping("empDelete/{empid}")
    @ResponseBody
    public String empDelete(@PathVariable String empid){
        int i = employeeService.deleteEmployee(empid);

        if (i==1){
            return "删除成功";
        }
        return "删除失败";
    }

    /**
     * 根据empid重置密码，初始密码为 123456
     * @param empid
     * @return
     */
    @RequestMapping("resetPassword/{empid}")
    @ResponseBody
    public String resetPassword(@PathVariable String empid){
        int i = employeeService.resetPassword(empid);

        if (i==1){
            return "重置成功,初始密码为：123456";
        }
        return "重置失败";
    }

    /**
     * 多条件模糊查询(未分页)
     * @return
     */
    @RequestMapping("mutiQuery")
    public String mutiQuery(Employee employee,HttpSession session) throws ParseException {
        List<Employee> emps=employeeService.mutiQueryLike(employee);
        for (Employee e : emps) {
            DateFormatUtil.EmployeeDateFormat(e);
//            查询对应部门信息存入dept
            Dept dept = deptService.selectByDeptNo(e.getDeptno());
            e.setDept(dept);
//            查询对应的岗位信息 存入position
            Position position = positionService.selectByPosid(e.getPosid());
            e.setPosition(position);
        }
        session.setAttribute("employees", emps);

        return "empList";
    }

    public static final Integer PAGE_SIZE = 3;
    /**
     * 显示所有employee的信息（分页）
     *
     * @param
     * @return
     */
    @RequestMapping("showEmpList/{pageNum}")
    public String showEmpList(@PathVariable Integer pageNum,HttpSession session) throws ParseException {
//        对所有数据进行分页，按入职时间降序排列
        PageInfo pageInfo= employeeService.splitPage(pageNum,PAGE_SIZE);
        List<Employee> employees = pageInfo.getList();
        for (Employee e : employees) {
                DateFormatUtil.EmployeeDateFormat(e);
//            查询对应部门信息存入dept
                Dept dept = deptService.selectByDeptNo(e.getDeptno());
                e.setDept(dept);
//            查询对应的岗位信息 存入position
                Position position = positionService.selectByPosid(e.getPosid());
                e.setPosition(position);
            }
        //            总共多少条记录
        long total = pageInfo.getTotal();

//            总共多少页
        int pages = pageInfo.getPages();

//            当前第几页 pageNum

        session.setAttribute("employees", employees);
        session.setAttribute("total",total);
        session.setAttribute("pages",pages);
        session.setAttribute("pageNum",pageNum);

        List<Dept> depts = deptService.selectAllDepts();
        session.setAttribute("depts", depts);
        return "empList";

    }

    /*@RequestMapping("showEmpList/{pageNum}")
    public String splitPage(@PathVariable Integer pageNum,HttpSession session){

        List<Employee> employees = (List<Employee>) session.getAttribute("employees");

        System.out.println("获取到的集合"+employees);
        PageInfo pageInfo= employeeService.splitPage(employees,pageNum,PAGE_SIZE);

        long total = pageInfo.getTotal();
        List<Employee> employees1 = pageInfo.getList();
        session.setAttribute("employees",employees1);
        session.setAttribute("total",total);


        return "empList";
    }*/



}