package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface MeasureRegionRelMapper extends LFMySQLMapper<MeasureRegionRel> {
    /**
     *  查询rel Info 通过id
     * @param rel_id
     * @return
     */
    Map<String,Object> selectByRelId(@Param("relId") String rel_id);
}