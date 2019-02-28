package com.longfor.longjian.measure.domain.externalservice.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.dao.zhijian2.*;
import com.longfor.longjian.measure.domain.externalservice.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class MeasureListServiceImpl implements IMeasureListService {

    @Autowired
    private MeasureListMapper measureListMapper;
    @Autowired
    private MeasureZoneMapper measureZoneMapper;
    @Autowired
    private MeasureSquadUserMapper measureSquadUserMapper;
    @Resource
    private MeasureSquadMapper measureSquadMapper;
    @Resource
    private MeasureListAreaMapper measureListAreaMapper;
    @Resource
    private MeasureListIssueMapper measureListIssueMapper;
    @Resource
    private CategoryV3Mapper categoryV3Mapper;
    @Resource
    private AreaMapper areaMapper;
    private static final String PROJECTID = "projectId";
    private static final String DELETEAT = "deleteAt";
    private static final String LISTID = "listId";
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
    public MeasureList getNoProjNoFoundErr(String listId) {
        return measureListMapper.getNoProjNoFoundErr(listId);
    }

    @Override
    public List<MeasureList> searchListByProjIdUserId(String projectId, Integer userId) {
        return measureListMapper.searchListByProjIdUserId(projectId, userId);
    }

    @Override
    public List<MeasureZone> searchZoneByMeasureListIdRegionUuidCategoryKey(Integer project_id, Integer list_id, String uuid, String category_key) {
        return measureZoneMapper.searchByCondition(project_id, list_id, uuid, category_key);
    }

    @Override
    public List<MeasureZone> getZoneByUuid(Integer project_id, String uuid) {
        Example example = new Example(MeasureZone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, project_id);
        criteria.andEqualTo("uuid",uuid);
        criteria.andIsNull(DELETEAT);
        return measureZoneMapper.selectByExample(example);
    }

    @Override
    public void createZoneFromApp(Integer project_id, Integer list_id, String uuid, String regionUuid, Integer areaId, String areaPathAndId, String category_key, String categoryPathAndKey, Integer finishId, Integer closeId) {
        MeasureZone measureZone = new MeasureZone();
        measureZone.setProjectId(project_id);
        measureZone.setListId(list_id);
        measureZone.setUuid(uuid);
        measureZone.setRegionUuid(regionUuid);
        measureZone.setAreaId(areaId);
        measureZone.setAreaPathAndId(areaPathAndId);
        measureZone.setCategoryKey(category_key);
        measureZone.setCategoryPathAndKey(categoryPathAndKey);
        measureZone.setFinishStatus(finishId);
        measureZone.setCloseStatus(closeId);
        measureZone.setCreateAt(new Date());
        measureZone.setUpdateAt(new Date());
        measureZoneMapper.insert(measureZone);
    }

    @Override
    public List<MeasureSquadUser> searchMeasureSquadUserByListIds(Integer currentProjectId, Set<Integer> listIds) {
        return measureSquadUserMapper.searchMeasureSquadUserByListIds(currentProjectId, listIds);
    }

    @Override
    public int updateMeasureList(MeasureList measureList) {
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", measureList.getId());
        criteria.andIsNull(DELETEAT);
        return measureListMapper.updateByExampleSelective(measureList, example);
    }

    @Override
    public void delete(Integer id) {
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);

        MeasureList measureList = new MeasureList();
        measureList.setId(id);
        measureList.setUpdateAt(new Date());
        measureList.setDeleteAt(new Date());

        measureListMapper.updateByExampleSelective(measureList, example);
    }

    @Override
    public void updateFinishStatus(Map<String, Object> map) {
        measureListMapper.updateFinishStatus(map);
    }

    @Override
    public MeasureList getMeasureListByProjIdAndId(Integer projId, Integer Id) {
        return measureListMapper.getMeasureListByProjIdAndId(projId, Id);
    }

    @Override
    public void updateCloseStatus(Map<String, Object> map) {
        measureListMapper.updateCloseStatus(map);
    }

    @Override
    public List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer projId, Integer list_id) {
        Example example = new Example(MeasureSquad.class);
        example.createCriteria().andEqualTo(PROJECTID, projId).andEqualTo(LISTID, list_id);
        return measureSquadMapper.selectByExample(example);
    }

    @Override
    public MeasureList getNoFoundErr(Integer projId, Integer list_id) {
        Example example = new Example(MeasureList.class);
        example.createCriteria().andEqualTo(PROJECTID, projId).andEqualTo("id", list_id);
        return measureListMapper.selectOneByExample(example);
    }

    @Override
    public List<MeasureZone> searchZoneByProjUuids(Integer projectId, Set<String> keySet) {
        Example example = new Example(MeasureZone.class);
        example.createCriteria().andEqualTo(PROJECTID, projectId).andIn("uuid", keySet);
        return measureZoneMapper.selectByExample(example);
    }

    @Override
    public List<MeasureSquad> searchByProjIdIdIn(Integer projId, Set<Integer> keySet) {
        Example example = new Example(MeasureSquad.class);
        example.createCriteria().andEqualTo(PROJECTID, projId).andIn("id", keySet);
        return measureSquadMapper.selectByExample(example);
    }

    @Override
    public MeasureList GetByProjIdAndIdNoFoundErr(Integer projectId, Integer id) {
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projectId).andEqualTo("id", id);
        return measureListMapper.selectOneByExample(example);
    }

    @Override
    public Map<String, Object> conditionSearch(Integer group_id, Integer project_id, Integer page, Integer page_size, String area_id, String user_id_list, Integer finish_status, String name, String category_key) throws LjBaseRuntimeException {
        List<Integer> list_id_set = Lists.newArrayList();
        List<Integer> userIdList = Lists.newArrayList();
        List<Integer> list_id_set2 = Lists.newArrayList();
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, project_id);
        if (area_id != null && !area_id.equals("")) {
            Example example1 = new Example(MeasureListArea.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo(PROJECTID, project_id).andEqualTo("areaId", area_id);
            List<MeasureListArea> measureListAreas = measureListAreaMapper.selectByExample(example1);
            measureListAreas.forEach(measureListArea -> {
                list_id_set.add(measureListArea.getListId());
            });
        }
        if (name != null && !name.equals("")) {
            criteria.andEqualTo("name", name);
        }
        if (user_id_list.length() > 0) {
            for (String user_id : StringUtils.split(user_id_list, ",")) {
                userIdList.add(Integer.parseInt(user_id));
            }
            Example example2 = new Example(MeasureSquadUser.class);
            Example.Criteria criteria1 = example2.createCriteria();
            criteria1.andEqualTo(PROJECTID, project_id).andIn("id", userIdList);
            List<MeasureSquadUser> measureSquadUsers = measureSquadUserMapper.selectByExample(example2);
            measureSquadUsers.forEach(measureSquadUser -> {
                if (list_id_set.contains(measureSquadUser.getUserId())) {
                    list_id_set2.add(measureSquadUser.getUserId());
                }
            });
        }
        if (finish_status != null) {
            criteria.andEqualTo("finishStatus", finish_status);
        }
        if (category_key != null && !category_key.equals("")) {
            criteria.andEqualTo("rootCategoryKey", category_key);
        }
        if (!list_id_set.isEmpty()) {
            criteria.andIn("id", list_id_set);
        }
        criteria.andIsNull(DELETEAT);
        example.setOrderByClause("create_at");
        List<MeasureList> measureLists = measureListMapper.selectByExampleAndRowBounds(example,new RowBounds((page - 1) * page_size,page_size));
        //List<measurelist> measureLists = measureListMapper.selectByExample(example);
        //PageHelper.startPage((page - 1) * page_size,page_size);
        int total_num = measureListMapper.selectCountByExample(example);
        List<Map<String,Object>> return_list =Lists.newArrayList();
        for (MeasureList list_model : measureLists) {
            Map<String, Object> map = null;
            try {
                map = objectToMap(list_model);
            } catch (Exception e) {
               throw new LjBaseRuntimeException(-9999,e+"");
            }
            Example example4 =new Example(MeasureListIssue.class);
            example4.createCriteria().andEqualTo(LISTID,list_model.getId()).andEqualTo(PROJECTID,project_id).andIsNull(DELETEAT);
            int issue_count = measureListIssueMapper.selectCountByExample(example4);
            map.put("issue_count",issue_count);
            List<String> key_list = Lists.newArrayList();
            key_list.add(list_model.getRootCategoryKey());
            List<CategoryV3> categoryV3s = this.searchbykeylist(group_id, key_list);
            Example example5 = new Example(MeasureListArea.class);
            Example.Criteria criteria1 = example5.createCriteria();
            //todo 源码上的为'^/[1-9][0-9]*/$' 但是匹配不到三级目录
            criteria1.andEqualTo(LISTID,list_model.getId()).andCondition("area_path_and_id  REGEXP '^/[1-9][0-9]*/[1-9][0-9]*/$'");
            List<MeasureListArea> area_list = measureListAreaMapper.selectByExample(example5);
            Set<Integer> area_id_list = Sets.newHashSet();
            List<String> top_areas =Lists.newArrayList();
            if(area_list.size()>0){
                area_list.forEach(measureListArea->{
                    area_id_list.add(measureListArea.getAreaId());
                });
                List<Area> area_info_list = search_by_id_list(project_id, area_id_list);
                area_info_list.forEach(area -> {
                    top_areas.add(area.getName());
                });
            }
            map.put("top_areas",top_areas);
            map.put("root_category_name",categoryV3s.size()>0 ? categoryV3s.get(0).getName():new CategoryV3().getName());
            return_list.add(map);
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("total_num",total_num);
        map.put("return_list",return_list);
        return map;
    }

    @Override
    public MeasureList createMeasureList(int proj_id, String name, String area_type, Integer closeStatusCode, Integer finishStatusCode, String root_category_key, String plan_begin_on, String plan_end_on) {
        MeasureList measureList = new MeasureList();
        measureList.setProjectId(proj_id);
        measureList.setName(name);
        measureList.setAreaType(area_type);
        measureList.setCloseStatus(closeStatusCode);
        measureList.setFinishStatus(closeStatusCode);
        measureList.setRootCategoryKey(root_category_key);
        measureList.setPlanBeginOn(DateUtil.getDateShortFromString(plan_begin_on));
        measureList.setPlanEndOn(DateUtil.getDateShortFromString(plan_end_on));
        measureList.setCreateAt(new Date());
        measureList.setUpdateAt(new Date());
        measureListMapper.insertSelective(measureList);
        return measureList;
    }

    private List<Area> search_by_id_list(Integer project_id, Set<Integer> area_id_list) {
        Example example =new Example(Area.class);
        example.createCriteria().andEqualTo(PROJECTID,project_id).andIn("id",area_id_list);
        return areaMapper.selectByExample(example);
    }

    private List<CategoryV3> searchbykeylist(Integer group_id, List<String> key_list) {
        Example example=new Example(CategoryV3.class);
        example.createCriteria().andEqualTo("teamId",group_id).andIn("key", key_list);
        return  categoryV3Mapper.selectByExample(example);
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }
}
