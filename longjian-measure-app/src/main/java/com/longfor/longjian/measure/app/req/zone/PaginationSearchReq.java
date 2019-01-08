package com.longfor.longjian.measure.app.req.zone;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Wang on 2019/1/8.
 */
@Data
public class PaginationSearchReq {

    /**
     * 集团id
     */
    @NotNull
    private  Integer group_id;

    /**
     * 项目id
     */
    @NotNull
    private  Integer project_id;

    /**
     * 实测任务id
     */
    @NotNull
    private  Integer  list_id;

    /**
     * 页面数
     */
    @NotNull
    private  Integer page=1;

    /**
     * 每页的条目数
     */
    @NotNull
    private  Integer page_size;

    /**
     * 区域id列表
     */
    @NotBlank
    private  String area_id_list;

    /**
     * 检查项key列表
     */
    @NotBlank
    private  String category_key_list;


}
