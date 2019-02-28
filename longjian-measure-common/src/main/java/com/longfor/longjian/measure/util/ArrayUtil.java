package com.longfor.longjian.measure.util;

import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * Created by Wang on 2019/1/7.
 */
public class ArrayUtil {

    private ArrayUtil() {

    }

    /**
     * 根据不同类型  取数组差值
     *
     * @param arrayA
     * @param arrayB
     * @param type
     * @return
     */
    public static List<Integer> getDiff(Integer[] arrayA, Integer[] arrayB, String type) {
        if ("A-B".equals(type)) {//取数组A-B的值
            return getDiff(arrayA,arrayB);
        }
        if ("B-A".equals(type)) {//取数组B-A的值
            return getDiff(arrayB,arrayA);
        }
        return new ArrayList<>();
    }

    public static List<Integer> getDiff(Integer[] arrayA, Integer[] arrayB) {
        if(ArrayUtils.isEmpty(arrayA)) return new ArrayList<>();
        if(ArrayUtils.isEmpty(arrayB)) return Arrays.asList(arrayA);
        Set<Integer> setA = new HashSet<>(Arrays.asList(arrayA));
        Set<Integer> setB = new HashSet<>(Arrays.asList(arrayB));
        Set<Integer> setDiff = SetUtils.difference(setA,setB).toSet();
        return new ArrayList<>(setDiff);
    }

    /**
     * 比较2个set元素
     *
     * @param set1
     * @param set2
     * @return
     */
    public static boolean getSetDiff(Set<Integer> set1, Set<Integer> set2) {
        set1.removeAll(set2);
        if (set1.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

//    public static void main(String[] args) {
//        Integer[] arrayA= new Integer[]{12,32};
//        Integer[] arrayB = new Integer[]{};
//        System.out.println(getDiff(arrayA,arrayB,"A-B"));
//        System.out.println(getDiff(arrayA,arrayB,"B-A"));
//    }
}


