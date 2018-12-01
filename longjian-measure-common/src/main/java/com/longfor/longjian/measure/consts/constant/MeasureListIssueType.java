package com.longfor.longjian.measure.consts.constant;

public class MeasureListIssueType {
    /**
     * typ  需整改问题"
     */
    public static String REPAIRABLE = "1";
    /**
     * typ  难以整改问题
     */
    public static String NOREPAIRABLE = "2";
    /**
     * status 已记录未分配
     */
    public static String NOTENOASSIGN = "1";
    /**
     * status 已分配未整改
     */
    public static String ASSIGNNOREFORM = "2";
    /**
     * status 已整改未验收
     */
    public static String REFORMNOCHECK = "3";
    /**
     * status 已验收
     */
    public static String CHECKYES = "4";
    /**
     * status 已关闭
     */
    public static String CLOSED = "5";
}
