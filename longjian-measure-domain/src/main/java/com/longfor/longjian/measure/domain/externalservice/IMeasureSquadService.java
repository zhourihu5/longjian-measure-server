package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureSquad;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface IMeasureSquadService {
    /**
     * 查询测量小组
     * @param project_id
     * @param measure_list_id
     * @return
     */
    List<MeasureSquad> searchOnlyMeasureSquadByProjIdAndListId(Integer project_id, Integer measure_list_id);


    /**
     *
     * @param projectId
     * @param list_id
     * @param updateAtGt
     * @return
     */
    List<MeasureSquad> searchMeasureSquadByListIdTimestampGt(Integer projectId, Integer list_id, String updateAtGt);

    /**
     *
     * @param id
     */
    void delete (Integer id);

    int create(MeasureSquad measureSquad);

    int update (MeasureSquad measureSquad);

    /**
     *
     * @param example
     * @return
     */
    List<MeasureSquad>selectByExample(Example example);

    List<MeasureSquad> SearchSquadByProjIdListId(Integer projId , Integer listId);

    MeasureSquad createReturnSuqad(MeasureSquad measureSquad);

    /**
     *
     * @param example
     * @return
     */
    Integer selectByCount(Example example);
}
