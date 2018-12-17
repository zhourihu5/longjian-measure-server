package com.longfor.longjian.measure.dao.zhijian2;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.longjian.measure.po.zhijian2.MeasureRepairerUser;

import java.util.List;

public interface MeasureRepairerUserMapper extends LFMySQLMapper<MeasureRepairerUser> {
    /**
     *
     * @param projectId
     * @param list_id
     * @param updateAtGt
     * @return
     */
    List<MeasureRepairerUser> searchMeasureReparierUserByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt);
}