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
     * @param projectId
     * @param areaId
     * @return
     */
    Area getAreaByProjIdAndAreaId(Integer projectId, Integer areaId);

    /**
     *
     * @param projectId
     * @param areaId
     * @return
     */
    List<Map<String,Object>> getProMeasureAreaListByFatherId(String projectId, String areaId);

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
     * @param projectId
     * @param areaIds
     * @return
     */
    List<Area> searchByIdList(Integer projectId, List<Integer> areaIds);

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
