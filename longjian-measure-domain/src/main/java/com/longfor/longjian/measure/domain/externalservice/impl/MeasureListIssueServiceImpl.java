package com.longfor.longjian.measure.domain.externalservice.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.dao.zhijian2.*;
import com.longfor.longjian.measure.dao.zhijian2_apisvr.UserMapper;
import com.longfor.longjian.measure.domain.externalservice.IMeasureListIssueService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.util.AreaUtils;
import com.longfor.longjian.measure.util.CategoryUtils;
import com.longfor.longjian.measure.util.DateTool;
import com.longfor.longjian.measure.util.DateUtil;
import com.longfor.longjian.measure.vo.GetMeasureListIssueBriefVo;
import com.longfor.longjian.measure.vo.SearchMeasueListIssueInProjVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
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
    private static final String DELETEAT = "deleteAt";
    private static final String NEW_COUNT = "new_count";
    private static final String REFORM_COUNT = "reform_count";
    private static final String PROJECTID = "projectId";
    private static final String PATHFLAG = "/";

    @Override
    public Integer countByMeasureListId(String id) {
        Example example = new Example(MeasureListIssue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("listId", id);
        criteria.andIsNull(DELETEAT);
        return measureListIssueMapper.selectCountByExample(example);
    }

    @Override
    public Map<String, Object> getMeasureListIssueBrief(GetMeasureListIssueBriefVo vo) {
        Map<String, Object> map = new HashMap<>();
        Integer zoneCount = measureListIssueMapper.getZoneCount(vo.getProject_id(), vo.getMeasure_list_id(), vo.getUNCLOSECODE());
        map.put("zone_count", zoneCount);
        map.put("repairable_count", measureListIssueMapper.getCountByTyp(vo.getProject_id(), vo.getMeasure_list_id(), vo.getREPAIRABLE(), vo.getUNCLOSECODE()));
        map.put("unrepairable_count", measureListIssueMapper.getCountByTyp(vo.getProject_id(), vo.getMeasure_list_id(), vo.getNOREPAIRABLE(), vo.getUNCLOSECODE()));
        map.put("note_no_assign", measureListIssueMapper.getCountByStatus(vo.getProject_id(), vo.getMeasure_list_id(), vo.getNOTENOASSIGN(), vo.getUNCLOSECODE()));
        map.put("assign_no_reform", measureListIssueMapper.getCountByStatus(vo.getProject_id(), vo.getMeasure_list_id(), vo.getASSIGNNOREFORM(), vo.getUNCLOSECODE()));
        map.put("reform_no_check", measureListIssueMapper.getCountByStatus(vo.getProject_id(), vo.getMeasure_list_id(), vo.getREFORMNOCHECK(), vo.getUNCLOSECODE()));
        map.put("check_yes", measureListIssueMapper.getCountByStatus(vo.getProject_id(), vo.getMeasure_list_id(), vo.getCHECKYES(), vo.getUNCLOSECODE()));
        //查找所有测区
        int count = measureZoneMapper.searchTotalByProjectIdAndMeasureListId(vo.getProject_id(), new int[]{vo.getMeasure_list_id()});
        String zonePercentage = String.format("%.2f", (Double.parseDouble(zoneCount.toString()) / count) * 100);
        if ("0.00".equals(zonePercentage) || zoneCount == 0) {
            map.put("zone_percentage", "0");
        } else {
            map.put("zone_percentage", zonePercentage);
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> searchMeasureListIssueTrend(Integer projectId, Integer measureListId, String startTime, String endTime, String unCloseCode) throws ParseException {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> newCountList = measureListIssueMapper.searchMeasureListIssueTrendNewCountList(projectId, measureListId, startTime, endTime, unCloseCode);
        List<Map<String, Object>> trendReformList = measureListIssueMapper.searchMeasureListIssueTrendReformList(projectId, measureListId, startTime, endTime, unCloseCode);
        //数据合并，把两个查询出来的时间合并到开始到结束时间，数据是空就记0
        do {
            Map<String, Object> map = new HashMap<>();
            map.put("date", startTime);
            boolean newCountListFlag = false;
            for (Map<String, Object> newCount : newCountList
            ) {
                if (startTime.equals(newCount.get("days").toString())) {
                    map.put(NEW_COUNT, Integer.parseInt(newCount.get(NEW_COUNT).toString()));
                    newCountListFlag = true;
                    break;
                }
            }
            if (!newCountListFlag) {
                map.put(NEW_COUNT, 0);
            }
            for (Map<String, Object> trendReform : trendReformList
            ) {
                if (startTime.equals(trendReform.get("days").toString())) {
                    map.put(REFORM_COUNT, Integer.parseInt(trendReform.get(REFORM_COUNT).toString()));
                    break;
                }
            }
            if (!newCountListFlag) {
                map.put(REFORM_COUNT, 0);
            }
            result.add(map);
            startTime = DateTool.getShortDateStringByStringDate(startTime, 1);
        } while (DateTool.getLongFromShortString(startTime) <= DateTool.getLongFromShortString(endTime));
        return result;
    }

    @Override
    public Integer countMeasureListIssueDistributionCategory(Integer projectId, Integer measureListId, String unCloseCode) {
        return measureListIssueMapper.countMeasureListIssueDistributionCategory(projectId, measureListId, unCloseCode);
    }

    @Override
    public List<MeasureListIssue> searchMeasureListIssueDistributionCategory(Integer projectId, Integer measureListId, String unclosecode) {
        return measureListIssueMapper.searchMeasureListIssueDistributionCategory(projectId, measureListId, unclosecode);
    }

    @Override
    public List<Map<String, Object>> getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(String[] listIds, CategoryV3 categoryV3, Area area, String closedcode) {
        String cateChildPath = categoryV3.getPath() + categoryV3.getKey() + PATHFLAG;
        String areaChilePath = area.getPath() + area.getId() + PATHFLAG;
        return measureListIssueMapper.getMeasureListIssueStatusMapByListIdsAndCategoryKeyAndAreaId(listIds, cateChildPath, areaChilePath, closedcode);
    }

    @Override
    public List<MeasureListIssue> searchIssueListByListIdLastIdTimestampGt(Integer listId, Integer lastId, Long timestamp, Integer start, Integer pageSize) {
        return measureListIssueMapper.searchIssueListByListIdLastIdTimestampGt(listId, lastId, timestamp, start, pageSize);
    }

    @Override
    public List<MeasureListIssue> searchMeasueListIssueInZoneUuids(Set<String> zoneUuids) {
        return measureListIssueMapper.searchMeasueListIssueInZoneUuids(zoneUuids);
    }

    @Override
    public void insertMeasureListIssueObject(MeasureListIssue issue) {
        try {
            boolean has = measureListIssueMapper.getByUuidUnscoped(issue.getUuid()) != null;
            if (has) {
                //如果存在
                //如果是已被删除的，应该舍弃掉
                //如果是未被删除的，应该快速失败，而不执行update
                Exception err = new Exception("测区已存在爆点整改信息");
                log.warn(err.getMessage());
                throw err;
            }
            issue.setCreateAt(new Date());
            issue.setUpdateAt(new Date());
            measureListIssueMapper.insert(issue);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void updateFullNoAffectedErr(MeasureListIssue issue) {
        issue.setUpdateAt(new Date());
        measureListIssueMapper.updateByPrimaryKey(issue);
    }

    @Override
    public Integer searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(Integer projectId, Integer measureListId, String unclosecode, String categoryPathAndKey, Integer status) {
        return measureListIssueMapper.searchMeasureListIssueByCloseStatusAndStatusAndCategoryPathAndKeyLike(projectId, measureListId, unclosecode, categoryPathAndKey, status);
    }

    @Override
    @SuppressWarnings("squid:S3776")
    public Map<String, Object> searchMeasueListIssueInProj(SearchMeasueListIssueInProjVo vo) throws ParseException {
        Map<String, Object> map = Maps.newHashMap();
        Example example = new Example(MeasureListIssue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, vo.getProjectId());
        if (!vo.getMeasureListIdList().isEmpty() && vo.getMeasureListIdList().size() > 0) {
            criteria.andIn("listId", vo.getMeasureListIdList());
        }
        if (vo.getCreateAtRangeList().size() == 2 && Integer.parseInt(vo.getCreateAtRangeList().get(1)) > 0) {
            String t1 = DateUtil.getLongDateStringByLong(Long.parseLong(vo.getCreateAtRangeList().get(0)) * 1000);
            String t2 = DateUtil.getLongDateStringByLong(Long.parseLong(vo.getCreateAtRangeList().get(1)) * 1000);
            criteria.andBetween("createAt", t1, t2);
        }
        if (!vo.getAreaIdList().isEmpty() && vo.getAreaIdList().size() > 0) {
            try {
                this.createAreasMapByLeaveIds(vo.getAreaIdList());
            } catch (Exception e) {
                throw new LjBaseRuntimeException(-9999, e + "");
            }
            Example.Criteria criteria1 = example.createCriteria();
            for (Integer s : vo.getAreaIdList()) {
                String pathAndId = AreaUtils.getPathAndId(s);
                if (!pathAndId.equals("")) {
                    criteria1.orCondition("(area_path_and_id like \"" + this.startswith(pathAndId) + "\" and area_id = " + s + " )");
                }
            }
            example.and(criteria1);
        }
        if (vo.getCategory_key() != null && vo.getCategory_key().length() > 0) {
            Example.Criteria criteria2 = example.createCriteria();
            try {
                CategoryV3 category = categoryV3Mapper.getCategoryByKeyNoFoundErr(vo.getCategory_key());
                if (category != null) {
                    criteria2.andEqualTo("categoryKey", category.getKey());
                    criteria2.orLike("categoryPathAndKey", this.startswith(this.childPath(category)));
                }
                example.and(criteria2);
            } catch (Exception e) {
                throw new LjBaseRuntimeException(-9999, e + "");
            }

        }
        if (vo.getClose_status() == null && vo.getStatus() != null && vo.getStatus() > 0) {
            criteria.andEqualTo("status", vo.getStatus());
            criteria.andEqualTo("closeStatus",1);
        }
        if (vo.getClose_status() != null && vo.getClose_status() > 0) {
            criteria.andEqualTo("closeStatus", vo.getClose_status());
        }
        if (vo.getRepairer_id() != null && vo.getRepairer_id() > 0) {
            criteria.andEqualTo("repairerId", vo.getRepairer_id());
        }
        if (vo.getIs_overdue() != null && !vo.getIs_overdue().equals(false)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date nowTimestamp = new Date();
            Date startTimestamp = sdf.parse("1980-01-01 00:00:00");
            criteria.andGreaterThan("planEndOn", startTimestamp);
            criteria.andCondition("( end_on > plan_end_on or ( end_on < " + com.longfor.longjian.common.util.DateUtil.dateToTimestamp(startTimestamp) + " and plan_end_on < " + com.longfor.longjian.common.util.DateUtil.dateToTimestamp(nowTimestamp) + " ))");
        }
        criteria.andIsNull(DELETEAT);
        Integer count = measureListIssueMapper.selectCountByExample(example);
        Integer start = 0;
        if (vo.getPage() > 1) {
            start = (vo.getPage() - 1) * vo.getLimit();
        }
        String orderBy = "id ASC";
        example.setOrderByClause(orderBy);
        List<MeasureListIssue> items = measureListIssueMapper.selectByExampleAndRowBounds(example, new RowBounds(start, vo.getLimit()));
        map.put("count", count);
        map.put("items", items);
        return map;
    }

    private void createAreasMapByLeaveIds(List<Integer> areaIdList) throws LjBaseRuntimeException {
        try {
            List<Area> areas = this.selectAllByLeaveIds(areaIdList);
            this.createAreasMapByAreaList(areas);
        } catch (Exception e) {
            throw new LjBaseRuntimeException(-9999, e + "");
        }
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
        if(ids.isEmpty()){
            return  new ArrayList<>();
        }
        return areaMapper.selectByIds(StringUtils.join(ids.toArray(), ","));
    }

    private void createAreasMapByAreaList(List<Area> areas) {
        Map<Integer, Area> maps = AreaUtils.getAreas();
        areas.forEach(area -> AreaUtils.getAreas().put(area.getId(), area));
        AreaUtils.setAreas(maps);
        AreaUtils.setList(areas);
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
        example.createCriteria().andIn("key", categoryKeys).andIsNull(DELETEAT);

        List<CategoryV3> categories = categoryV3Mapper.selectByExample(example);
        Map<String, CategoryV3> cMap = this.newCategoryMap(categories);
        CategoryUtils.setM(cMap);
        Map<String, List<String>> r = Maps.newHashMap();
        categoryKeys.forEach(categoryKey -> r.put(categoryKey, CategoryUtils.getFullNamesByKey(categoryKey)));
        return r;
    }

    private Map<String, CategoryV3> newCategoryMap(List<CategoryV3> categories) {
        Map<String, CategoryV3> mCategory = Maps.newHashMap();
        categories.forEach(categoryV3 -> mCategory.put(categoryV3.getKey(), categoryV3));
        return mCategory;
    }

    @Override
    public Map<Integer, String> getMeasureListNameMap(Integer projectId, List<Integer> measureListIds) {
        List<MeasureList> measureLists = null;
        try {
            Example example = new Example(MeasureList.class);
            example.createCriteria().andEqualTo(PROJECTID, projectId).andIn("id", measureListIds);
            measureLists = measureListMapper.selectByExample(example);
        } catch (Exception e) {
            return null;
        }
        Map<Integer, String> r = Maps.newHashMap();
        measureLists.forEach(measureList -> r.put(measureList.getId(), measureList.getName()));
        return r;
    }

    @Override
    public Map<Integer, List<String>> getAreaPathNamesMap(List<Integer> areaIdLists) {
        this.createAreasMapByLeaveIds(areaIdLists);
        Map<Integer, List<String>> mAreaName = Maps.newHashMap();
        areaIdLists.forEach(id -> mAreaName.put(id, AreaUtils.getPathNames(id)));
        return mAreaName;
    }

    @Override
    public MeasureListIssue getIssueByProjectIdAndUuid(Integer projectId, String uuid) {
        return measureListIssueMapper.GetIssueByProjectIdAndUuid(projectId, uuid);
    }

    @Override
    public void deletedByUpdateDeletedAt(Integer projectId, String uuid) {
        MeasureListIssue measureListIssue = new MeasureListIssue();
        measureListIssue.setDeleteAt(new Date());

        Example example = new Example(MeasureListIssue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(PROJECTID, projectId);
        criteria.andEqualTo("uuid", uuid);

        measureListIssueMapper.updateByExampleSelective(measureListIssue, example);
    }

    @Override
    public MeasureListIssue getByConditionNoFoundErr(Integer projectId, String uuid) {
        Example example = new Example(MeasureListIssue.class);
        example.createCriteria().andEqualTo("uuid", uuid).andEqualTo(PROJECTID, projectId);
        return measureListIssueMapper.selectOneByExample(example);
    }

    @Override
    public MeasureListIssue getByUuidUnscoped(String issueUuid) {
//        Example example = new Example(MeasureListIssue.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("uuid", issueUuid);
        return measureListIssueMapper.getByUuid(issueUuid);
    }
}
