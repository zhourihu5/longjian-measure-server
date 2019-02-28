package com.longfor.longjian.measure.app.vo.measurelistvo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MeasureListInfoVo {
    /**
     * 任务信息描述
     */
    private List<ListInfoVo> list_info;
    /**
     * 数据总量
     */
    private Integer total;
}
