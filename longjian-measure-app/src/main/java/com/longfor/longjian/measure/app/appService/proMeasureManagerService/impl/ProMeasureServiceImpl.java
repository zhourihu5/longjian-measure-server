package com.longfor.longjian.measure.app.appService.proMeasureManagerService.impl;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.measure.app.appService.proMeasureManagerService.IProMeasureService;
import com.longfor.longjian.measure.app.req.proMeasureManagerReq.GetProMeasurePlanListReq;
import com.longfor.longjian.measure.app.vo.ItemsVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.ProMeasurePlanListVo;
import com.longfor.longjian.measure.app.vo.proMeasureVo.ProMeasurePlanVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.TagListVo;
import com.longfor.longjian.measure.consts.util.ConvertUtil;
import com.longfor.longjian.measure.domain.externalService.IAreaService;
import com.longfor.longjian.measure.domain.externalService.ICategoryService;
import com.longfor.longjian.measure.domain.externalService.IMeasureListService;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.spi.SyncResolver;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
@Slf4j
public class ProMeasureServiceImpl implements IProMeasureService {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IMeasureListService measureListService;

    @Override
    public LjBaseResponse<ProMeasurePlanListVo> getProMeasurePlanList(GetProMeasurePlanListReq getProMeasurePlanListReq) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        LjBaseResponse<ProMeasurePlanListVo> ljBaseResponse = new LjBaseResponse<>();
        ProMeasurePlanListVo proMeasurePlanListVo = new ProMeasurePlanListVo();
        // todo 权限鉴定（通过http请求公共方法），获取项目（包括用户？）信息等，暂时项目id从前端获取
        String [] userIds = null;
        if (StringUtils.isNotBlank(getProMeasurePlanListReq.getUser_ids())){
            userIds = getProMeasurePlanListReq.getUser_ids().split(",");
        }
        //categoryPathAndKey 参数赋值
        String categoryPathAndKey = getCategoryPathAndKey(getProMeasurePlanListReq);
        //areaPathAndId 参数赋值
        String areaPathAndId = getAreaPathAndId(getProMeasurePlanListReq);
        //获取measureList
        List<ProMeasurePlanVo> list = SearchByProjIdCategoryKeyAreaIdStatusUserIdInPage(getProMeasurePlanListReq,userIds,categoryPathAndKey,areaPathAndId);
        //获取total
        Integer total = measureListService.getTotalMeasure(getProMeasurePlanListReq.getFinish_status(),getProMeasurePlanListReq.getQ(),getProMeasurePlanListReq.getProject_id(),categoryPathAndKey,areaPathAndId,userIds);
        proMeasurePlanListVo.setItems(list);
        proMeasurePlanListVo.setTotal(total);
        ljBaseResponse.setData(proMeasurePlanListVo);
        return ljBaseResponse;
    }

    /**
     *areaPathAndId 参数赋值
     * @param getProMeasurePlanListReq
     * @return
     */
    private String getAreaPathAndId(GetProMeasurePlanListReq getProMeasurePlanListReq) {
        //areaPathAndId 参数赋值
        String areaPathAndId = "";
        if (getProMeasurePlanListReq.getArea_id() != null){
            //查找子集
            Area area = areaService.getAreaByProjIdAndAreaId(getProMeasurePlanListReq.getProject_id(),getProMeasurePlanListReq.getArea_id());
            if (area != null){
                areaPathAndId = area.getPath() + area.getId() + "/";
            }
        }
        return areaPathAndId;
    }

    /**
     * categoryPathAndKey 参数赋值
     * @param getProMeasurePlanListReq
     * @return
     */
    private String getCategoryPathAndKey(GetProMeasurePlanListReq getProMeasurePlanListReq) {
        //categoryPathAndKey 参数赋值
        String categoryPathAndKey = "";
        if (StringUtils.isNoneBlank(getProMeasurePlanListReq.getCategory_key())){
            //查找子集
            CategoryV3 categoryV3 = categoryService.getCategoryByKey(getProMeasurePlanListReq.getCategory_key());
            if (categoryV3 != null){
                categoryPathAndKey = categoryV3.getPath() + categoryV3.getKey() + "/";
            }
        }
        return categoryPathAndKey;
    }

    /**
     * 获取任务列表
     * @param getProMeasurePlanListReq
     * @param userIds
     * @param categoryPathAndKey
     * @param areaPathAndId
     * @return
     */
    private List<ProMeasurePlanVo> SearchByProjIdCategoryKeyAreaIdStatusUserIdInPage(GetProMeasurePlanListReq getProMeasurePlanListReq, String[] userIds, String categoryPathAndKey, String areaPathAndId) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        List<ProMeasurePlanVo> measurePlanVoList = new ArrayList<>();
        //查询 MeasureList
        List<Map<String,Object>> list = measureListService.getMeasureList(getProMeasurePlanListReq.getFinish_status(),getProMeasurePlanListReq.getQ(),getProMeasurePlanListReq.getProject_id(),categoryPathAndKey,areaPathAndId,userIds,getProMeasurePlanListReq.getPage(),getProMeasurePlanListReq.getPage_size());
        for (Map<String,Object> map: list
                ) {
            //map转换成vo
            ProMeasurePlanVo measurePlanVo = (ProMeasurePlanVo) ConvertUtil.convertMap(ProMeasurePlanVo.class,map);
            if ("1".equals(map.get("closeStatusId").toString())){
                measurePlanVo.setClose_status("打开");
            }
            if ("1".equals(map.get("finishStatusId").toString())){
                measurePlanVo.setFinish_status("进行中");
            }
            measurePlanVo.setCreate_at(111111);
            // todo area 数据处理
            measurePlanVoList.add(measurePlanVo);
        }
        return measurePlanVoList;
    }
}
