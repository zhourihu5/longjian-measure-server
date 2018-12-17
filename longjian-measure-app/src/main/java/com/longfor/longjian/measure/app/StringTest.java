package com.longfor.longjian.measure.app;



import com.alibaba.druid.sql.visitor.functions.Char;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class StringTest {

    /**
     * 1.将字符串转换为char字符数组/
     * 2.定义一个2维数组.对char字符数组第一层遍历，第二层遍历相邻元素进行比较，如果不相等，则把下标i记录2维数组x，如果相等，则将这时的j记录2维数组y中
     * 3.继续遍历 得到2维数组
     * 4。遍历2维数组，找到j-i最大的
     * 5.截取字符串，参数维i，j
     */


    public static void main(String[] args) {
        String  a = "sdfaefgcva";

        char[] x = a.toCharArray();
     /*   for (int i = 0; i <x.length; i++) {
            System.out.print(x[i]);
            boolean contains = a.contains(x[i]);
            if(contains){

            }
        }*/
        Map<Character,Object> map=new HashMap<>();
        for (int i = 0; i <x.length ; i++) {
            Object o = map.get(x[i]);
            if(o==null){
                System.out.print(x[i]);
                map.put(x[i],x[i]+1);
            }
            if(o!=null){
                return;
            }
        }
    }






















}
