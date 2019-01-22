package com.longfor.longjian.measure.app.vo.appMeasureSyncVo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AreaProtoVo {
    /**
     * 区域id
     */
    private Integer id;
    /**
     *图纸md5
     */
    private String drawing_md5;
    /**
     *父级区域id
     */
    private Integer father_id;
    /**
     *区域全名
     */
    private String full_name;
    /**
     *区域位置
     */
    private String location;
    /**
     *该区域名称
     */
    private String name;
    /**
     *区域路径
     */
    private String path;
}
