package com.longfor.longjian.measure.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.measure.po.zhijian2.CategoryV3;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Jiazm 2019/01/14 16:34
 */
@Data
public class CategoryUtils {
    private CategoryUtils() {
    }

    private static Map<String, CategoryV3> m = Maps.newHashMap();

    public static Map<String, CategoryV3> getM() {
        return m;
    }

    public static void setM(Map<String, CategoryV3> m) {
        CategoryUtils.m = m;
    }

    public static List<String> getFullNamesByKey(String categoryKey) {
        try {
            CategoryV3 categoryV3 = CategoryUtils.m.get(categoryKey);
            return getFullNames(categoryV3);
        } catch (Exception e) {
            return Lists.newArrayList();
        }

    }

    private static List<String> getFullNames(CategoryV3 categoryV3) {
        List<String> nameList = Lists.newArrayList();
        if (categoryV3 == null) {
            return Lists.newArrayList();
        }
        String[] keys = StringUtils.split(StringUtils.trim(categoryV3.getPath()), "/");
        for (String key : keys) {
            if (key.equals("")) {
                continue;
            }
            if (CategoryUtils.m.get(key) != null) {
                nameList.add(CategoryUtils.m.get(key).getName());
            }
        }
        nameList.add(categoryV3.getName());
        return nameList;
    }
}
