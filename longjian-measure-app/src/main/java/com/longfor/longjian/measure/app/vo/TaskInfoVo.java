package com.longfor.longjian.measure.app.vo;

import lombok.Data;

/**
 * wangxs
 * 2019-2-21 14:06
 */
@Data
public class TaskInfoVo {
    private String id;
    private String name;
    private String product;
    private String product_name;
    private String typ;
    private String typ_name;
    private Integer status;
    private String status_msg;
    private String down_url;
    private Integer create_at;
    private Integer finish_at;
}
