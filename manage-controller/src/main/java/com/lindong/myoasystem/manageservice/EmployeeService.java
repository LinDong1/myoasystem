package com.lindong.myoasystem.manageservice;

import com.github.pagehelper.PageInfo;
import com.lindong.myoasystem.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    /**
     * 用户（Employee）的登录
     * @param empid
     * @param password
     * @return
     */
    Employee login(String empid, String password);


    List<Employee> selectAllEmployee();

    List<Employee> selectAllMgr();

    int addEmployee(Employee emp);

    Employee selectByEmpid(String empid);

    int updateEmployee(Employee employee);

    int deleteEmployee(String empid);

    int resetPassword(String empid);

    List<Employee> mutiQueryLike(Employee emp);

    PageInfo splitPage(Integer pageNum, Integer pageSize);
}
