package com.longfor.longjian.measure.app.req.MeasureList;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Jiazm 2019/01/11 17:55
 */
@Data
@NoArgsConstructor
public class MeasureIssueQueryReq {

    private Integer lang;           // 语言 取值范围及含义：;1 - 简体中文; ;2 - English;
    @NotNull
    private Integer project_id;      // 项目id
    @NotNull
    private Integer limit;       // PageSize
    @NotNull
    private Integer page;                 // CurrentPage
    @NotNull
    private String category_key;    // 检查项ID
    @NotNull
    private String area_ids;        // 区域ids
    @NotNull
    private String measure_list_ids; // 清单ids
    @NotNull
    private String create_at_range;   // 创建时间范围
    @NotNull
    private String is_overdue;        // 整改超期
    @NotNull
    private Integer repairer_id;          // 整改人Id
    @NotNull
    private Integer status;          // 问题状态
}
