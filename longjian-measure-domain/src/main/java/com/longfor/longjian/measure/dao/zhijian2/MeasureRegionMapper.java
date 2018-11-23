package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeasureRegionMapper extends LFMySQLMapper<MeasureRegion> {
    /**
     * 项目描画区域管理新增描画区域请求测区
     * @param project_id
     * @param area_id
     * @return
     */
    List<Map<String,Object>> getProjMeasureRegionByAreaId(@Param("projId") Integer project_id, @Param("areaId") Integer area_id);
}