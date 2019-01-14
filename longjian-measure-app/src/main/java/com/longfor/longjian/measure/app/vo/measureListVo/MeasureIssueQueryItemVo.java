package com.longfor.longjian.measure.app.vo.measureListVo;

import com.longfor.longjian.measure.po.zhijian2.MeasureList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Jiazm 2019/01/11 18:10
 */
@Data
@NoArgsConstructor
public class MeasureIssueQueryItemVo {
    private Integer id; // 编号
    private String uuid;// 唯一编号
    private Integer project_id;// 项目id
    private String area_info; // 区域信息
    private Integer region_id; // 描画区域id
    private List<String> category_path_names;// 检查项名称路径
    private List<String> area_path_names; // 区域名称路径
    private Integer create_at; // 创建时间戳
    private Integer plan_end_on;// 整改期限时间戳
    private Integer status;// 问题状态
    private Integer close_status;// 关闭or开启状态
    private MeasureListSearchResultVo measure_list; // 清单
    private UserInfoVo repairer;// 整改人
}
