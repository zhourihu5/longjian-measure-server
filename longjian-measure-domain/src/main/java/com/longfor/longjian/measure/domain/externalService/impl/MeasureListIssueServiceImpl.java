package com.longfor.longjian.measure.domain.externalService.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.longfor.longjian.measure.consts.Enum.MeasureListCloseStatusEnum;
import com.longfor.longjian.measure.consts.constant.MeasureListIssueType;
import com.longfor.longjian.measure.dao.zhijian2.*;
import com.longfor.longjian.measure.dao.zhijian2_apisvr.UserMapper;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2_apisvr.User;
import com.longfor.longjian.measure.util.AreaUtils;
import com.longfor.longjian.measure.util.CategoryUtils;
import com.longfor.longjian.measure.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class MeasureListIssueServiceImpl implements IMeasureListIssueService {
    @Resource
    private MeasureListIssueMapper measureListIssueMapper;
    @Resource
    private MeasureZoneMapper measureZoneMapper;
    @Resource
    private AreaMapper areaMapper;

    @Resource
    private CategoryV3Mapper categoryV3Mapper;

    @Resource
    private MeasureListMapper measureListMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public Integer countByMeasureListId(String id) {
        MeasureListIssue measureListIssue = new MeasureListIssue();
        measureListIssue.setListId(Integer.parseInt(id));
        return measureListIssueMapper.selectCount(measureListIssue);
    }

    @Override
    public Map<String, Object> getMeasureListIssueBrief(Integer project_id, Integer measure_list_id, String UNCLOSECODE, String REPAIRABLE, String NOREPAIRABLE, String NOTENOASSIGN, String ASSIGNNOREFORM, String REFORMNOCHECK, String CHECKYES) {
        Map<String, Object> map = new HashMap<>();
        int zoneCount = measureListIssueMapper.getZoneCount(project_id, measure_list_id, UNCLOSECODE);
        map.put("zone_count", zoneCount);
        map.put("repairable_count", measureListIssueMapper.getCountByTyp(project_id, measure_list_id, REPAIRABLE));
        map.put("unrepairable_count", measureListIssueMapper.getCountByTyp(project_id, measure_list_id, NOREPAIRABLE));
        map.put("note_no_assign", measureListIssueMapper.getCountByStatus(project_id, measure_list_id, NOTENOASSIGN));
        map.put("assign_no_reform", measureListIssueMapper.getCountByStatus(project_id, measure_list_id, ASSIGNNOREFORM));
        map.put("reform_no_check", measureListIssueMapper.getCountByStatus(project_id, measure_list_id, REFORMNOCHECK));
        map.put("check_yes", measureListIssueMapper.getCountByStatus(project_id, measure_list_id, CHECKYES));
        //查找所有测区
        int count = measureZoneMapper.searchTotalByProjectIdAndMeasureListId(project_id, new int[]{measure_list_id});
        String zonePercentage = String.format("%.2f", (double) zoneCount / count);
        if ("0.00".equals(zonePercentage) || zoneCount == 0 || count == 0) {
            map.put("zone_percentage", "0");
        } else {
            map.put("zone_percentage", zonePercentage);
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> searchMeasureListIssueTrend(Integer project_id, Integer measure_list_id, String startTime, String endTime, String UNCLOSECODE) throws ParseException {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> newCountList = measureListIssueMapper.searchMeasureListIssueTrendNewCountList(project_id, measure_list_id, startTime, endTime, UNCLOSECODE);
        List<Map<String, Object>> trendReformList = measureListIssueMapper.searchMeasureListIssueTrendReformList(project_id, measure_list_id, startTime, endTime, UNCLOSECODE);
        //数据合并，把两个查询出来的时间合并到开始到结束时间，数据是空就记0
        do {
            Map<String, Object> map = new HashMap<>();
            map.put("date", startTime);
            boolean newCountListFlag = false;
            for (Map<String, Object> newCount : newCountList
            ) {
                if (startTime.equals(newCount.get("days").toString())) {
                    map.put("new_count", Integer.parseInt(newCount.get("new_count").toString()));
                    newCountListFlag = true;
                    break;
                }
            }
            if (!newCountListFlag) {
                map.put("new_count", 0);
            }
            boolean trendReformListFlag = false;
            for (Map<String, Object> trendReform : trendReformList
            ) {
                if (startTime.equals(trendReform.get("days").toString())) {
                    map.put("reform_count", Integer.parseInt(trendReform.get("reform_count").toString()));
                    trendReformListFlag = true;
                    break;
                }
            }
            if (!newCountListFlag) {
                map.put("reform_count", 0);
            }
            result.add(map);
            startTime = DateUtil.getShortDateStringByStringDate(startTime, 1);
        } while (DateUtil.getLongFromShortString(startTime) <= DateUtil.getLongFromShortString(endTime));
//        Collections.sort(humans, Comparator.comparing(Human::getName));
        return result;
    }

    @Override
    public Integer countMeasureListIssueDistributionCategory(Integer project_id, Integer measure_list_id, String UNCLOSECODE) {
        return measureListIssueMapper.countMeasureListIssueDistributionCategory(project_id, measure_list_id, UNCLOSECODE);
    }

    @Override
    public List<MeasureListIssue> searchMeasureListIssueDistributionCategory(Integer project_id, Integer measure_list_id, String unclosecode) {
        return measureListIssueMapper.searchMeasureListIssueDistributionCategory(project_id, measure_list_id, unclosecode);
    }

    @Override
    public List<Map<String, Object>> getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(String[] listIds, CategoryV3 categoryV3, Area area, String closedcode) {
        String cateChildPath = categoryV3.getPath() + categoryV3.getKey() + "/";
        String areaChilePath = area.getPath() + area.getId() + "/";
        return measureListIssueMapper.getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(listIds, cateChildPath, areaChilePath, closedcode);
    }

    @Override
    public List<MeasureListIssue> searchIssueListByListIdLastIdTimestampGt(Integer list_id, Integer last_id, Long timestamp, Integer start, Integer pageSize) {
        return measureListIssueMapper.searchIssueListByListIdLastIdTimestampGt(list_id, last_id, timestamp, start, pageSize);
    }

    @Override
    public List<MeasureListIssue> searchMeasueListIssueInZoneUuids(Set<String> zoneUuids) {
        return measureListIssueMapper.searchMeasueListIssueInZoneUuids(zoneUuids);
    }

    @Override
    public void insertMeasureListIssueObject(MeasureListIssue issue) {
        try {
            boolean has = measureListIssueMapper.getByUuidUnscoped(issue.getUuid()) == null ? false : true;
            if (has) {
                //如果存在
                //如果是已被删除的，应该舍弃掉
                //如果是未被删除的，应该快速失败，而不执行update
                Exception err = new Exception("测区已存在爆点整改信息");
                log.warn(err.getMessage());
                throw err;
            }
            measureListIssueMapper.insert(issue);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void updateFullNoAffectedErr(MeasureListIssue issue) {
        measureListIssueMapper.updateByPrimaryKey(issue);
    }

    @Override
    public Integer searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(Integer project_id, Integer measure_list_id, String unclosecode, String categoryPathAndKey, Integer status) {
        return measureListIssueMapper.searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(project_id, measure_list_id, unclosecode, categoryPathAndKey, status);
    }

    @Override
    public Map<String, Object> searchMeasueListIssueInProj(Integer projectId, Integer limit, Integer page, String category_key, List<Integer> areaIdList, List<String> measureListIdList, List<String> createAtRangeList, Integer status, Integer repairer_id, String is_overdue) throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        Example example = new Example(MeasureListIssue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        if (measureListIdList.size() > 0) {
            criteria.andIn("listId", measureListIdList);
        }
        if (createAtRangeList.size() == 2 && Integer.parseInt(createAtRangeList.get(1)) > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date t1 = sdf.parse(createAtRangeList.get(0));
            Date t2 = sdf.parse(createAtRangeList.get(1));
            criteria.andBetween("createAt", t1, t2);
        }
        if (areaIdList.size() > 0) {
            AreaUtils areaUtils = null;
            try {
                areaUtils = this.createAreasMapByLeaveIds(areaIdList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (Integer s : areaIdList) {
                String pathAndId = areaUtils.getPathAndId(s);
                if (!pathAndId.equals("")) {
                    criteria.andLike("areaPathAndId", this.startswith(pathAndId));
                    criteria.andEqualTo("areaId", s);
                }
            }
        }
        if (category_key.length() > 0) {
            try {
                CategoryV3 category = categoryV3Mapper.getCategoryByKeyNoFoundErr(category_key);
                if (category != null) {

                    criteria.andEqualTo("categoryKey", category.getKey());
                    criteria.orLike("categoryPathAndKey", this.startswith(this.childPath(category)));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        if (status > 0) {
            criteria.andEqualTo("status", status);
        }
        if (repairer_id > 0) {
            criteria.andEqualTo("repairerId", repairer_id);
        }
        if (!is_overdue.equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long nowTimestamp = sdf.parse("2006-01-02 23:59:59").getTime();
            Long startTimestamp = sdf.parse("1980-01-01 00:00:00").getTime();
            criteria.andGreaterThan("planEndOn", startTimestamp.intValue());
            criteria.andCondition("( end_on > plan_end_on or ( end_on < " + startTimestamp.intValue() + " and plan_end_on < " + nowTimestamp.intValue() + " ))");
        }
        Integer count = measureListIssueMapper.selectCountByExample(example);
        Integer start = 0;
        if (page > 1) {
            start = (page - 1) * limit;
        }
        String orderBy = "id ASC";
        example.setOrderByClause(orderBy);
        List<MeasureListIssue> items = measureListIssueMapper.selectByExampleAndRowBounds(example, new RowBounds(start, limit));
        map.put("count", count);
        map.put("items", items);
        return map;
    }

    private AreaUtils createAreasMapByLeaveIds(List<Integer> areaIdList) throws Exception {
        List<Area> areas = null;
        try {
            areas = this.selectAllByLeaveIds(areaIdList);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return this.createAreasMapByAreaList(areas);
    }

    private List<Area> selectAllByLeaveIds(List<Integer> areaIdList) {
        List<Area> areas = areaMapper.selectByIds(StringUtils.join(areaIdList.toArray(), ","));
        List<Integer> totalIds = Lists.newArrayList();
        Set<Integer> ids = Sets.newHashSet();
        areas.forEach(area -> {
            totalIds.add(area.getId());
            String[] sids = StringUtils.split(area.getPath(), "/");
            Integer[] aftIdArray = (Integer[]) ConvertUtils.convert(sids, Integer.class);
            List<Integer> sidss = Arrays.asList(aftIdArray);
            ids.addAll(sidss);
        });
        ids.addAll(totalIds);
        return areaMapper.selectByIds(StringUtils.join(ids.toArray(), ","));
    }

    private AreaUtils createAreasMapByAreaList(List<Area> areas) {
        AreaUtils areaUtils = new AreaUtils();
        Map<Integer, Area> maps = AreaUtils.getAreas();
        areas.forEach(area -> {
            maps.put(area.getId(), area);
        });
        areaUtils.setList(areas);
        return areaUtils;
    }

    private String startswith(String pathAndId) {
        return String.format("%s%%", pathAndId);
    }

    private String childPath(CategoryV3 categoryV3) {
        return String.format("%s%s/", categoryV3.getPath(), categoryV3.getKey());
    }

    @Override
    public Map<String, List<String>> getCategoryPathNamesMap(List<String> categoryKeys) {
        Example example = new Example(CategoryV3.class);
        example.createCriteria().andIn("key", categoryKeys);
        List<CategoryV3> categories = categoryV3Mapper.selectByExample(example);
        Map<String, CategoryV3> cMap = this.newCategoryMap(categories);
        CategoryUtils.setM(cMap);
        Map<String, List<String>> r = Maps.newHashMap();
        categoryKeys.forEach(categoryKey -> {
            r.put(categoryKey, CategoryUtils.getFullNamesByKey(categoryKey));
        });
        return r;
    }

    private Map<String, CategoryV3> newCategoryMap(List<CategoryV3> categories) {
        Map<String, CategoryV3> mCategory = Maps.newHashMap();
        categories.forEach(categoryV3 -> {
            mCategory.put(categoryV3.getKey(), categoryV3);
        });
        return mCategory;
    }

    @Override
    public Map<Integer, String> getMeasureListNameMap(Integer projectId, List<Integer> measureListIds) {
        List<MeasureList> measureLists = null;
        try {
            Example example = new Example(MeasureList.class);
            example.createCriteria().andEqualTo("projectId", projectId).andIn("id", measureListIds);
            measureLists = measureListMapper.selectByExample(example);
        } catch (Exception e) {
            return null;
        }
        Map<Integer, String> r = Maps.newHashMap();
        measureLists.forEach(measureList -> {
            r.put(measureList.getId(), measureList.getName());
        });
        return r;
    }

    @Override
    public Map<Integer, String> getUserRealNameMap(List<Integer> userIds) {
        if (userIds.size() == 0) {
            return Maps.newHashMap();
        }
        Example example = new Example(User.class);
        example.createCriteria().andIn("userId", userIds);
        Map<Integer, String> r = Maps.newHashMap();
        List<User> userList = userMapper.selectByExample(example);
        userList.forEach(user -> {
            r.put(user.getUserId(), user.getRealName());
        });
        return r;
    }

    @Override
    public Map<Integer, List<String>> getAreaPathNamesMap(List<Integer> areaIdLists) throws Exception {
        AreaUtils areaUtils = this.createAreasMapByLeaveIds(areaIdLists);
        Map<Integer, List<String>> mAreaName = Maps.newHashMap();
        areaIdLists.forEach(id -> {
            mAreaName.put(id, areaUtils.getPathNames(id));
        });
        return mAreaName;
    }

    @Override
    public MeasureListIssue GetIssueByProjectIdAndUuid(Integer projectId, String uuid) {
        return measureListIssueMapper.GetIssueByProjectIdAndUuid(projectId, uuid);
    }

    @Override
    public void deletedByUpdateDeletedAt(Integer project_id, String uuid) {
        MeasureListIssue measureListIssue = new MeasureListIssue();
        measureListIssue.setDeleteAt(new Date());

        Example example = new Example(MeasureListIssue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", project_id);
        criteria.andEqualTo("uuid", uuid);

        measureListIssueMapper.updateByExampleSelective(measureListIssue, example);
    }
    @Override
    public MeasureListIssue getByConditionNoFoundErr(Integer project_id, String uuid) {
        Example example = new Example(MeasureListIssue.class);
        example.createCriteria().andEqualTo("uuid", uuid).andEqualTo("projectId", project_id);
        return measureListIssueMapper.selectOneByExample(example);
    }

    @Override
    public MeasureListIssue getByUuidUnscoped(String issueUuid) {
        Example example = new Example(MeasureListIssue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",issueUuid);
        return measureListIssueMapper.selectOneByExample(example);
    }
}
