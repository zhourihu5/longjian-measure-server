package com.longfor.longjian.measure.app.req.proMeasureQuickSearchReq;

import lombok.Data;

@Data
public class GetQuickSearchPlanListReq {
    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English
     */
    private Integer Lang;
    /**
     * // 项目ID
     */
    private Integer project_id;
}
