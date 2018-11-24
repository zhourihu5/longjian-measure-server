package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.CategoryV3;

public interface ICategoryService {
    /**
     *通过key查对象
     * @param category_key
     * @return
     */
    CategoryV3 getCategoryByKey(String category_key);
}
