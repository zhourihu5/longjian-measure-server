package com.longfor.longjian.measure.domain.externalservice.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.dao.zhijian2.*;
import com.longfor.longjian.measure.domain.externalservice.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.DateUtil;
import com.longfor.longjian.measure.util.ExampleUtil;
import com.longfor.longjian.measure.vo.ConditionSearchVo;
import com.longfor.longjian.measure.vo.CreateMeasureListVo;
import com.longfor.longjian.measure.vo.CreateZoneFromAppVo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
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

    @Resource
    private MeasureListMapper measureListMapper;
    @Resource
    private MeasureZoneMapper measureZoneMapper;
    @Resource
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
    public Integer getTotalMeasure(Integer finishStatus, String q, Integer projectId, String categoryPathAndKey, String areaPathAndId, String[] userIds) {
        return measureListMapper.getTotalMeasure(finishStatus, q, projectId, categoryPathAndKey, areaPathAndId, userIds);
    }

    @Override
    public List<Map<String, Object>> searchByProjectId(Integer projectId) {
        return measureListMapper.searchByProjectId(projectId);
    }

    @Override
    public MeasureList searchByProjectIdAndMeasureListId(Integer projectId, Integer measureListId) {
        MeasureList measureList = new MeasureList();
        measureList.setId(measureListId);
        measureList.setProjectId(projectId);
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
    public List<MeasureZone> searchZoneByMeasureListIdRegionUuidCategoryKey(Integer projectId, Integer listId, String uuid, String categoryKey) {
        return measureZoneMapper.searchByCondition(projectId, listId, uuid, categoryKey);
    }

    @Override
    public List<MeasureZone> getZoneByUuid(Integer projectId, String uuid) {
        Example example = new Example(MeasureZone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projectId);
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
        //客户端添加
        measureZone.setSrcType(2);
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
    public MeasureList getMeasureListByProjIdAndId(Integer projId, Integer id) {
        return measureListMapper.getMeasureListByProjIdAndId(projId, id);
    }

    @Override
    public void updateCloseStatus(Map<String, Object> map) {
        measureListMapper.updateCloseStatus(map);
    }

    @Override
    public List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer projId, Integer listId) {
        Example example = new Example(MeasureSquad.class);
        example.createCriteria().andEqualTo(PROJECTID, projId).andEqualTo(LISTID, listId);
        ExampleUtil.addDeleteAtJudge(example);
        return measureSquadMapper.selectByExample(example);
    }

    @Override
    public MeasureList getNoFoundErr(Integer projId, Integer listId) {
        Example example = new Example(MeasureList.class);
        example.createCriteria().andEqualTo(PROJECTID, projId).andEqualTo("id", listId);
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
    @SuppressWarnings("squid:S3776")
    public Map<String, Object> conditionSearch(ConditionSearchVo vo) throws LjBaseRuntimeException {
        List<Integer> listIdSet = Lists.newArrayList();
        List<Integer> userIdList = Lists.newArrayList();
        List<Integer> listIdSet2 = Lists.newArrayList();
        Example example = new Example(MeasureList.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, vo.getProject_id());
        if (vo.getArea_id()!= null && !vo.getArea_id().equals("")) {
            Example example1 = new Example(MeasureListArea.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo(PROJECTID, vo.getProject_id()).andEqualTo("areaId", vo.getArea_id());
            List<MeasureListArea> measureListAreas = measureListAreaMapper.selectByExample(example1);
            measureListAreas.forEach(measureListArea -> listIdSet.add(measureListArea.getListId()));
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
                if (listIdSet.contains(measureSquadUser.getUserId())) {
                    listIdSet2.add(measureSquadUser.getUserId());
                }
            });
        }
        if (vo.getFinish_status() != null) {
            criteria.andEqualTo("finishStatus", vo.getFinish_status());
        }
        if (vo.getCategory_key() != null && !vo.getCategory_key().equals("")) {
            criteria.andEqualTo("rootCategoryKey", vo.getCategory_key());
        }
        if (!listIdSet.isEmpty()) {
            criteria.andIn("id", listIdSet);
        }
        criteria.andIsNull(DELETEAT);
        example.setOrderByClause("create_at");
        List<MeasureList> measureLists = measureListMapper.selectByExampleAndRowBounds(example,new RowBounds((vo.getPage() - 1) * vo.getPage_size(),vo.getPage_size()));
        int totalNum = measureListMapper.selectCountByExample(example);
        List<Map<String,Object>> returnList =Lists.newArrayList();
        for (MeasureList list_model : measureLists) {
            Map<String, Object> map = null;
            try {
                map = objectToMap(list_model);
            } catch (Exception e) {
               throw new LjBaseRuntimeException(-9999,e+"");
            }
            Example example4 =new Example(MeasureListIssue.class);
            example4.createCriteria().andEqualTo(LISTID,list_model.getId()).andEqualTo(PROJECTID,vo.getProject_id()).andIsNull(DELETEAT);
            int issueCount = measureListIssueMapper.selectCountByExample(example4);
            map.put("issue_count",issueCount);
            List<String> keyList = Lists.newArrayList();
            keyList.add(list_model.getRootCategoryKey());
            List<CategoryV3> categoryV3s = this.searchbykeylist(vo.getGroup_id(), keyList);
            Example example5 = new Example(MeasureListArea.class);
            Example.Criteria criteria1 = example5.createCriteria();
            criteria1.andEqualTo(LISTID,list_model.getId()).andCondition("area_path_and_id  REGEXP '^/[1-9][0-9]*/$'");
            List<MeasureListArea> areaList = measureListAreaMapper.selectByExample(example5);
            Set<Integer> areaIdList = Sets.newHashSet();
            List<String> topAreas =Lists.newArrayList();
            if(!areaList.isEmpty()){
                areaList.forEach(measureListArea-> areaIdList.add(measureListArea.getAreaId()));
                List<Area> areaInfoList = searchByIdList(vo.getProject_id(), areaIdList);
                areaInfoList.forEach(area -> topAreas.add(area.getName()));
            }
            map.put("top_areas",topAreas);
            map.put("root_category_name",!categoryV3s.isEmpty() ? categoryV3s.get(0).getName():new CategoryV3().getName());
            returnList.add(map);
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("total_num",totalNum);
        map.put("return_list",returnList);
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

    private List<Area> searchByIdList(Integer projectId, Set<Integer> areaIdList) {
        Example example =new Example(Area.class);
        example.createCriteria().andEqualTo(PROJECTID,projectId).andIn("id",areaIdList);
        return areaMapper.selectByExample(example);
    }

    private List<CategoryV3> searchbykeylist(Integer groupId, List<String> keyList) {
        Example example=new Example(CategoryV3.class);
        example.createCriteria().andEqualTo("teamId",groupId).andIn("key", keyList);
        return  categoryV3Mapper.selectByExample(example);
    }

    public static Map<String, Object> objectToMap(Object obj) throws IntrospectionException,
            InvocationTargetException, IllegalAccessException {
        if (obj == null)
            return null;
        Map<String, Object> map = new HashMap<>();

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
