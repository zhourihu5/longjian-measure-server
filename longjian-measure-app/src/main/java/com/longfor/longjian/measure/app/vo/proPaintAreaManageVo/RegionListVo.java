package com.longfor.longjian.measure.app.vo.proPaintAreaManageVo;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionListVo {
    private Integer id;
    private Integer area_id;
    /**
     * 路径区域（暂勿使用）
     */
    private String  area_path_and_id;
    /**
     * 图纸MD5
     */
    private String  drawing_md5;
    private Integer project_id;
    private Integer region_index;
    /**
     * 来源类型 1=后台添加 2=客户端添加
     */
    private Integer src_type;
    private String  uuid;
    private PolygonVo polygon;
    private RelVo rel;
    private String tag_id_list;

    /**
     * 所属描画区域关联关系ID
     */
    private Integer rel_id;

    /**
     * 更新时间
     */
    private Integer update_at;
    /**
     * 删除时间
     */
    private Integer delete_at;
}
