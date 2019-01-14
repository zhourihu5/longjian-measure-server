package com.longfor.longjian.measure.domain.externalService;

import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import com.longfor.longjian.measure.po.zhijian2.CheckItemV3;

public interface ICheckItemV3Service {
    /**
     *
     * @param subKey
     * @return
     */
    CheckItemV3 getCheckItemByKeyNoFoundErr(String subKey);

    /**
     *
     * @param subKey
     * @return
     */
    CategoryV3 getCategoryByKeyNoFoundErr(String subKey);

    /**
     *
     * @param rootCategoryId
     * @return
     */
    CategoryV3 getRootCategoryNoFoundErr(Integer rootCategoryId);
}
