package com.longfor.longjian.measure.domain.externalservice.impl;

import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2.MeasureTagMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureTagService;
import com.longfor.longjian.measure.po.zhijian2.MeasureTag;
import com.longfor.longjian.measure.vo.EditTagProtoVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * wangxs
 * 2018-11-22
 */
@Service
public class MeasureTagServiceImpl implements IMeasureTagService {

    @Resource
    private MeasureTagMapper measureTagMapper;

    @Override
    public List<Map<String, Object>> searchByGroupId(Integer group_id, Integer ownership) {
        //暂时不需要用page_level判断
        return measureTagMapper.searchByGroupId(group_id, ownership);
    }

    @Override
    public List<Map<String, Object>> searchByGroupIdAndProjId(Integer group_id, Integer proj_id, Integer ownership) {
        return measureTagMapper.searchByGroupIdAndProjId(group_id, proj_id, ownership);
    }

    @Override
    @LFAssignDataSource("zhijian2")
    @Transactional(rollbackFor = Exception.class)
    public Integer editOnGroup(Integer group_id, List<EditTagProtoVo> editTagProtoVos, Integer ownership) {
        Integer count=0;
        for (EditTagProtoVo editTagProtoVo : editTagProtoVos) {
            Date date =new Date();
            Integer tagId = editTagProtoVo.getTagId();
            count += measureTagMapper.updateByIdAndOwnership(group_id, tagId,editTagProtoVo.getName(),ownership,date);
        }
        return count;
    }

    @Override
    @LFAssignDataSource("zhijian2")
    @Transactional(rollbackFor = Exception.class)
    public Integer addOnGroup(Integer group_id, List<String> nameList, Integer ownership,Integer proj_id) {
        Integer count=0;
        for (String name : nameList) {
            Date date =new Date();
            MeasureTag measureTag =new MeasureTag();
            measureTag.setGroupId(group_id);
            measureTag.setName(name);
            measureTag.setProjId(0);
            measureTag.setOwnership(ownership);
            measureTag.setCreateAt(date);
            measureTag.setUpdateAt(date);
            count += measureTagMapper.insertSelective(measureTag);
        }
        return count;
    }

    @Override
    @LFAssignDataSource("zhijian2")
    @Transactional(rollbackFor = Exception.class)
    public Integer addOnProj(Integer group_id, Integer project_id, List<String> nameList, Integer ownership) {
        Integer count=0;
        for (String name : nameList) {
            Date date =new Date();
            MeasureTag measureTag =new MeasureTag();
            measureTag.setGroupId(group_id);
            measureTag.setProjId(project_id);
            measureTag.setName(name);
            measureTag.setOwnership(ownership);
            measureTag.setCreateAt(date);
            measureTag.setUpdateAt(date);
            count += measureTagMapper.insertSelective(measureTag);
        }
        return count;
    }

    @Override
    public Integer editOnProjId(Integer group_id, Integer project_id, List<EditTagProtoVo> editTagProtoVos, Integer ownership) {
        Integer count=0;
        for (EditTagProtoVo editTagProtoVo : editTagProtoVos) {
            Date date =new Date();
            Integer tagId = editTagProtoVo.getTagId();
            String name = editTagProtoVo.getName();
            count += measureTagMapper.updateByProjectIdAndOwnership(group_id,project_id,tagId,name, ownership,date);
        }
        return count;
    }
}
