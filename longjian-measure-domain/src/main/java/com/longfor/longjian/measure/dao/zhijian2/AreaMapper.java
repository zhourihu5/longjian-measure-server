package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AreaMapper extends LFMySQLMapper<Area> {
    /**
     *
     * @param project_id
     * @param area_id
     * @return
     */
    List<Map<String,Object>> selectByFatherId(@Param("projectId") String project_id, @Param("areaId") String area_id);

    /**
     *
     * @param areaIds
     * @return
     */
    List<Area> getAreaByIds(@Param("areaIds") List<Integer> areaIds);
}