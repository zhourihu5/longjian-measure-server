package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AreaMapper extends LFMySQLMapper<Area> {
    /**
     *
     * @param projectId
     * @param areaId
     * @return
     */
    List<Map<String,Object>> selectByFatherId(@Param("projectId") String projectId, @Param("areaId") String areaId);

    /**
     *
     * @param areaIds
     * @return
     */
    List<Area> getAreaByIds(@Param("areaIds") List<Integer> areaIds);
}