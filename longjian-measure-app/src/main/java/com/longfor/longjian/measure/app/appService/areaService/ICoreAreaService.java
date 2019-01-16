package com.longfor.longjian.measure.app.appService.areaService;

import com.longfor.longjian.measure.app.commonEntity.AreasMap;
import com.longfor.longjian.measure.app.vo.feignVo.AreaRetrieveVo;

import java.util.List;

public interface ICoreAreaService {

    List<AreaRetrieveVo> searchByIdList(Integer project_id, List area_id_list);

    AreasMap createAreasMapByLeaveIds(List<Integer> ids);

}
