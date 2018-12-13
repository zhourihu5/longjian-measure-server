package com.longfor.longjian.measure.app.appService.appService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appService.IAPPMeasureService;
import com.longfor.longjian.measure.app.appService.appService.IKeyProcedureTaskAppService;
import com.longfor.longjian.measure.app.req.appReq.*;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.*;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.PolygonVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.RegionListVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.RelVo;
import com.longfor.longjian.measure.consts.constant.KeyProcedureTaskConstant;
import com.longfor.longjian.measure.domain.externalService.*;
import com.longfor.longjian.measure.po.zhijian2.*;
import com.longfor.longjian.measure.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class APPMeasureServiceImpl implements IAPPMeasureService {

    private static Integer MEASURE_API_GET_PER_TIME = 5000;

    @Autowired
    IKeyProcedureTaskAppService keyProcedureTaskAppService;
    @Autowired
    IMeasureRegionService measureRegionService;
    @Autowired
    IMeasureRegionRelService measureRegionRelService;
    @Autowired
    IMeasureListService measureListService;
    @Autowired
    IMeasureSquadService measureSquadService;
    @Autowired
    IMeasureSquadUserService measureSquadUserService;
    @Autowired
    IMeasureRepairerUserService measureRepairerUserService;


    @Override
    public LjBaseResponse<ReportIssueVo> reportIssue(ApiMeasureReportIssueReq apiMeasureReportIssueReq, HttpServletRequest request) throws Exception {
        // 检查uuid，没有uuid也可以执行以下代码以保存请求内容
        String reportUuidStatus = KeyProcedureTaskConstant.ERROR;
        // TODO session获取uid
        Integer uid = 8;
        try {
            keyProcedureTaskAppService.startReport(apiMeasureReportIssueReq.getReport_uuid(), uid, request);
        } catch (Exception e) {
            keyProcedureTaskAppService.updateReportStatus(apiMeasureReportIssueReq.getReport_uuid(), reportUuidStatus);
            throw e;
        }
        return null;
    }

    @Override
    public LjBaseResponse<MeasureRegionVo> getMeasureRegion(ApiMeasureRegionReq apiMeasureRegionReq) throws Exception {
        LjBaseResponse<MeasureRegionVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureRegionVo measureRegionVo = new MeasureRegionVo();
        List<RegionListVo> region_list = new ArrayList<>();
        List<RelVo> rel_list = new ArrayList<>();
        String [] projectIds = apiMeasureRegionReq.getProject_ids().split(",");
        if (projectIds.length == 0) {
            throw new Exception("project ids is empty.");
        }
        String updateAtGte = "0001-01-01 00:00:00";
        List<MeasureRegion> regionList = new ArrayList<>();
        for (String projectId:projectIds
             ) {
            List<MeasureRegion> regions = measureRegionService.searchUnscopedByProjIdUpdateAtGt(projectId,updateAtGte);
            regionList.addAll(regions);
        }
        //类转换,手动
        regionList.forEach(region -> {
            RegionListVo regionListVo = converMeasureRegionToRegionListVo(region);
            region_list.add(regionListVo);
        });
        List<MeasureRegionRel> regionRelList = new ArrayList<>();
        for (String projectId:projectIds
        ) {
            List<MeasureRegionRel> regionRels = measureRegionRelService.searchRelUnscopedByProjIdUpdateAtGt(projectId,updateAtGte);
            regionRelList.addAll(regionRels);
        }
        //类转换,手动
        regionRelList.forEach(regionRel -> {
            RelVo relVo = converMeasureRegionRelToRegionRelListVo(regionRel);
            rel_list.add(relVo);
        });
        measureRegionVo.setRel_list(rel_list);
        measureRegionVo.setRegion_list(region_list);
        ljBaseResponse.setData(measureRegionVo);
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureRegionV2Vo> getMeasureRegionV2(ApiMeasureRegionReqV2 apiMeasureRegionReqV2) throws Exception {
        LjBaseResponse<MeasureRegionV2Vo> ljBaseResponse = new LjBaseResponse<>();
        MeasureRegionV2Vo measureRegionV2Vo = new MeasureRegionV2Vo();
        List<RegionListVo> region_list = new ArrayList<>();
        Integer start = 0;
        try {
            List<MeasureRegion> items = measureRegionService.searchUnscopedByProjIdLastIdUpdateAtGt(apiMeasureRegionReqV2.getProject_id(),apiMeasureRegionReqV2.getLast_id(),apiMeasureRegionReqV2.getTimestamp(),MEASURE_API_GET_PER_TIME,start);
            Integer newLastId = 0;
            if (items.size() > 0){
                newLastId = items.get(items.size() - 1).getId();
            }
            measureRegionV2Vo.setLast_id(newLastId);
            items.forEach(measureRegion -> {
                RegionListVo regionListVo = converMeasureRegionToRegionListVo(measureRegion);
                region_list.add(regionListVo);
            });
            measureRegionV2Vo.setRegion_list(region_list);
            ljBaseResponse.setData(measureRegionV2Vo);
        }catch (Exception e){
            log.error("SearchUnscopedByProjIdLastIdUpdateAtGt" + "[" + apiMeasureRegionReqV2.getProject_id() + "],error:" + e);
            throw new Exception("读取数据失败，code:region");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<TotalVo> getMeasureRegionV2Total(ApiMeasureRegionTotalReqV2 apiMeasureRegionTotalReqV2) throws Exception {
        LjBaseResponse<TotalVo> ljBaseResponse = new LjBaseResponse<>();
        TotalVo totalVo = new TotalVo();
        try {
            Integer total = measureRegionService.getCountUnscopedByProjIdUpdateAtGt(apiMeasureRegionTotalReqV2.getProject_id(),apiMeasureRegionTotalReqV2.getTimestamp());
            totalVo.setTotal(total);
            ljBaseResponse.setData(totalVo);
        }catch (Exception e){
            log.error("GetCountUnscopedByProjIdUpdateAtGt" + "[" + apiMeasureRegionTotalReqV2.getProject_id() + "],error:" + e);
            throw new Exception("读取数据失败，code:region_total");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureRegionRelV2Vo> getMeasureRegionRelV2(ApiMeasureRegionRelReqV2 apiMeasureRegionRelReqV2) throws Exception {
        LjBaseResponse<MeasureRegionRelV2Vo>ljBaseResponse = new LjBaseResponse<>();
        MeasureRegionRelV2Vo measureRegionRelV2Vo = new MeasureRegionRelV2Vo();
        List<RelVo> rel_list = new ArrayList<>();
        Integer start = 0;
        try {
            List<MeasureRegionRel> items = measureRegionRelService.searchRelUnscopedByProjIdLastIdUpdateAtGt(apiMeasureRegionRelReqV2.getProject_id(), apiMeasureRegionRelReqV2.getLast_id(), apiMeasureRegionRelReqV2.getTimestamp(), MEASURE_API_GET_PER_TIME, start);
            Integer newLastId = 0;
            if (items.size() > 0){
                newLastId = items.get(items.size() - 1).getId();
            }
            measureRegionRelV2Vo.setLast_id(newLastId);
            items.forEach(measureRegionRel -> {
                RelVo relVo = converMeasureRegionRelToRegionRelListVo(measureRegionRel);
                rel_list.add(relVo);
            });
            measureRegionRelV2Vo.setRel_list(rel_list);
            ljBaseResponse.setData(measureRegionRelV2Vo);
        }catch (Exception e){
            log.error("SearchRelUnscopedByProjIdLastIdUpdateAtGt" + "[" + apiMeasureRegionRelReqV2.getProject_id() + "],error:" + e);
            throw new Exception("读取数据失败，code:region_rel");
        }
        return ljBaseResponse;
    }

    @Override
    public LjBaseResponse<MeasureSquadAndRepairerVo> measureSquadAndRepairer(ApiMeasureSquadAndRepairerReq apiMeasureSquadAndRepairerReq) throws Exception {
        LjBaseResponse<MeasureSquadAndRepairerVo> ljBaseResponse = new LjBaseResponse<>();
        MeasureSquadAndRepairerVo measureSquadAndRepairerVo = new MeasureSquadAndRepairerVo();
        List<SquadListVo> squad_list = new ArrayList<>();
        List<SquadUserListVo> squad_user_list = new ArrayList<>();
        List<RepairerListVo> repairer_list = new ArrayList<>();
       MeasureList measureList = null;
        try{
            measureList = measureListService.getNoProjNoFoundErr(apiMeasureSquadAndRepairerReq.getList_id());
            String updateAtGt = "";
            if (apiMeasureSquadAndRepairerReq.getTimestamp() != null && apiMeasureSquadAndRepairerReq.getTimestamp() > 0){
                updateAtGt = DateUtil.getDateStringByLong(apiMeasureSquadAndRepairerReq.getTimestamp());
            }
            List<MeasureSquad> squads = measureSquadService.searchMeasureSquadByListIdTimestampGt(measureList.getProjectId(),apiMeasureSquadAndRepairerReq.getList_id(),updateAtGt);
            squads.forEach(measureSquad -> {
                SquadListVo squadListVo = converMeasureSquadToSquadListVo(measureSquad);
                squad_list.add(squadListVo);
            });
            measureSquadAndRepairerVo.setSquad_list(squad_list);
            List<MeasureSquadUser> squadsUsers = measureSquadUserService.searchMeasureSquadUserByListIdTimestampGt(measureList.getProjectId(),apiMeasureSquadAndRepairerReq.getList_id(),updateAtGt);
            squadsUsers.forEach(measureSquadUser -> {
                SquadUserListVo squadUserListVo = converMeasureSquadUserToSquadUserListVo(measureSquadUser);
                squad_user_list.add(squadUserListVo);
            });
            measureSquadAndRepairerVo.setSquad_user_list(squad_user_list);
            List<MeasureRepairerUser> repairerUsers = measureRepairerUserService.searchMeasureReparierUserByListIdTimestampGt(measureList.getProjectId(),apiMeasureSquadAndRepairerReq.getList_id(),updateAtGt);
            repairerUsers.forEach(measureRepairerUser -> {
                RepairerListVo repairerListVo = converMeasureRepairerUserToRepairerListVo(measureRepairerUser);
                repairer_list.add(repairerListVo);
            });
            measureSquadAndRepairerVo.setRepairer_list(repairer_list);
        }catch (Exception e){
            log.error(e + "");
            throw new Exception(e);
        }
        ljBaseResponse.setData(measureSquadAndRepairerVo);
        return ljBaseResponse;
    }

    /**
     *
     * @param measureRepairerUser
     * @return
     */
    private RepairerListVo converMeasureRepairerUserToRepairerListVo(MeasureRepairerUser measureRepairerUser) {
        RepairerListVo repairerListVo = new RepairerListVo();
        repairerListVo.setDelete_at(measureRepairerUser.getDeleteAt() ==  null ? 0 : measureRepairerUser.getDeleteAt().getTime());
        repairerListVo.setId(measureRepairerUser.getId());
        repairerListVo.setList_id(measureRepairerUser.getListId());
        repairerListVo.setProject_id(measureRepairerUser.getProjectId());
        repairerListVo.setRole_type(measureRepairerUser.getRoleType());
        repairerListVo.setUpdate_at(measureRepairerUser.getUpdateAt() == null ? 0 :measureRepairerUser.getUpdateAt().getTime());
        repairerListVo.setUser_id(measureRepairerUser.getUserId());
        return repairerListVo;
    }

    /**
     *
     * @param measureSquadUser
     * @return
     */
    private SquadUserListVo converMeasureSquadUserToSquadUserListVo(MeasureSquadUser measureSquadUser) {
        SquadUserListVo squadUserListVo = new SquadUserListVo();
        squadUserListVo.setDelete_at(measureSquadUser.getDeleteAt() ==  null ? 0 : measureSquadUser.getDeleteAt().getTime());
        squadUserListVo.setId(measureSquadUser.getId());
        squadUserListVo.setList_id(measureSquadUser.getListId());
        squadUserListVo.setProject_id(measureSquadUser.getProjectId());
        squadUserListVo.setSquad_id(measureSquadUser.getSquadId());
        squadUserListVo.setUpdate_at(measureSquadUser.getUpdateAt() ==  null ? 0 : measureSquadUser.getUpdateAt().getTime());
        squadUserListVo.setUser_id(measureSquadUser.getUserId());
        return squadUserListVo;
    }

    /**
     *
     * @param measureSquad
     * @return
     */
    private SquadListVo converMeasureSquadToSquadListVo(MeasureSquad measureSquad) {
        SquadListVo squadListVo = new SquadListVo();
        squadListVo.setDelete_at(measureSquad.getDeleteAt() ==  null ? 0 : measureSquad.getDeleteAt().getTime());
        squadListVo.setId(measureSquad.getId());
        squadListVo.setList_id(measureSquad.getListId());
        squadListVo.setName(measureSquad.getName());
        squadListVo.setPlan_rate(measureSquad.getPlanRate());
        squadListVo.setProject_id(measureSquad.getProjectId());
        squadListVo.setRate(measureSquad.getRate());
        squadListVo.setUpdate_at(measureSquad.getUpdateAt() ==  null ? 0 : measureSquad.getUpdateAt().getTime());
        return squadListVo;
    }

    /**
     *
     * @param regionRel
     * @return
     */
    private RelVo converMeasureRegionRelToRegionRelListVo(MeasureRegionRel regionRel) {
        RelVo relVo = new RelVo();
        relVo.setDelete_at(regionRel.getDeleteAt() == null ? 0 : regionRel.getDeleteAt().getTime());
        relVo.setDesc(regionRel.getDesc());
        relVo.setId(regionRel.getId());
        relVo.setProject_id(regionRel.getProjectId());
        relVo.setRegion_ids(regionRel.getRegionIds());
        relVo.setUpdate_at(regionRel.getUpdateAt() == null ? 0 : regionRel.getUpdateAt().getTime());
        return relVo;
    }

    /**
     * 手动转换
     * @param region
     * @return
     */
    private RegionListVo converMeasureRegionToRegionListVo(MeasureRegion region) {
        RegionListVo regionListVo = new RegionListVo();
        regionListVo.setArea_id(region.getAreaId());
        regionListVo.setArea_path_and_id(region.getAreaPathAndId());
        regionListVo.setDelete_at(region.getDeleteAt() ==  null ? 0 : region.getDeleteAt().getTime());
        regionListVo.setDrawing_md5(region.getDrawingMd5());
        regionListVo.setId(region.getId());
        regionListVo.setProject_id(region.getProjectId());
        regionListVo.setRegion_index(region.getRegionIndex());
        regionListVo.setRel_id(region.getRelId());
        regionListVo.setSrc_type(region.getSrcType());
        regionListVo.setTag_id_list(region.getTagIdList());
        regionListVo.setUpdate_at(region.getUpdateAt() ==  null ? 0 : region.getUpdateAt().getTime());
        regionListVo.setUuid(region.getUuid());
        JSONObject polygon = JSON.parseObject(region.getPolygon());
        PolygonVo polygonVo = new PolygonVo();
        polygonVo.setX(Double.parseDouble(polygon.get("X") + ""));
        polygonVo.setY(Double.parseDouble(polygon.get("Y") + ""));
        regionListVo.setPolygon(polygonVo);
        return regionListVo;
    }
}
