package com.longfor.longjian.measure.app.bgtask;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * wangxs 2019-2-11
 */
@Component
public class UserTaskNotice extends Productor{

    public String send(Integer userId, String item, String s, Map params,String task_cls_name) {
        super.send(item,params,task_cls_name);
        return "";
    }
}
