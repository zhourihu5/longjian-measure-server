package com.longfor.longjian.measure.app.appservice.areaservice;

import com.longfor.longjian.measure.app.commonentity.AreasMap;
import com.longfor.longjian.measure.app.vo.feignvo.AreaRetrieveVo;

import java.util.List;

public interface ICoreAreaService {

    List<AreaRetrieveVo> searchByIdList(Integer project_id, List area_id_list);

    AreasMap createAreasMapByLeaveIds(List<Integer> ids);

}
