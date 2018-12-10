package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

@Data
public class GetAreaPOPReq {
    /**
     * 项目ID
     */
    private Integer project_id;
    /**
     * 任务ID，逗号隔开
     */
    private String list_ids;
    /**
     * 区域ID，逗号隔开
     */
    private String area_ids;
    /**
     * 父级检查项key
     */
    private String parent_category_key;
}
