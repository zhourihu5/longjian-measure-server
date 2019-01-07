package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureRepairerUser;

import java.util.List;
import java.util.Map;

public interface IMeasureRepairerUserService {
    /**
     *
     * @param projectId
     * @param list_id
     * @param updateAtGt
     * @return
     */
    List<MeasureRepairerUser> searchMeasureReparierUserByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt);


    List<MeasureRepairerUser>select(MeasureRepairerUser measureRepairerUser);

    void delOld(Map<String,Object> map);

    void insertList(List<MeasureRepairerUser>measureRepairerUsers);

}
