package com.longfor.longjian.measure.vo;

import com.longfor.longjian.measure.po.zhijian2.Area;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Jiazm 2019/01/18 15:19
 */
@Data
@NoArgsConstructor
public class AreaInfoWithExtendVo {
    private Area area;
    private boolean isParent;
    private List<String> pathNames;
}
