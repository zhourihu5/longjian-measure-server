package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureTagMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * wangxs
 * 2018-11-22
 */
@Service
public class MeasureTagServiceImpl implements IMeasureTagService {

    @Autowired
    private MeasureTagMapper measureTagMapper;

    @Override
    public List<Map<String, Object>> searchByGroupId(Integer group_id, Integer ownership) {
        //暂时不需要用page_level判断
        return measureTagMapper.searchByGroupId(group_id,ownership);
    }

    @Override
    public List<Map<String, Object>> searchByGroupIdAndProjId(Integer group_id, Integer proj_id, Integer ownership) {
        return measureTagMapper.searchByGroupIdAndProjId(group_id,proj_id,ownership);
    }
}
