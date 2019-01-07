package com.longfor.longjian.measure.util;


import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            for(int i=0;i<arrayB.length;i++){

                if(aList.contains(arrayB[i])){

                    newList=new ArrayList(aList);

                    newList.remove(arrayB[i]);

                    aList=newList;
                }
            }
        }

        if("B-A".equals(type)){//取数组B-A的值
            List<Integer> bList=Arrays.asList(arrayB);

            for(int i=0;i<arrayA.length;i++){
                if(bList.contains(arrayA[i])){
                    newList=new ArrayList(bList);
                    newList.remove(arrayA[i]);

                    bList=newList;
                }
            }

        }
        return newList;
    }

}


