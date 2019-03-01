package com.longfor.longjian.measure.domain.externalservice.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.dao.zhijian2.*;
import com.longfor.longjian.measure.domain.externalservice.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.DateUtil;
import com.longfor.longjian.measure.vo.ConditionSearchVo;
import com.longfor.longjian.measure.vo.CreateMeasureListVo;
import com.longfor.longjian.measure.vo.CreateZoneFromAppVo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
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
    public List<Map<String, Object>> getMeasureList(Map<String,Object> map) {
        return measureListMapper.getMeasureList(map);
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
    public void createZoneFromApp(CreateZoneFromAppVo vo) {
        MeasureZone measureZone = new MeasureZone();
        measureZone.setProjectId(vo.getProject_id());
        measureZone.setListId(vo.getList_id());
        measureZone.setUuid(vo.getUuid());
        measureZone.setRegionUuid(vo.getUuid1());
        measureZone.setAreaId(vo.getAreaId());
        measureZone.setAreaPathAndId(vo.getAreaPathAndId());
        measureZone.setCategoryKey(vo.getCategory_key());
        measureZone.setCategoryPathAndKey(vo.getS());
        measureZone.setFinishStatus(vo.getId());
        measureZone.setCloseStatus(vo.getId1());
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
    public MeasureList getByProjIdAndIdNoFoundErr(Integer projectId, Integer id) {
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projectId).andEqualTo("id", id);
        return measureListMapper.selectOneByExample(example);
    }

    @Override
    public Map<String, Object> conditionSearch(ConditionSearchVo vo) throws LjBaseRuntimeException {
        List<Integer> list_id_set = Lists.newArrayList();
        List<Integer> userIdList = Lists.newArrayList();
        List<Integer> list_id_set2 = Lists.newArrayList();
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, vo.getProject_id());
        if (vo.getArea_id()!= null && !vo.getArea_id().equals("")) {
            Example example1 = new Example(MeasureListArea.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo(PROJECTID, vo.getProject_id()).andEqualTo("areaId", vo.getArea_id());
            List<MeasureListArea> measureListAreas = measureListAreaMapper.selectByExample(example1);
            measureListAreas.forEach(measureListArea -> {
                list_id_set.add(measureListArea.getListId());
            });
        }
        if (vo.getName() != null && !vo.getName().equals("")) {
            criteria.andEqualTo("name", vo.getName());
        }
        if (vo.getUser_id_list().length() > 0) {
            for (String user_id : StringUtils.split(vo.getUser_id_list(), ",")) {
                userIdList.add(Integer.parseInt(user_id));
            }
            Example example2 = new Example(MeasureSquadUser.class);
            Example.Criteria criteria1 = example2.createCriteria();
            criteria1.andEqualTo(PROJECTID, vo.getProject_id()).andIn("id", userIdList);
            List<MeasureSquadUser> measureSquadUsers = measureSquadUserMapper.selectByExample(example2);
            measureSquadUsers.forEach(measureSquadUser -> {
                if (list_id_set.contains(measureSquadUser.getUserId())) {
                    list_id_set2.add(measureSquadUser.getUserId());
                }
            });
        }
        if (vo.getFinish_status() != null) {
            criteria.andEqualTo("finishStatus", vo.getFinish_status());
        }
        if (vo.getCategory_key() != null && !vo.getCategory_key().equals("")) {
            criteria.andEqualTo("rootCategoryKey", vo.getCategory_key());
        }
        if (!list_id_set.isEmpty()) {
            criteria.andIn("id", list_id_set);
        }
        criteria.andIsNull(DELETEAT);
        example.setOrderByClause("create_at");
        List<MeasureList> measureLists = measureListMapper.selectByExampleAndRowBounds(example,new RowBounds((vo.getPage() - 1) * vo.getPage_size(),vo.getPage_size()));
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
            example4.createCriteria().andEqualTo(LISTID,list_model.getId()).andEqualTo(PROJECTID,vo.getProject_id()).andIsNull(DELETEAT);
            int issue_count = measureListIssueMapper.selectCountByExample(example4);
            map.put("issue_count",issue_count);
            List<String> key_list = Lists.newArrayList();
            key_list.add(list_model.getRootCategoryKey());
            List<CategoryV3> categoryV3s = this.searchbykeylist(vo.getGroup_id(), key_list);
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
                List<Area> area_info_list = search_by_id_list(vo.getProject_id(), area_id_list);
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
    public MeasureList createMeasureList(CreateMeasureListVo vo) {
        MeasureList measureList = new MeasureList();
        measureList.setProjectId(vo.getProj_id());
        measureList.setName(vo.getName());
        measureList.setAreaType(vo.getArea_type());
        measureList.setCloseStatus(vo.getId());
        measureList.setFinishStatus(vo.getId1());
        measureList.setRootCategoryKey(vo.getRoot_category_key());
        measureList.setPlanBeginOn(DateUtil.getDateShortFromString(vo.getPlan_begin_on()));
        measureList.setPlanEndOn(DateUtil.getDateShortFromString(vo.getPlan_end_on()));
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

    public static Map<String, Object> objectToMap(Object obj) throws IntrospectionException ,IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
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
