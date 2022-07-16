package com.lindong.myoasystem.managecontroller;

import com.lindong.myoasystem.manageservice.PositionService;
import com.lindong.myoasystem.pojo.Position;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping("goPositionAdd")
    @ResponseBody
    public String positionAdd(Position position){
        int i = positionService.positionAdd(position);
        if (i==1){
            return "添加成功";
        }
        return "添加失败";
    }

    @RequestMapping("checkPosid")
    @ResponseBody
    public String checkPosid(Integer posid){
        System.out.println(posid);
        Position position = positionService.selectByPosid(posid);
        if (position==null){
            return "可以使用";
        }
        return "该id已存在，请更换一个id";
    }

    @RequestMapping("showPositionList")
    public String showPositionList(HttpSession session){
        List<Position> positions=positionService.selectAllPosition();
        session.setAttribute("positions",positions);
        return "positionList";
    }

    @RequestMapping("updatePosition")
    @ResponseBody
    public String updatePosition(Position position){
        int n = positionService.updatePosition(position);
        if (n==1){
            return "修改成功";
        }
        return "修改失败";
    }

    @RequestMapping("deletePosition")
    @ResponseBody
    public String deletePosition(Integer posid){
        int n=0;
        try {
            n = positionService.deletePosition(posid);
        }catch (MySQLIntegrityConstraintViolationException exception){
            exception.printStackTrace();
            return "当前职位下有员工,不能删除,如果要删除,必须先删除此职位下所有的员工";
        }

        if (n==1){
            return "删除成功";
        }
        return "删除失败";
    }

}
