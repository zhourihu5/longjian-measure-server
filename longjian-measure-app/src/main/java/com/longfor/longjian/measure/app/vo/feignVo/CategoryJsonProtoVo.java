package com.longfor.longjian.measure.app.vo.feignVo;

import lombok.Data;

/**
 * Created by Wang on 2019/1/9.
 */
@Data
public class CategoryJsonProtoVo {

    /**
     * ID
     */
    private Integer id;

    /**
     * Key
     */
    private String key;

    private String path ;
    private Integer cls ;
    private String father_key ;
    private String order ;
    private String custom_key ;
    private String   name ;
    private String desc ;
    private Integer node_status ;
}
