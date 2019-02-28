package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureRepairerUser;
import tk.mybatis.mapper.entity.Example;

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

    List<MeasureRepairerUser> selectByExample(Example example);

    List<MeasureRepairerUser> SearchMeasureReparierUserByListId(Integer projId,Integer listId);

    void create(int proj_id, Integer id, String role_type, Integer user_id);
}
