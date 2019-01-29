package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.Area;
import com.longfor.longjian.measure.po.zhijian2.MeasureListArea;

import java.util.List;

/**
 * Created by Wang on 2019/1/7.
 */
public interface IMeasureListAreaService {

   void delete (Integer id);

    List<MeasureListArea> searchListAreaByListIdIn(Integer project_id, List<Integer> listIds);

    /**
     *
     * @param projectId
     * @param id
     * @return
     */
    List<MeasureListArea> searchByListId(String projectId, Integer id);
}
