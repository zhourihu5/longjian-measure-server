package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.CategoryV3;

/**
 * Jiazm
 * 2018/12/18 15:16
 */
public interface ICheckItemService {
    /**
     *
     * @param category_key
     * @return
     */
    CategoryV3 getCategoryByKeyNoFoundErr(String category_key);
}
