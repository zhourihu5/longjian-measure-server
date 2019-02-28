package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.vo.AreaInfoWithExtendVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IAreaService {
    /**
     *
     * @param project_id
     * @param area_id
     * @return
     */
    Area getAreaByProjIdAndAreaId(Integer project_id, Integer area_id);

    /**
     *
     * @param project_id
     * @param area_id
     * @return
     */
    List<Map<String,Object>> getProMeasureAreaListByFatherId(String project_id, String area_id);

    /**
     *
     * @param areaId
     * @return
     */
    Area getAreaById(String areaId);

    /**
     *
     * @param areaIds
     * @return
     */
    List<Area> getAreaByIds(List<Integer> areaIds);

    /**
     *
     * @param proj_id
     * @param areaIds
     * @return
     */
    List<Area> searchByIdList(Integer proj_id, List<Integer> areaIds);

    /**
     *
     * @param keySet
     * @return
     */
    List<Area> selectByIds(Set<Integer> keySet);

    /**
     *
     * @param areas
     * @return
     */
    List<AreaInfoWithExtendVo> formatAreaInfoWithExtend(List<Area> areas) throws LjBaseRuntimeException;
}
