package com.longfor.longjian.measure.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;

@Slf4j
public class StringUtil {
    private StringUtil() {
        throw new UnsupportedOperationException();
    }

    public static String[] getPathSlice(String keyPath) {
        if (keyPath.charAt(0) == '/') {
            keyPath = keyPath.substring(1);
        }
        if (keyPath.charAt(keyPath.length() - 1) == '/') {
            keyPath = keyPath.substring(0, keyPath.length() - 1);
        }
        return keyPath.split("/");
    }

    public static String keysToPath(String[] keys) {
        if (keys.length > 0) {
            String result = "";
            if (keys.length == 1) {
                return "/" + keys[0] + "/";
            } else {
                for (String s : keys
                ) {
                    result += s + "/";
                }
                return "/" + result;
            }
        }
        return "/";
    }

    /**
     * 比较两个Id序列的大小
     *
     * @param a
     * @param b
     * @param sep
     * @return
     */
    public static Integer compareIdList(String a, String b, String sep) {
        if (a.equals(b)) {
            return 0;
        }
        try {
            String[] la = StringUtils.split(a, sep);
            String[] lb = StringUtils.split(b, sep);
            Integer lengtha = la.length;
            Integer lengthb = lb.length;
            Integer length = null;
            length = lengtha;
            if (lengthb < length) {
                length = lengthb;
            }
            for (int i = 0; i < length; i++) {
                String ia = la[i];
                String ib = lb[i];
                if (ia.equals(ib)) {
                    continue;
                }
                return Integer.parseInt(ia) - Integer.parseInt(ib);
            }
            return lengtha - lengthb;
        } catch (Exception e) {
            log.error("error:" + e.getMessage());
            return 0;
        }
    }
    //比较两个Id序列的大小

    /**
     * 删除首尾字符进行分割
     * @param str
     * @param removeSep
     * @param splitSep
     * @return
     */
    public static List<String> removeStartAndEndStrAndSplit(String str, String removeSep, String splitSep) {
        if (StringUtils.isEmpty(str)) {
            return Lists.newArrayList();
        }
        String s = StringUtils.removeStart(str, removeSep);
        String s1 = StringUtils.removeEnd(s, removeSep);
        return splitToStringComma(s1, splitSep);
    }

    /**
     *
     * @param ids
     * @param sep
     * @return
     */
    public static List<String> splitToStringComma(String ids, String sep) {
        if (ids == null) return Lists.newArrayList();
        String[] str = ids.split(sep);
        List<String> list = Lists.newArrayList();
        Collections.addAll(list, str);
        return list;
    }
}
