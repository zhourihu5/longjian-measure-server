package com.longfor.longjian.measure.app.controller.measurev1papicontroller;

import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appservice.paintareaservice.IProPaintAreaManageService;
import com.longfor.longjian.measure.app.req.measureregionreq.AddOnGroupReq;
import com.longfor.longjian.measure.app.req.measureregionreq.AddOnProjReq;
import com.longfor.longjian.measure.app.req.measureregionreq.EditByProjIdReq;
import com.longfor.longjian.measure.app.req.measureregionreq.EditOnGroupReq;
import com.longfor.longjian.measure.app.req.paintareareq.GetGroupMeasureRegionTagReq;
import com.longfor.longjian.measure.app.req.paintareareq.GetProjMeasureRegionTagReq;
import com.longfor.longjian.measure.app.vo.propaintareamanagevo.GroupRegionTagVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * app_measure服务
 * 项目实测描画区域管理
 * 项目实测实量任务列表
 * wangxs
 * 2019-1-7
 */
@RestController
@RequestMapping("measure/v1/papi/measure_region_tag/")
@Slf4j
public class MeasureRegionTagController {

    @Autowired
    private IProPaintAreaManageService proPaintAreaManageService;

    /**
     * 集团描画区域新增加描画区域请求标签
     * http://192.168.37.159:3000/project/8/interface/api/712
     * @param getGroupMeasureRegionTagReq
     * @return
     */
    @RequestMapping(value = "search_by_group_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(@Valid GetGroupMeasureRegionTagReq getGroupMeasureRegionTagReq) throws LjBaseRuntimeException {
        return proPaintAreaManageService.getGroupMeasureRegionTag(getGroupMeasureRegionTagReq);
    }

    /**
     * 项目描画区域新增加描画区域请求标签
     * http://192.168.37.159:3000/project/8/interface/api/712
     * @param getProjMeasureRegionTagReq
     * @return
     */
    @RequestMapping(value = "search_by_proj_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<GroupRegionTagVo> getGroupMeasureRegionTag(@Valid GetProjMeasureRegionTagReq getProjMeasureRegionTagReq) throws LjBaseRuntimeException {
        return proPaintAreaManageService.getProjMeasureRegionTag(getProjMeasureRegionTagReq);
    }

    /**集团实测描画区域标签管理
     * http://192.168.37.159:3000/project/8/interface/api/2816
     * http://192.168.37.159:3000/mock/8/longjian.longhu.net/measure/v1/papi/measure_region_tag/edit_by_group_id/?_ct=json
     * @return
     */
    @RequestMapping(value = "edit_by_group_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<Object> editByGroupId(@Valid EditOnGroupReq editOnGroupReq) {
        return proPaintAreaManageService.editOnGroup(editOnGroupReq);
    }


    /**
     *集团实测描画区域标签管理添加标签
     * http://192.168.37.159:3000/project/8/interface/api/2824
     * http://192.168.37.159:3000/mock/8/longjian.longhu.net/measure/v1/papi/measure_region_tag/add_on_group/?_ct=json
     * @return
     */
    @RequestMapping(value = "add_on_group/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<Object> addOnGroup(@Valid AddOnGroupReq addOnGroupReq) {
        return proPaintAreaManageService.addOnGroup(addOnGroupReq);
    }

    /**
     *项目描画区域管理新增加描画区域添加标签
     * http://192.168.37.159:3000/project/8/interface/api/2872
     * http://192.168.37.159:3000/mock/8/longjian.longhu.net/measure/v1/papi/measure_region_tag/add_on_proj/?_ct=json
     * @return
     */
    @RequestMapping(value = "add_on_proj/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<Object> addOnProj(@Valid AddOnProjReq addOnProjReq) {
        return proPaintAreaManageService.addOnProj(addOnProjReq);
    }


    /**
     *项目描画区域管理新增描画区域修改标签
     * http://192.168.37.159:3000/project/8/interface/api/2876
     * http://192.168.37.159:3000/mock/8/longjian.longhu.net/measure/v1/papi/measure_region_tag/edit_by_proj_id/?_ct=json
     * @return
     */
    @RequestMapping(value = "edit_by_proj_id/" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LjBaseResponse<Object> editByProjId(@Valid EditByProjIdReq editByProjId) {
        return proPaintAreaManageService.editByProjId(editByProjId);
    }





}
