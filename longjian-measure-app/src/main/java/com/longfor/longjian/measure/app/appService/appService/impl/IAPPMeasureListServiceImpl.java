package com.longfor.longjian.measure.app.appService.appService.impl;

import com.google.common.collect.Lists;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appService.IAPPMeasureListService;
import com.longfor.longjian.measure.app.req.MeasureList.*;
import com.longfor.longjian.measure.app.vo.measureListVo.ListInfoVo;
import com.longfor.longjian.measure.app.vo.measureListVo.MeasureListInfoVo;
import com.longfor.longjian.measure.app.vo.measureListVo.SetStatusVo;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.domain.externalService.impl.MeasureListServiceImpl;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.MeasureZone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wang on 2019/1/7.
 */
@Service
@Slf4j
public class IAPPMeasureListServiceImpl implements IAPPMeasureListService {

    @Resource
    private IMeasureListService iMeasureListService;

    @Resource
    private IMeasureListAreaService IMeasureListAreaService;

    @Resource
    private IMeasureZoneService iMeasureZoneService;

    @Resource
    private IMeasureZoneResultService iMeasureZoneResultService;

    @Resource
    private IMeasureSquadService iMeasureSquadService;

    @Resource
    private IMeasureSquadUserService iMeasureSquadUserService;

    @Autowired
    IMeasureZoneService measureZoneService;
    @Resource
    private IMeasureListService measureListService;

    @Override
    public SetStatusVo setStatus(SetStatusReq setStatusReq) {

        MeasureList measureList = new MeasureList();

        measureList.setId(setStatusReq.getList_id());
        measureList.setProjectId(setStatusReq.getProject_id());
        measureList.setCloseStatus(setStatusReq.getClose_status());
        measureList.setFinishStatus(setStatusReq.getFinish_status());

        iMeasureListService.updateMeasureList(measureList);

        SetStatusVo setStatusVo = new SetStatusVo();

        setStatusVo.setList_id(setStatusReq.getList_id());
        setStatusVo.setClose_status(setStatusReq.getClose_status());
        setStatusVo.setFinish_status(setStatusReq.getFinish_status());

        return setStatusVo;
    }

    @Override
    public Map<String, Object> updateName(UpdateNameReq updateNameReq) {

        MeasureList measureList = new MeasureList();
        measureList.setId(updateNameReq.getList_id());
        measureList.setProjectId(updateNameReq.getProject_id());
        measureList.setName(updateNameReq.getName());
        iMeasureListService.updateMeasureList(measureList);

        Map<String, Object> map = new HashMap<>();
        map.put("name", updateNameReq.getName());
        return map;
    }

    @Override
    @Transactional
    public void delete(DeleteReq deleteReq) {
        iMeasureListService.delete(deleteReq.getList_id());

        IMeasureListAreaService.delete(deleteReq.getList_id());

        iMeasureZoneService.delete(deleteReq.getList_id());

        iMeasureZoneResultService.delete(deleteReq.getList_id());

        iMeasureSquadService.delete(deleteReq.getList_id());

        iMeasureSquadUserService.delete(deleteReq.getList_id());
    }

    @Override
    public void updateFinishStatus(UpdateFinishStatusReq updateFinishStatusReq) {

        Map<String, Object> map = new HashMap<>();

        map.put("project_id", updateFinishStatusReq.getProject_id());
        map.put("finish_status", updateFinishStatusReq.getFinish_status());
        map.put("list_ids", updateFinishStatusReq.getList_ids().split(","));
        map.put("update_at", new Date());

        iMeasureListService.updateFinishStatus(map);

    }

    @Override
    public void updateCloseStatus(UpdateCloseStatusReq updateCloseStatusReq) {
        Map<String, Object> map = new HashMap<>();

        map.put("project_id", updateCloseStatusReq.getProject_id());
        map.put("close_status", updateCloseStatusReq.getClose_status());
        map.put("list_ids", updateCloseStatusReq.getList_ids().split(","));
        map.put("update_at", new Date());

        iMeasureListService.updateCloseStatus(map);
    }

    @Override
    public MeasureList GetByProjIdAndIdNoFoundErr(Integer projectId, Integer id) {
        return measureListService.GetByProjIdAndIdNoFoundErr(projectId, id);
    }

    @Override
    public LjBaseResponse<MeasureListInfoVo> conditionSearch(ConditionSearchReq req) throws Exception {
        Map<String, Object> listMap = measureListService.conditionSearch(req.getGroup_id(), req.getProject_id(), req.getPage(), req.getPage_size(), req.getArea_id(), req.getUser_id_list(), req.getFinish_status(), req.getName(), req.getCategory_key());
        LjBaseResponse<MeasureListInfoVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureListInfoVo measureListInfoVo = new MeasureListInfoVo();
        List<ListInfoVo> listInfoVos = Lists.newArrayList();
        Integer total_num = (Integer) listMap.get("total_num");
        measureListInfoVo.setTotal(total_num);
        List<Map<String, Object>> return_list = (List<Map<String, Object>>) listMap.get("return_list");
        for (Map<String, Object> map : return_list) {
            ListInfoVo listInfoVo = new ListInfoVo();
            listInfoVo.setId((Integer) map.get("id"));
            listInfoVo.setName(map.get("name").toString());
            listInfoVo.setProj_id((Integer) map.get("projectId"));
            listInfoVo.setArea_type(map.get("areaType") == null ? "" : map.get("areaType").toString());
            listInfoVo.setClose_status((Integer) map.get("closeStatus"));
            listInfoVo.setFinish_status((Integer) map.get("finishStatus"));
            listInfoVo.setTop_areas((List<String>) map.get("top_areas"));
            listInfoVo.setRoot_category_key(map.get("rootCategoryKey").toString());
            listInfoVo.setRoot_category_name(map.get("root_category_name").toString());
            listInfoVo.setIssue_count(map.get("issue_count") == null ? 0 : (Integer) map.get("issue_count"));
            listInfoVo.setCreate_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) map.get("createAt")));
            listInfoVos.add(listInfoVo);
        }
        measureListInfoVo.setList_info(listInfoVos);
        ljBaseResponse.setData(measureListInfoVo);
        return ljBaseResponse;
    }
}
