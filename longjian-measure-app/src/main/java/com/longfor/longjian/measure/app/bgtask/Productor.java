package com.longfor.longjian.measure.app.bgtask;

import lombok.Data;

import java.util.Map;

/**
 * wangxs 2019-2-11
 */
@Data
public class Productor {
    private String name;

//    protected void send(Object item, Map params, String task_cls_name) {
//        return do_send(_notice_2_task_name(item),params,task_cls_name);
//    }
//
//    private Object _notice_2_task_name(Object item) {
//        if (item instanceof String){
//            return item;
//        }else {
//            if (item instanceof NoticeInnerItem){
//                return item.get_value(this.name);
//            }
//        }
//    }
}
