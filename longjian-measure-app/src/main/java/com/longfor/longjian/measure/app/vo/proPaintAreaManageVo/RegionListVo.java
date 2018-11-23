package com.longfor.longjian.measure.app.vo.proPaintAreaManageVo;

import lombok.Data;

import java.util.List;

/**
 *
 *     id = required.IntegerField(desc='描区id')
 *     area_id = required.IntegerField(desc='区域id')
 *     area_path_and_id = required.StringField(desc='区域id')
 *     drawing_md5 = required.StringField(desc='区域id')
 *     proj_id = required.IntegerField(desc='区域id')
 *     region_index = required.IntegerField(desc='区域id')
 *     src_type = required.IntegerField(desc='区域id')
 *     uuid = required.StringField(desc='区域id')
 *     polygon = required.MessageField(cls=PolygonProto, desc='坐标')
 *     rel = required.MessageField(cls=RelProto, desc='描区关系')
 *     tag_id_list = required.StringField(desc='标签列表以逗号分隔返回')
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
