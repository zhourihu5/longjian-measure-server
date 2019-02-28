package com.longfor.longjian.measure.app.vo.appmeasuresyncvo;

import lombok.Data;

import java.util.Date;

/**
 * Jiazm
 * 2018/12/14 17:12
 */
@Data
public class MeasureIssueLogVo {
    private Integer id;
    private String attachment_md5_list;
    private String category_key;
    private String desc;
    private String issue_uuid;
    private Integer list_id;
    private Integer project_id;
    private Integer sender_id;
    private Integer status;
    private Integer typ;
    private String uuid;
    private Date client_create_at;
    private Date create_at;
    private Date update_at;
    private Date delete_at;
    private String detail;
}
