package com.ghh.sys.service;

import com.ghh.sys.bean.Position;
import com.ghh.sys.mapper.PositionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionMapper positionMapper;

    @Override
    public List<Position> getJesonList() {

        return positionMapper.selectByExample(null);
    }
}
