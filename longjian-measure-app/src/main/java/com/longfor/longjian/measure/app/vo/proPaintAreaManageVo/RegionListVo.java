package com.longfor.longjian.measure.app.vo.proPaintAreaManageVo;

import lombok.Data;

/**
 * wangxs
 * 2018-11-20 9:56
 */
@Data
public class RegionListVo {
    private Integer id;
    private Integer area_id;
    private String  area_path_and_id;
    private String  drawing_md5;
    private Integer proj_id;
    private Integer region_index;
    private Integer src_type;
    private String  uuid;
    private PolygonVo polygon;
    private RelVo rel;
    private String tag_id_list;
}
