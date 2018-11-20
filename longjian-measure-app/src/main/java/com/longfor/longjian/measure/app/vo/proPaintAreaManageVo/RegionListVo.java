package com.longfor.longjian.measure.app.vo.proPaintAreaManageVo;

import lombok.Data;

import java.util.List;

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
    private List<RelVo> rel;
    private String tag_id_list;


    /**
     * app端
     * 获取指定项目的描画区域，此为全量接口
     */
    private Integer project_id;
    private Integer rel_id;
    private Integer update_at;
    private Integer delete_at;

    /**
     * app端
     */
    private String region_ids;

}
