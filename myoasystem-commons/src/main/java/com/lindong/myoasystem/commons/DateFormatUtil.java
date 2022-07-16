package com.lindong.myoasystem.commons;

import com.lindong.myoasystem.pojo.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {


    public static void EmployeeDateFormat(Employee employee) throws ParseException {
//        将3种date转换为数据库中的date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String shiredate = employee.getShiredate();
        Date hiredate = employee.getHiredate();
        if (shiredate != null&&shiredate!="") {
            if (hiredate==null){
                employee.setHiredate(sdf.parse(shiredate));
            }
        }else {
            if (hiredate !=null){
                employee.setShiredate(sdf.format(hiredate));
            }
        }

        String sbirthdate = employee.getSbirthdate();
        Date birthdate = employee.getBirthdate();
        if (sbirthdate != null&&sbirthdate!="") {
            if (birthdate==null){
                employee.setBirthdate(sdf.parse(sbirthdate));
            }
        }else {
            if (birthdate!=null){
                employee.setSbirthdate(sdf.format(birthdate));
            }
        }

        String sleavedate = employee.getSleavedate();
        Date leavedate = employee.getLeavedate();
        if (sleavedate != null&&sleavedate!="") {
            if (leavedate==null){
                employee.setLeavedate(sdf.parse(sleavedate));
            }
        }else {
            if (leavedate!=null){
                employee.setSleavedate(sdf.format(leavedate));
            }
        }

    }
}
