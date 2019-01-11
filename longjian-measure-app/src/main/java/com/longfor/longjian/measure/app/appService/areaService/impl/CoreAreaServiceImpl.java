package com.longfor.longjian.measure.app.appService.areaService.impl;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.app.appService.areaService.ICoreAreaService;
import com.longfor.longjian.measure.app.feignClient.ICoreAreaFeignService;
import com.longfor.longjian.measure.app.req.feignReq.SearchByIdListReq;
import com.longfor.longjian.measure.app.vo.feignVo.AreaRetrieveVo;
import com.longfor.longjian.measure.app.vo.feignVo.ProjAreaSearchByIdListVo;
import com.longfor.longjian.measure.po.zhijian2.Area;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CoreAreaServiceImpl implements ICoreAreaService {

    @Resource
    ICoreAreaFeignService coreAreaFeignService;

    @Override
    public List<AreaRetrieveVo> searchByIdList(Integer project_id, List area_id_list) {
        SearchByIdListReq searchByIdListReq = new SearchByIdListReq();
        searchByIdListReq.setArea_id_list(area_id_list);
        searchByIdListReq.setProject_id(project_id);
        try {
            LjBaseResponse<ProjAreaSearchByIdListVo> ljBaseResponse = coreAreaFeignService.searchByIdList(searchByIdListReq);
            return ljBaseResponse.getData().getArea_list();
        }catch (Exception e){
            throw new LjBaseRuntimeException(500,"core_svr 调用失败");
        }
    }
}
