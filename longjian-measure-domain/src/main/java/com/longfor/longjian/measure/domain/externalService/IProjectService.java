package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.Project;
import com.longfor.longjian.measure.po.zhijian2.MeasureList;

public interface IProjectService {
    /**
     *
     * @param projId
     * @return
     */
    Project GetByIdNoFoundErr(Integer projId);

    /**
     *
     * @param projId
     * @param list_id
     * @return
     */
    MeasureList getByProjIdAndIdNoFoundErr(Integer projId, Integer list_id);
}
