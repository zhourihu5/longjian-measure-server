package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListAreaMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Wang on 2019/1/7.
 */
@Slf4j
@Service
public class IMeasureListAreaServiceImpl implements IMeasureListAreaService {

    @Autowired
    private MeasureListAreaMapper measureListAreaMapper;

    @Override
    public void delete(Integer id) {
        measureListAreaMapper.deleteByPrimaryKey(id);
    }
}
