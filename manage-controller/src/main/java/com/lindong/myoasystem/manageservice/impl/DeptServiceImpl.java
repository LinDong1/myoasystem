package com.lindong.myoasystem.manageservice.impl;

import com.lindong.myoasystem.manageservice.DeptService;
import com.lindong.myoasystem.mapper.DeptMapper;
import com.lindong.myoasystem.pojo.Dept;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;


    @Override
    public int deptAdd(Dept dept) {
       return deptMapper.insert(dept);
    }

    @Override
    public Dept selectByDeptNo(Integer deptno){
        return deptMapper.selectByPrimaryKey(deptno);
    }

    @Override
    public List<Dept> selectAllDepts() {
        return deptMapper.selectByExample(null);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateByPrimaryKey(dept);
    }

    @Override
    public int deleteDeptByDeptNo(Integer deptno) throws MySQLIntegrityConstraintViolationException {
        int i = 0;
        try {
            i = deptMapper.deleteByPrimaryKey(deptno);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MySQLIntegrityConstraintViolationException("有外键");
        }


        return i;
    }
}
