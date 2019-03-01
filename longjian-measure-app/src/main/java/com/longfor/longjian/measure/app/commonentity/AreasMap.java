package com.longfor.longjian.measure.app.commonentity;

import com.longfor.longjian.measure.po.zhijian2.Area;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: DDC
 * @create: 2019-01-11 14:16
 **/
@Data
@SuppressWarnings("squid:S1068")
public class AreasMap {
    /**
     *
     */
    private Map<Integer, Area> areas;
    /**
     *
     */
    private List<Area> list;
}


