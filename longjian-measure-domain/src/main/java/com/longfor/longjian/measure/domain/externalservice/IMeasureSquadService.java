package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface IMeasureSquadService {
    /**
     * 查询测量小组
     * @param projectId
     * @param measureListId
     * @return
     */
    List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer projectId, Integer measureListId);


    /**
     *
     * @param projectId
     * @param listId
     * @param updateAtGt
     * @return
     */
    List<MeasureSquad> searchMeasureSquadByListIdTimestampGt(Integer projectId, Integer listId, String updateAtGt);

    /**
     *
     * @param id
     */
    void delete (Integer id);

    /**
     *
     * @param measureSquad
     * @return
     */
    int create(MeasureSquad measureSquad);

    /**
     *
     * @param measureSquad
     * @return
     */
    int update (MeasureSquad measureSquad);

    /**
     *
     * @param example
     * @return
     */
    List<MeasureSquad>selectByExample(Example example);

    /**
     *
     * @param projId
     * @param listId
     * @return
     */
    List<MeasureSquad> SearchSquadByProjIdListId(Integer projId , Integer listId);

    /**
     *
     * @param measureSquad
     * @return
     */
    MeasureSquad createReturnSuqad(MeasureSquad measureSquad);

    /**
     *
     * @param example
     * @return
     */
    Integer selectByCount(Example example);
}
