package com.longfor.longjian.measure.domain.externalservice;

import com.longfor.longjian.measure.po.zhijian2.CategoryV3;

/**
 * Jiazm
 * 2018/12/18 15:16
 */
public interface ICheckItemService {
    /**
     *
     * @param categoryKey
     * @return
     */
    CategoryV3 getCategoryByKeyNoFoundErr(String categoryKey);
}
