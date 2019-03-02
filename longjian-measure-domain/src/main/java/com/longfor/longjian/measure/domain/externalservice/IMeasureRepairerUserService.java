package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureRepairerUser;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

public interface IMeasureRepairerUserService {
    /**
     *
     * @param projectId
     * @param listId
     * @param updateAtGt
     * @return
     */
    List<MeasureRepairerUser> searchMeasureReparierUserByListIdTimestampGt(Integer projectId, Integer listId, String updateAtGt);


    /**
     *
     * @param measureRepairerUser
     * @return
     */
    List<MeasureRepairerUser>select(MeasureRepairerUser measureRepairerUser);

    /**
     *
     * @param map
     */
    void delOld(Map<String,Object> map);

    /**
     *
     * @param measureRepairerUsers
     */
    void insertList(List<MeasureRepairerUser>measureRepairerUsers);

    /**
     *
     * @param example
     * @return
     */
    List<MeasureRepairerUser> selectByExample(Example example);

    /**
     *
     * @param projId
     * @param listId
     * @return
     */
    List<MeasureRepairerUser> searchMeasureReparierUserByListId(Integer projId,Integer listId);

    /**
     *
     * @param projId
     * @param id
     * @param roleType
     * @param userId
     */
    void create(int projId, Integer id, String roleType, Integer userId);
}
