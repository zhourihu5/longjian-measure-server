package com.longfor.longjian.measure.app.vo.measureListVo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.log4j.spi.RootCategory;

/**
 * Jiazm 2019/01/11 18:53
 */
@Data
@NoArgsConstructor
public class MeasureListSearchResultVo {
    private Integer id;// id
    private String name; // 名称
    private String top_areas = ""; // 区域范围
    private Integer issue_count = 0;// 问题数量
    private String root_category = "";  // 检查项
    private String finish_status = "";  // 完成状态
    private String close_status = ""; // 开关状态
    private Integer create_at = 0;// 创建时间
}
