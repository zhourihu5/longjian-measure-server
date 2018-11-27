package com.longfor.longjian.measure.app.req.proMeasureManagerReq;

import lombok.Data;

@Data
public class GetCheckerListReq {
    private Integer project_id;
    private Integer _;

    /**
     * 语言 取值范围及含义：;1 - 简体中文; ;2 - English
     */
    private Integer lang;
}
