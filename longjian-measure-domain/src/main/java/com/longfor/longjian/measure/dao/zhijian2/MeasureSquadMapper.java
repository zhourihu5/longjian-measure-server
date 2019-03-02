package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeasureSquadMapper extends LFMySQLMapper<MeasureSquad> {

    /**
     *
     * @param projectId
     * @param lastId
     * @param updateAtGt
     * @return
     */
    List<MeasureSquad> searchMeasureSquadByListIdTimestampGt(@Param("projectId") Integer projectId, @Param("listId") Integer lastId, @Param("updateAtGt") String updateAtGt);


    int insertMeasureSquad(MeasureSquad measureSquad);

    List<MeasureSquad> searchSquadByProjIdListId(@Param("projId") Integer projId,@Param("listId") Integer listId);
}