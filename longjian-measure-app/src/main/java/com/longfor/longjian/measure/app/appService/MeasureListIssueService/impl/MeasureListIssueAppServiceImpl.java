package com.longfor.longjian.measure.app.appService.MeasureListIssueService.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.MeasureListIssueService.IMeasureListIssueAppService;
import com.longfor.longjian.measure.app.req.MeasureList.MeasureIssueQueryReq;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureIssueQueryItemVo;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureIssueQueryVo;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureListSearchResultVo;
import com.longfor.longjian.measure.app.vo.measureListVo.UserInfoVo;
import com.longfor.longjian.measure.domain.externalService.IMeasureListIssueService;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionService;
import com.longfor.longjian.measure.domain.externalService.IMeasureZoneService;
import com.longfor.longjian.measure.util.AreaUtils;
import com.longfor.longjian.measure.po.zhijian2.MeasureListIssue;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Jiazm 2019/01/11 19:18
 */
@Service
@Slf4j
public class MeasureListIssueAppServiceImpl implements IMeasureListIssueAppService {
    @Resource
    private IMeasureListIssueService measureListIssueService;
    @Resource
    private IMeasureZoneService measureZoneSerivce;
    @Resource
    private IMeasureRegionService measureRegionService;

    @Override
    public LjBaseResponse<MeasureIssueQueryVo> issueQueryJson(MeasureIssueQueryReq req, HttpServletRequest request) throws Exception {
        LjBaseResponse<MeasureIssueQueryVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureIssueQueryVo measureIssueQueryVo = new MeasureIssueQueryVo();
        List<MeasureIssueQueryItemVo> measureIssueQueryItemVos = Lists.newArrayList();
        //todo 调用鉴权
        request.setAttribute("project_id", 1);
        Integer projectId = (Integer) request.getAttribute("project_id");
        String[] areaIdArr = StringUtils.split(req.getArea_ids(), ",");
        Integer[] convert = (Integer[]) ConvertUtils.convert(areaIdArr, Integer.class);
        String[] measureListIdArr = StringUtils.split(req.getMeasure_list_ids(), ",");
        String[] createAtRangeArr = StringUtils.split(req.getCreate_at_range(), ",");
        List<Integer> areaIdList = Arrays.asList(convert);
        List<String> measureListIdList = Arrays.asList(measureListIdArr);
        List<String> createAtRangeList = Arrays.asList(createAtRangeArr);
        Map<String, Object> issueMap = null;
        try {
            issueMap = measureListIssueService.searchMeasueListIssueInProj(projectId, req.getLimit(), req.getPage(), req.getCategory_key(),
                    areaIdList, measureListIdList, createAtRangeList, req.getStatus(),
                    req.getRepairer_id(), req.getIs_overdue());

        } catch (Exception e) {
            log.error("error:" + e);
            return new LjBaseResponse<>();
        }
        measureIssueQueryVo.setCount((Integer) issueMap.get("count"));
        measureIssueQueryVo.setLimit(req.getLimit());
        measureIssueQueryVo.setPage(req.getPage());
        List<MeasureListIssue> items = (List<MeasureListIssue>) issueMap.get("items");
        if (items.size() == 0) {
            ljBaseResponse.setData(measureIssueQueryVo);
            return ljBaseResponse;
        }
        String none = "";
        List<String> zoneUuids = Lists.newArrayList();
        List<String> regionUuids = Lists.newArrayList();
        List<Integer> areaIds = Lists.newArrayList();
        Map<Integer, Object> mapMeasureId = Maps.newHashMap();
        Map<Integer, Object> mapUserId = Maps.newHashMap();
        Map<Integer, Object> mapAreaId = Maps.newHashMap();
        Map<String, Object> mapCategoryKey = Maps.newHashMap();
        // 获取相关 user area category ids
        for (MeasureListIssue item : items) {
            zoneUuids.add(item.getZoneUuid());
            mapMeasureId.put(item.getListId(), none);
            mapUserId.put(item.getRepairerId(), none);
            mapAreaId.put(item.getAreaId(), none);
            for (String id : StringUtils.split(item.getAreaPathAndId(), "/")) {
                mapAreaId.put(Integer.parseInt(id), none);
            }
            mapCategoryKey.put(item.getCategoryKey(), none);
            for (String key : StringUtils.split(StringUtils.trim(item.getCategoryPathAndKey()), "/")) {
                mapCategoryKey.put(key, none);
            }
        }
        //获取测区
        List<MeasureZone> measureZones = null;
        try {
            measureZones = measureZoneSerivce.searchZoneByProjUuids(req.getProject_id(), zoneUuids);
        } catch (Exception e) {
            log.error("error:" + e);
            return new LjBaseResponse<>();
        }
        Map<String, MeasureZone> mZone = Maps.newHashMap();
        measureZones.forEach(measureZone -> {
            regionUuids.add(measureZone.getRegionUuid());
            mZone.put(measureZone.getUuid(), measureZone);
        });
        //获取描画区域
        List<MeasureRegion> regions = null;
        try {
            regions = measureRegionService.searchByProjUuids(req.getProject_id(), regionUuids);
        } catch (Exception e) {
            log.error("error:" + e);
            return new LjBaseResponse<>();
        }
        Map<String, MeasureRegion> mRegion = Maps.newHashMap();
        regions.forEach(measureRegion -> {
            areaIds.add(measureRegion.getAreaId());
            mRegion.put(measureRegion.getUuid(), measureRegion);
        });
        List<Integer> userIds = Lists.newArrayList();
        List<Integer> areaIdLists = Lists.newArrayList();
        List<String> categoryKeys = Lists.newArrayList();
        List<Integer> measureListIds = Lists.newArrayList();
        mapUserId.forEach((k, v) -> {
            userIds.add(k);
        });
        mapAreaId.forEach((k, v) -> {
            areaIdLists.add(k);
        });
        mapCategoryKey.forEach((k, v) -> {
            categoryKeys.add(k);
        });
        mapMeasureId.forEach((k, v) -> {
            measureListIds.add(k);
        });
        // 获取列表相关的名称map
        Map<String, Object> map = this.getIssueReferNameMap(projectId, areaIdLists, categoryKeys, measureListIds, userIds);
        Map<Integer, List<String>> mAreaName = (Map<Integer, List<String>>) map.get("mAreaName");
        Map<String, List<String>> mCategoryName = (Map<String, List<String>>) map.get("mCategoryName");
        Map<Integer, String> mMeasureListName = (Map<Integer, String>) map.get("mMeasureListName");
        Map<Integer, String> mUserName = (Map<Integer, String>) map.get("mUserName");
        for (MeasureListIssue item : items) {
            MeasureIssueQueryItemVo r = new MeasureIssueQueryItemVo();
            MeasureListSearchResultVo measureList = new MeasureListSearchResultVo();
            measureList.setId(item.getListId());
            measureList.setName(mMeasureListName.get(item.getListId()));
            UserInfoVo repairer =new UserInfoVo();
            repairer.setId(item.getRepairerId());
            repairer.setReal_name(mUserName.get(item.getRepairerId()));
            r.setMeasure_list(measureList);
            r.setRepairer(repairer);
            r.setArea_path_names(mAreaName.get(item.getAreaId()));
            r.setCategory_path_names(mCategoryName.get(item.getCategoryKey()));
            r.setId(item.getId());
            r.setUuid(item.getUuid());
            r.setProject_id(item.getProjectId());
            r.setCreate_at(new Long(item.getCreateAt().getTime()).intValue());
            r.setPlan_end_on(item.getPlanEndOn());
            r.setStatus(item.getStatus());
            r.setClose_status(item.getCloseStatus());
            try {
                MeasureZone zone = mZone.get(item.getUuid());
                MeasureRegion region = mRegion.get(zone.getRegionUuid());
                if(item.getAreaId().equals(region.getAreaId())){
                    r.setArea_info(StringUtils.join(r.getArea_path_names(),"-")+" "+region.getRegionIndex() );
                    r.setRegion_id(region.getId());
                }
            }catch (Exception e){
                throw new Exception(e);
            }
            measureIssueQueryItemVos.add(r);
            measureIssueQueryVo.setItems(measureIssueQueryItemVos);
            ljBaseResponse.setData(measureIssueQueryVo);
        }
        return ljBaseResponse;
    }

    public Map<String, Object> getIssueReferNameMap(Integer projectId, List<Integer> areaIdLists, List<String> categoryKeys, List<Integer> measureListIds, List<Integer> userIds) {
        try {
            Map<Integer, List<String>> mAreaName = measureListIssueService.getAreaPathNamesMap(areaIdLists);
            Map<String, List<String>> mCategoryName = measureListIssueService.getCategoryPathNamesMap(categoryKeys);
            Map<Integer, String> mMeasureListName = measureListIssueService.getMeasureListNameMap(projectId, measureListIds);
            Map<Integer, String> mUserName = measureListIssueService.getUserRealNameMap(userIds);
            Map<String, Object> map = Maps.newHashMap();
            map.put("mAreaName", mAreaName);
            map.put("mCategoryName", mCategoryName);
            map.put("mMeasureListName", mMeasureListName);
            map.put("mUserName", mUserName);
            return map;
        } catch (Exception e) {
            return Maps.newHashMap();
        }
    }
}
