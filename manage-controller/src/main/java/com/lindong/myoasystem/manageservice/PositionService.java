package com.lindong.myoasystem.manageservice;

import com.lindong.myoasystem.pojo.Dept;
import com.lindong.myoasystem.pojo.Position;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.util.List;

public interface PositionService {
    int positionAdd(Position position);

    Position selectByPosid(Integer posid);

    List<Position> selectAllPosition();

    int updatePosition(Position position);

    int deletePosition(Integer posid) throws MySQLIntegrityConstraintViolationException;
}
