package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;

import java.util.List;

public interface IMeasureSquadUserService {
    /**
     *
     * @param projectId
     * @param list_id
     * @param updateAtGt
     * @return
     */
    List<MeasureSquadUser> searchMeasureSquadUserByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt);
}
