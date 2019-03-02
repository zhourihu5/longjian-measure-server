package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureSquadUser;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

public interface IMeasureSquadUserService {
    /**
     *
     * @param projectId
     * @param listId
     * @param updateAtGt
     * @return
     */
    List<MeasureSquadUser> searchMeasureSquadUserByListIdTimestampGt(Integer projectId, Integer listId, String updateAtGt);

    /**
     *
     * @param id
     */
    void delete(Integer id);

    /**
     *
     * @param measureSquadUser
     */
    void create(MeasureSquadUser measureSquadUser);

    /**
     *
     * @param measureSquadUser
     * @return
     */
    List<MeasureSquadUser> select(MeasureSquadUser measureSquadUser);

    /**
     *
     * @param map
     */
    void deleteOld(Map<String,Object> map);

    /**
     *
     * @param measureSquadUserList
     */
    void insertList(List<MeasureSquadUser>measureSquadUserList);

    /**
     *
     * @param measureSquadUser
     */
    void deleteMeasureSquadUser(MeasureSquadUser measureSquadUser);

    /**
     *
     * @param example
     * @return
     */
    List<MeasureSquadUser>selectByExample(Example example);

    /**
     *
     * @param projId
     * @param squadId
     * @return
     */
    List<MeasureSquadUser>SearchBySquadId(Integer projId,Integer squadId);
}
