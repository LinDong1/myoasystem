package com.lindong.myoasystem.manageservice.impl;

import com.lindong.myoasystem.manageservice.PositionService;
import com.lindong.myoasystem.mapper.PositionMapper;
import com.lindong.myoasystem.pojo.Position;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;


    @Override
    public int positionAdd(Position position) {

        return positionMapper.insert(position);
    }

    @Override
    public Position selectByPosid(Integer posid) {
        return positionMapper.selectByPrimaryKey(posid);
    }

    @Override
    public List<Position> selectAllPosition() {
        return positionMapper.selectByExample(null);
    }

    @Override
    public int updatePosition(Position position) {
        return positionMapper.updateByPrimaryKey(position);
    }

    @Override
    public int deletePosition(Integer posid) throws MySQLIntegrityConstraintViolationException {
        int i = 0;
        try {
            i = positionMapper.deleteByPrimaryKey(posid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MySQLIntegrityConstraintViolationException("有外键");
        }


        return i;

    }
}
