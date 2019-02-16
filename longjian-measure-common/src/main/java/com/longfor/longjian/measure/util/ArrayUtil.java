package com.longfor.longjian.measure.util;


import io.swagger.models.auth.In;

import java.util.*;

/**
 * Created by Wang on 2019/1/7.
 */
public class ArrayUtil {

    /**
     * 根据不同类型  取数组差值
     * @param arrayA
     * @param arrayB
     * @param type
     * @return
     */
    public static List<Integer> getDiff(Integer [] arrayA, Integer [] arrayB,String type){
        List newList=new ArrayList<>();
        if("A-B".equals(type)){//取数组A-B的值
            List<Integer>  aList = Arrays.asList(arrayA);
            boolean flag = false;
            for(int i=0;i<arrayB.length;i++){
                if(aList.contains(arrayB[i])){
                    flag = true;
                    newList=new ArrayList(aList);
                    newList.remove(arrayB[i]);
                    aList=newList;
                }
            }
            if (!flag){
                return aList;
            }
        }

        if("B-A".equals(type)){//取数组B-A的值
            List<Integer> bList=Arrays.asList(arrayB);
            boolean flag = false;
            for(int i=0;i<arrayA.length;i++){
                if(bList.contains(arrayA[i])){
                    flag = true;
                    newList=new ArrayList(bList);
                    newList.remove(arrayA[i]);
                    bList=newList;
                }
            }
            if (!flag){
                return bList;
            }
        }
        return newList;
    }

    /**
     * 比较2个set元素
     * @param set1
     * @param set2
     * @return
     */
    public static boolean getSetDiff(Set<Integer> set1,Set<Integer> set2){
        set1.removeAll(set2);
        if(set1.size()>0){
            return true;
        }else{
            return false;
        }
    }



}


