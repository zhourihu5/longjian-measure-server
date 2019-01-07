package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MeasureSquadUserMapper extends LFMySQLMapper<MeasureSquadUser> {
    /**
     *
     * @param projectId
     * @param list_id
     * @param updateAtGt
     * @return
     */
    List<MeasureSquadUser> searchMeasureSquadUserByListIdTimestampGt(@Param("projectId") Integer projectId, @Param("listId") Integer list_id, @Param("updateAtGt") String updateAtGt);

    /**
     *
     * @param currentProjectId
     * @param listIds
     * @return
     */
    List<MeasureSquadUser> searchMeasureSquadUserByListIds(@Param("currentProjectId") Integer currentProjectId, @Param("listIds") Set<Integer> listIds);

    /**
     *
     * @param map
     */
    void deleteOld(Map<String,Object> map);
}