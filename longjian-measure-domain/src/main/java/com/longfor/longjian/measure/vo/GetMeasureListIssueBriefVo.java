package com.longfor.longjian.measure.vo;

import lombok.Data;

/**
 * @Date 2019/3/1 15:13
 * Created by Jiazhongmin
 */
@Data
public class GetMeasureListIssueBriefVo {
    private Integer project_id;
    private Integer measure_list_id;
    private String UNCLOSECODE;
    private String REPAIRABLE;
    private String NOREPAIRABLE;
    private String NOTENOASSIGN;
    private String ASSIGNNOREFORM;
    private String REFORMNOCHECK;
    private String CHECKYES;
}
