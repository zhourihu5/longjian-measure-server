package com.longfor.longjian.measure.domain.externalService.impl;

import com.longfor.longjian.measure.dao.zhijian2.MeasureListMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MeasureListServiceImpl implements IMeasureListService {

    @Autowired
    private MeasureListMapper measureListMapper;

    @Override
    public List<Map<String, Object>> getMeasureList(Integer finish_status, String q, Integer project_id, String categoryPathAndKey, String areaPathAndId, String[] userIds, Integer page, Integer page_size) {
        page = page - 1;
        return measureListMapper.getMeasureList(finish_status, q, project_id, categoryPathAndKey, areaPathAndId, userIds, page, page_size);
    }

    @Override
    public Integer getTotalMeasure(Integer finish_status, String q, Integer project_id, String categoryPathAndKey, String areaPathAndId, String[] userIds) {
        return measureListMapper.getTotalMeasure(finish_status, q, project_id, categoryPathAndKey, areaPathAndId, userIds);
    }

    @Override
    public List<Map<String, Object>> searchByProjectId(Integer project_id) {
        return measureListMapper.searchByProjectId(project_id);
    }

    @Override
    public MeasureList searchByProjectIdAndMeasureListId(Integer project_id, Integer measure_list_id) {
        MeasureList measureList = new MeasureList();
        measureList.setId(measure_list_id);
        measureList.setProjectId(project_id);
        measureList.setDeleteAt(null);
        return measureListMapper.selectOne(measureList);
    }


    @Override
    public List<Map<String, Object>> searchListByProjIdUserId(String project_id, Integer userId) {
        //todo userId暂不知道用途
        List<Map<String, Object>> measureList = measureListMapper.searchListByProjId(project_id);

        return measureList;
    }

    @Override
    public List<MeasureList> getNoProjNoFoundErr(String listId) {
        return measureListMapper.getNoProjNoFoundErr(listId);
    }
}
