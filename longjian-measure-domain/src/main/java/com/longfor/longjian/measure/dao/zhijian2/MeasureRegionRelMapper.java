package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import com.longfor.longjian.measure.vo.MeasureRegionRelVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeasureRegionRelMapper extends LFMySQLMapper<MeasureRegionRel> {
    /**
     *  查询rel Info 通过id
     * @param relId
     * @return
     */
    Map<String,Object> selectByRelId(@Param("relId") String relId);

    /**
     *
     * @param projectId
     * @param updateAtGte
     * @return
     */
    List<MeasureRegionRel> searchRelUnscopedByProjIdUpdateAtGt(@Param("projectId") String projectId, @Param("updateAtGte") String updateAtGte);


    /**
     *
     * @param projectId
     * @param lastId
     * @param timestamp
     * @param measureApiGetPerTime
     * @param start
     * @return
     */
    List<MeasureRegionRel> searchRelUnscopedByProjIdLastIdUpdateAtGt(@Param("projectId") Integer projectId, @Param("lastId") Integer lastId, @Param("timestamp") Long timestamp, @Param("measureApiGetPerTime") Integer measureApiGetPerTime, @Param("start") Integer start);

    MeasureRegionRelVo selectById(Integer id);
}