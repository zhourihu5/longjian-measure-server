package com.longfor.longjian.measure.app.req.paintAreaReq;

import lombok.Data;

/**
 * wangxs
 * 2018-11-22
 */
@Data
public class GetProjMeasureRegionTagReq {
    private String _ct;
    private Integer group_id;
    private String page_level;
    private Integer project_id;
    private Integer team_id;
    private Integer proj_id;
}
