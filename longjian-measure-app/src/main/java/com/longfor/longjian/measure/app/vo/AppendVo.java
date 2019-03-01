package com.longfor.longjian.measure.app.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Date 2019/3/1 14:30
 * Created by Jiazhongmin
 */
@Data
public class AppendVo {
    private String uuid;
    private Integer projId;
    private Integer listId;
    private Integer checkerId;
    private Integer repairerId;
    private Integer areaId;
    private String areaPathAndId;
    private String categoryKey;
    private String categoryPathAndKey;
    private Integer senderId;
    private Date timeAt;
}
