package com.longfor.longjian.measure.domain.externalservice;

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
     * @param groupId
     * @param ownership
     * @return
     */
    List<Map<String,Object>> searchByGroupId(Integer groupId, Integer ownership);

    /**
     * 通过groupId,projId查询tag
     * wangxs
     * 2018-11-22 17:14
     * @param groupId
     * @param projId
     * @param ownership
     * @return
     */
    List<Map<String,Object>> searchByGroupIdAndProjId(Integer groupId, Integer projId, Integer ownership);

    /**
     *
     * @param groupId
     * @param editTagProtoVos
     * @param group
     */
    Integer editOnGroup(Integer groupId, List<EditTagProtoVo> editTagProtoVos, Integer group);

    /**
     *
     * @param groupId
     * @param nameList
     * @param group
     * @return
     */
    Integer addOnGroup(Integer groupId, List<String> nameList, Integer group,Integer projId);

    /**
     *
     * @param groupId
     * @param projectId
     * @param nameList
     * @param project
     * @return
     */
    Integer addOnProj(Integer groupId, Integer projectId, List<String> nameList, Integer project);

    /**
     *
     * @param groupId
     * @param projectId
     * @param editTagProtoVos
     * @param project
     * @return
     */
    Integer editOnProjId(Integer groupId, Integer projectId, List<EditTagProtoVo> editTagProtoVos, Integer project);
}
