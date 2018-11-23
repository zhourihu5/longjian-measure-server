package com.longfor.longjian.measure.domain.externalService;

import java.util.List;
import java.util.Map;

/**
 * wangxs
 * 2018-11-22
 */
public interface IMeasureTagService {
    /**
     * 通过groupId查询tag
     * wangxs
     * 2018-11-22 17:14
     * @param group_id
     * @param ownership
     * @return
     */
    List<Map<String,Object>> searchByGroupId(Integer group_id, Integer ownership);

    /**
     * 通过groupId,projId查询tag
     * wangxs
     * 2018-11-22 17:14
     * @param group_id
     * @param proj_id
     * @param ownership
     * @return
     */
    List<Map<String,Object>> searchByGroupIdAndProjId(Integer group_id, Integer proj_id, Integer ownership);
}
