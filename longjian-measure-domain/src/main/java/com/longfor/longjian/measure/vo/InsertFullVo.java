package com.longfor.longjian.measure.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Date 2019/3/1 15:05
 * Created by Jiazhongmin
 */
@Data
public class InsertFullVo {
    private Integer userId;
    private Integer teamId;
    private Integer projectId;
    private Integer exportType;
    private String params;
    private String resultFilePath;
    private String resultName;
    private int status;
    private String errorMsg;
    private Date executeAt;
}
