package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeasureSquadMapper extends LFMySQLMapper<MeasureSquad> {

    /**
     *
     * @param projectId
     * @param list_id
     * @param updateAtGt
     * @return
     */
    List<MeasureSquad> searchMeasureSquadByListIdTimestampGt(@Param("projectId") Integer projectId, @Param("listId") Integer list_id, @Param("updateAtGt") String updateAtGt);

}