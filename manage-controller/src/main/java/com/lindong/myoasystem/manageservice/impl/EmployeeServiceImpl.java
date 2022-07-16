package com.lindong.myoasystem.manageservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lindong.myoasystem.commons.DateFormatUtil;
import com.lindong.myoasystem.manageservice.EmployeeService;
import com.lindong.myoasystem.mapper.DeptMapper;
import com.lindong.myoasystem.mapper.EmployeeMapper;
import com.lindong.myoasystem.mapper.PositionMapper;
import com.lindong.myoasystem.pojo.Dept;
import com.lindong.myoasystem.pojo.Employee;
import com.lindong.myoasystem.pojo.EmployeeExample;
import com.lindong.myoasystem.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private PositionMapper positionMapper;
    @Override
    public Employee login(String empid, String password) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andEmpidEqualTo(empid)
                .andPasswordEqualTo(password);
        List<Employee> employees = employeeMapper.selectByExample(example);

        if (employees != null && employees.size()==1) {
            return employees.get(0);
        }

        return null;
    }

    @Override
    public List<Employee> selectAllEmployee() {
        return employeeMapper.selectByExample(null);
    }

    @Override
    public List<Employee> selectAllMgr() {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andEmptypeEqualTo(1);
        return employeeMapper.selectByExample(example);
    }

    @Override
    public int addEmployee(Employee emp) {
        return employeeMapper.insertSelective(emp);
    }

    /**
     * 返回的实例包含dept和position的信息
     * @param empid
     * @return
     */
    @Override
    public Employee selectByEmpid(String empid) {
        Employee employee = employeeMapper.selectByPrimaryKey(empid);

//        查部门
        Integer deptno = employee.getDeptno();
        Dept dept = deptMapper.selectByPrimaryKey(deptno);
        employee.setDept(dept);

//        查position
        Integer posid = employee.getPosid();
        Position position = positionMapper.selectByPrimaryKey(posid);
        employee.setPosition(position);

//        查上级
        String mgrid = employee.getMgrid();
        Employee mgr = employeeMapper.selectByPrimaryKey(mgrid);
        employee.setMgr(mgr);

        return employee;
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public int deleteEmployee(String empid) {
        return employeeMapper.deleteByPrimaryKey(empid);
    }

    @Override
    public int resetPassword(String empid) {
        Employee employee = new Employee();
        employee.setEmpid(empid);
        employee.setPassword("123456");

        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public List<Employee> mutiQueryLike(Employee emp) {

        EmployeeExample exp = new EmployeeExample();
        EmployeeExample.Criteria criteria = exp.createCriteria();
        criteria.andEmpidLike("%"+emp.getEmpid()+"%").andOndutyEqualTo(emp.getOnduty());

        if (emp.getDeptno()!=-1){
            criteria.andDeptnoEqualTo(emp.getDeptno());
        }
        //条件是在设定的日期之后
        try {
            DateFormatUtil.EmployeeDateFormat(emp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (emp.getHiredate()!=null){
            criteria.andHiredateGreaterThan(emp.getHiredate());
        }
        return employeeMapper.selectByExample(exp);
    }

    @Override
    public PageInfo splitPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        EmployeeExample exp = new EmployeeExample();
        exp.setOrderByClause("hiredate desc");
        List<Employee> employees = employeeMapper.selectByExample(exp);
        PageInfo<Employee> pageInfo = new PageInfo<>(employees);
        return pageInfo;
    }
}
