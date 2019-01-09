package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListAreaMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListAreaService;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureListArea;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

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

        Example example = new Example(MeasureListArea.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("listId",id);

        MeasureListArea measureListArea=new MeasureListArea();
        measureListArea.setListId(id);
        measureListArea.setDeleteAt(new Date());

        measureListAreaMapper.updateByExampleSelective(measureListArea,example);
    }
}
