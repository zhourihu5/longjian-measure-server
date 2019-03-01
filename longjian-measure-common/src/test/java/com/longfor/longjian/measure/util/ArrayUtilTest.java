package com.longfor.longjian.measure.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class ArrayUtilTest {

    @Test
    public void getDiffNormal() {
        Integer[] arrayA = new Integer[]{1,2,2,3,4};
        Integer[] arrayB = new Integer[]{4,5,6};
        List<Integer> result = ArrayUtil.getDiff(arrayA,arrayB);
        List<Integer> expected = Arrays.asList(1,2,3);
        assertEquals(expected,result);
    }

    @Test
    public void getDiffFirstNull() {
        Integer[] arrayA = new Integer[]{};
        Integer[] arrayB = new Integer[]{4,5,6};
        List<Integer> result = ArrayUtil.getDiff(arrayA,arrayB);
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected,result);
    }

    @Test
    public void getDiffSecondNull() {
        Integer[] arrayA = new Integer[]{1, 2, 2};
        Integer[] arrayB = new Integer[]{};
        List<Integer> result = ArrayUtil.getDiff(arrayA, arrayB);
        List<Integer> expected = Arrays.asList(1, 2);
        assertEquals(expected, result);
    }
}