package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeasureSquadUserMapper extends LFMySQLMapper<MeasureSquadUser> {
    /**
     *
     * @param projectId
     * @param list_id
     * @param updateAtGt
     * @return
     */
    List<MeasureSquadUser> searchMeasureSquadUserByListIdTimestampGt(@Param("projectId") Integer projectId, @Param("listId") Integer list_id, @Param("updateAtGt") String updateAtGt);
}