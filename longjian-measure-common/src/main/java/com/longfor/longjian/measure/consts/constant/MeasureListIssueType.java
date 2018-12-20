package com.longfor.longjian.measure.consts.constant;

public class MeasureListIssueType {
    /**
     * typ  需整改问题"
     */
    public static final int REPAIRABLE = 1;
    /**
     * typ  难以整改问题
     */
    public static final int NOREPAIRABLE = 2;
    /**
     * status 已记录未分配
     */
    public static final int NOTENOASSIGN = 1;
    /**
     * status 已分配未整改
     */
    public static final int ASSIGNNOREFORM = 2;
    /**
     * status 已整改未验收
     */
    public static final int REFORMNOCHECK = 3;
    /**
     * status 已验收
     */
    public static final int CHECKYES = 4;
    /**
     * status 已关闭
     */
    public static final int CLOSED = 5;
}
