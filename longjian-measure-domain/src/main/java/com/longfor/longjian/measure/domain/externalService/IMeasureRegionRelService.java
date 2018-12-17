package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.MeasureRegion;
import com.longfor.longjian.measure.po.zhijian2.MeasureRegionRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IMeasureRegionRelService {
    /**
     * 查询rel Info 通过id
     * @param rel_id
     * @return
     */
    Map<String,Object> selectByRelId(String rel_id);

    /**
     *
     * @param projectId
     * @param updateAtGte
     * @return
     */
    List<MeasureRegionRel> searchRelUnscopedByProjIdUpdateAtGt(String projectId, String updateAtGte);

}
