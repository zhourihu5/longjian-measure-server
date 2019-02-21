package com.longfor.longjian.measure.consts.Enum;

/**
 * '''后台任务状态'''
 *     FAILED     = Item(-9999, '失败')
 *     QUEUING    = Item(10, '队列中')
 *     PROCESSING = Item(20, '正在执行')
 *     DONE       = Item(30, '完成')
 */
public enum BgtaskStatusEnum {
    FAILED("失败",-9999),
    QUEUING("队列中",10),
    PROCESSING("正在执行",20),
    DONE("完成",30);
    private String name;
    private Integer value;

    BgtaskStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
