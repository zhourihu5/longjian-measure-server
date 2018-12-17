package com.longfor.longjian.measure.app.appService.appService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.appService.IAPPMeasureService;
import com.longfor.longjian.measure.app.appService.appService.IKeyProcedureTaskAppService;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureRegionReq;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureRegionReqV2;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureRegionTotalReqV2;
import com.longfor.longjian.measure.app.req.appReq.ApiMeasureReportIssueReq;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureRegionV2Vo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.MeasureRegionVo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.ReportIssueVo;
import com.longfor.longjian.measure.app.vo.appMeasureSyncVo.TotalVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.PolygonVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.RegionListVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.RelVo;
import com.longfor.longjian.measure.consts.constant.KeyProcedureTaskConstant;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionRelService;
import com.longfor.longjian.measure.domain.externalService.IMeasureRegionService;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public LjBaseResponse<DroppedInfoVo> reportZoneResult(ApiMeasureReportZoneResultReq apiMeasureReportZoneResultReq) {
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
