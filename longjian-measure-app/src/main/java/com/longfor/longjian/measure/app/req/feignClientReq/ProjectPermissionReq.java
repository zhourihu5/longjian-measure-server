package com.longfor.longjian.measure.app.req.feignClientReq;

import lombok.Data;

@Data
public class ProjectPermissionReq {
    /**
     * 用户ID
     */
    private Integer user_id;
    /**
     * 项目ID
     */
    private Integer proj_id;
    /**
     * 权限点标题
     */
    private String per_title;
}
