package com.longfor.longjian.measure.domain.externalService.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.longfor.gaia.gfs.data.mybatis.datasource.LFAssignDataSource;
import com.longfor.longjian.measure.dao.zhijian2.AreaMapper;
import com.longfor.longjian.measure.domain.externalService.IAreaService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.util.AreaUtils;
import com.longfor.longjian.measure.vo.AreaInfoWithExtendVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    AreaMapper areaMapper;

    @Override
    public Area getAreaByProjIdAndAreaId(Integer project_id, Integer area_id) {
        Area area = new Area();
        area.setId(area_id);
        area.setProjectId(project_id);
        area.setDeleteAt(null);
        return areaMapper.selectOne(area);
    }

    @Override
    public List<Map<String, Object>> getProMeasureAreaListByFatherId(String project_id, String area_id) {
        return areaMapper.selectByFatherId(project_id == null ? "" : project_id, area_id == null ? "" : area_id);
    }

    @Override
    public Area getAreaById(String areaId) {
        Example example = new Example(Area.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", areaId);
        criteria.andIsNull("deleteAt");
        return areaMapper.selectOneByExample(example);
    }

    @Override
    public List<Area> getAreaByIds(List<Integer> areaIds) {
        return areaMapper.getAreaByIds(areaIds);
    }

    @Override
    public List<Area> searchByIdList(Integer proj_id, List<Integer> areaIds) {
        Example example = new Example(Area.class);
        example.createCriteria().andEqualTo("projectId", proj_id).andIn("id", areaIds);
        return areaMapper.selectByExample(example);
    }

    @Override
    @LFAssignDataSource("zhijian2")
    public List<Area> selectByIds(Set<Integer> keySet) {
        Example example = new Example(Area.class);
        example.createCriteria().andIn("id", keySet);
        return areaMapper.selectByExample(example);
    }

    //@todo : 后续可以优化，暂时只有subs使用，考虑一次性取出所有数据，而不是遍历
    @Override
    public List<AreaInfoWithExtendVo> formatAreaInfoWithExtend(List<Area> areas) throws Exception {
        try {
            List<AreaInfoWithExtendVo> items = Lists.newArrayList();
            List<Integer> areaIds = Lists.newArrayList();
            areas.forEach(area -> {
                areaIds.add(area.getId());
            });
            this.createAreasMapByLeaveIds(areaIds);
            for (Area area : areas) {
                AreaInfoWithExtendVo item = new AreaInfoWithExtendVo();
                item.setArea(area);
                item.setPathNames(AreaUtils.getPathNames(area.getId()));
                Integer count = this.pGetSubsCount(area.getProjectId(), area.getId());
                if (count > 0) {
                    item.setParent(true);
                } else {
                    item.setParent(false);
                }
                items.add(item);
            }
            return items;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private Integer pGetSubsCount(Integer projectId, Integer id) {
        Example example = new Example(Area.class);
        example.createCriteria().andEqualTo("projectId", projectId).andEqualTo("fatherId", id);
        return areaMapper.selectCountByExample(example);
    }

    private void createAreasMapByLeaveIds(List<Integer> areaIds) throws Exception {
        try {
            List<Area> areas = this.selectAllByLeaveIds(areaIds);
            this.createAreasMapByAreaList(areas);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void createAreasMapByAreaList(List<Area> areas) {
        Map<Integer, Area> areaMap = AreaUtils.getAreas();
        areas.forEach(area -> {
            areaMap.put(area.getId(),area);
        });
        AreaUtils.setList(areas);
    }

    private List<Area> selectAllByLeaveIds(List<Integer> areaIds) {
        List<Area> areas = null;
        try {
            areas = this.selectByIds(new HashSet<>(areaIds));
        } catch (Exception e) {
            return null;
        }
        List<Integer> totalIds = Lists.newArrayList();
        areas.forEach(area -> {
            totalIds.add(area.getId());
            List<Integer> sids = Arrays.asList(StringUtils.split(area.getPath(), "/")).stream().map(Integer::parseInt).collect(Collectors.toList());
            totalIds.addAll(sids);
        });
        //列表去重
        Set<Integer> idSet = Sets.newHashSet();
        idSet.addAll(totalIds);
        return this.selectByIds(idSet);
    }

}
