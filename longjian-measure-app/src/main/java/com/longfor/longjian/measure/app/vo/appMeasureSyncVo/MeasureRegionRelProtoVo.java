package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import com.longfor.longjian.measure.app.vo.proMeasureVo.AreaInfoVo;
import com.longfor.longjian.measure.app.vo.proPaintAreaManageVo.PolygonVo;
import com.longfor.longjian.measure.po.zhijian2.Area;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jiazm 2019/01/09 10:45
 */
@Data
@NoArgsConstructor
public class MeasureRegionRelProtoVo {
    /**
     * 区域信息
     */
    private AreaProtoVo area;
    /**
     *区域id
     */
    private Integer area_id;
    /**
     *区域路径和id
     */
    private String area_path_and_id ;
    /**
     *图纸MD5
     */
    private String drawing_md5;
    /**
     *描区id
     */
    private Integer id;
    /**
     *项目id
     */
    private Integer proj_id;
    /**
     *点("X,Y")
     */
    private PolygonVo polygon;
    /**
     *描区唯一编码
     */
    private String uuid;
    /**
     *编号
     */
    private Integer region_index;
    /**
     *所属描画区域关联关系id
     */
    private Integer rel_id;
    /**
     *来源类型 1=后台添加 2=客户端添加
     */
    private Integer src_type;
    /**
     *描区标签id列表
     */
    private String tag_id_list;
}
