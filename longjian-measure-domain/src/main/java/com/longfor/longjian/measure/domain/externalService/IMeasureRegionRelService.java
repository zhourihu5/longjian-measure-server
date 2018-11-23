package com.longfor.longjian.measure.domain.externalService;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface IMeasureRegionRelService {
    /**
     * 查询rel Info 通过id
     * @param rel_id
     * @return
     */
    Map<String,Object> selectByRelId(String rel_id);
}
