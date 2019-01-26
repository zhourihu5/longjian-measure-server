package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.vo.EditTagProtoVo;

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

    /**
     *
     * @param group_id
     * @param editTagProtoVos
     * @param group
     */
    Integer editOnGroup(Integer group_id, List<EditTagProtoVo> editTagProtoVos, Integer group);

    /**
     *
     * @param group_id
     * @param nameList
     * @param group
     * @return
     */
    Integer addOnGroup(Integer group_id, List<String> nameList, Integer group,Integer proj_id);

    /**
     *
     * @param group_id
     * @param project_id
     * @param nameList
     * @param project
     * @return
     */
    Integer addOnProj(Integer group_id, Integer project_id, List<String> nameList, Integer project);

    /**
     *
     * @param group_id
     * @param project_id
     * @param editTagProtoVos
     * @param project
     * @return
     */
    Integer editOnProjId(Integer group_id, Integer project_id, List<EditTagProtoVo> editTagProtoVos, Integer project);
}
