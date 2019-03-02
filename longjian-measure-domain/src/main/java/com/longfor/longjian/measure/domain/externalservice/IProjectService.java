package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import com.longfor.longjian.measure.po.zhijian2.Project;

public interface IProjectService {
    /**
     *
     * @param projId
     * @return
     */
    Project getByIdNoFoundErr(Integer projId);

    /**
     *
     * @param projId
     * @param listId
     * @return
     */
    MeasureList getByProjIdAndIdNoFoundErr(Integer projId, Integer listId);
}
