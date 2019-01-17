package com.longfor.longjian.measure.app.req.measureRegionReq;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Jiazm 2019/01/09 10:08
 */
@Data
@NoArgsConstructor
public class AddOnGroupReq implements Serializable {
    /**
     * 集团id
     */
    @NotNull
    private Integer group_id;
    /**
     * 标签名列表, 用逗号分隔的字符串
     */
    @NotNull
    private String  name_list;
    /**
     * 分页
     */
    private String page_level;
}
