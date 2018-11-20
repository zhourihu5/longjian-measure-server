package com.longfor.longjian.measure.app.vo.appAnalysisVo;

import lombok.Data;

import java.util.List;

/**
 * wangxs
 * 2018-11-20 20:08
 */
@Data
public class ApiAreaInfoVo {
    private Integer id;
    private String uuid;
    private String project_id;
    private Integer categoryKey;
    private String categoryName;
    private String categoryPathAndKey;
    private Integer list;
    private String zoneUuid ;
    private Integer areaId ;
    private String areaName;
    private String areaPathAndId;
    private String areaPathName;
    private Integer squadId;
    private String squadName;
    private Integer senderId;
    private String senderName;
    private Integer repairerId;
    private String repairerName;
    private Integer typ;
    private Integer status;
    private List<LogVo> logs;
    private Integer planEndOn;
    private Integer endOn;
    private String drawingMd5;
    private Integer posX;
    private Integer posY;
    private String desc;
    private Integer condition;
    private String attachmentMd5List;
    private Integer lastAssigner;
    private Integer lastAssignerAt;
    private String destroyUser;
    private String destroyAt;
    private Integer closeStatus;
    private Integer closeUser;
    private Integer closeTime;
    private String clientCreateAt;
    private String regionUuid;
    private Integer polygonX;
    private Integer polygonY;
}
