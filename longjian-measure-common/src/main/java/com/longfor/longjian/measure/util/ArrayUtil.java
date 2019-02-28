package com.longfor.longjian.measure.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Wang on 2019/1/7.
 */
public class ArrayUtil {

    /**
     * 根据不同类型  取数组差值
     *
     * @param arrayA
     * @param arrayB
     * @param type
     * @return
     */
    public static List<Integer> getDiff(Integer[] arrayA, Integer[] arrayB, String type) {
        arrayA = arrayToDistinct(arrayA);
        arrayB = arrayToDistinct(arrayB);
        List newList = new ArrayList<>();
        if ("A-B".equals(type)) {//取数组A-B的值
            List<Integer> aList = Arrays.asList(arrayA);
            boolean flag = false;
            if (arrayB != null && arrayB.length > 0) {
                for (int i = 0; i < arrayB.length; i++) {
                    if (aList.contains(arrayB[i])) {
                        flag = true;
                        newList = new ArrayList(aList);
                        newList.remove(arrayB[i]);
                        aList = newList;
                    }
                }
            }
            if (!flag) {
                return aList;
            }
        }

        if ("B-A".equals(type)) {//取数组B-A的值
            List<Integer> bList = Arrays.asList(arrayToDistinct(arrayB));
            boolean flag = false;
            if (arrayA != null && arrayA.length > 0) {
                for (int i = 0; i < arrayA.length; i++) {
                    if (bList.contains(arrayA[i])) {
                        flag = true;
                        newList = new ArrayList(bList);
                        newList.remove(arrayA[i]);
                        bList = newList;
                    }
                }
            }
            if (!flag) {
                return bList;
            }
        }
        return newList;
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


    public static Integer[] arrayToDistinct(Integer[] arr) {
        if (arr != null && arr.length > 0) {
            List<Integer> tempList = new ArrayList();
            for (Integer i : arr) {
                if (!tempList.contains(i)) {//判断是否有重复数据，如果没有就将数据装进临时集合
                    tempList.add(i);
                }
            }
            return tempList.toArray(new Integer[1]);
        } else {
            return arr;
        }
    }


   /* public static void main(String[] args) {
        Integer[] A = new Integer[]{1,1,1,2,};
        Integer[] B = new Integer[]{1,2,2,2,2,3};
        for (Integer i: getDiff(A,B,"B-A")
             ) {
            System.out.println(i);
        }
    }*/
}


