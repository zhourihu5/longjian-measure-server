package com.longfor.longjian.measure.app.vo;

import lombok.Data;

/**
 * @Date 2019/3/1 15:44
 * Created by Jiazhongmin
 */
@Data
public class MsgAppendVo {
    private String uuid;
    private String zoneUuid;
    private Integer projId;
    private Integer listId;
    private Integer senderId;
    private Integer areaId;
    private String areaPathAndId;
    private String categoryKey;
    private String categoryPathAndKey;
    private Integer okTotal;
    private Integer total;
    private Long timeAt;
}
