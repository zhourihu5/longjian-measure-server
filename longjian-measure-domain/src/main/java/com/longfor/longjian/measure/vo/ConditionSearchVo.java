package com.longfor.longjian.measure.vo;

import lombok.Data;

/**
 * @Date 2019/3/1 16:37
 * Created by Jiazhongmin
 */
@Data
public class ConditionSearchVo {
    private Integer group_id;
    private Integer project_id;
    private Integer page;
    private Integer page_size;
    private String area_id;
    private String user_id_list;
    private Integer finish_status;
    private String name;
    private String category_key;
}
