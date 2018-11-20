package com.longfor.longjian.measure.app.vo.commonVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20 10:24
 */
@Data
public class TaskVo {
    private String id;
    private String name;
    private String product;
    private String product_name;
    private String typ;
    private String typ_name;
    private Integer status;
    private String status_msg;
    private String down_url;
    private Long create_at;
    private Long finish_at;
}
