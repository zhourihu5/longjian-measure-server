package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureListArea;

import java.util.List;

/**
 * Created by Wang on 2019/1/7.
 */
public interface IMeasureListAreaService {

   void delete (Integer id);

    List<MeasureListArea> searchListAreaByListIdIn(Integer projectId, List<Integer> listIds);

    /**
     *
     * @param projectId
     * @param id
     * @return
     */
    List<MeasureListArea> searchByListId(String projectId, Integer id);

    void create(int projId, Integer id, String s, Integer id1);
}
