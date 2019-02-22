package com.longfor.longjian.measure.app.bgtask;

import com.alibaba.fastjson.JSON;
import com.longfor.longjian.common.util.Md5Util;
import com.longfor.longjian.common.util.RedisUtil;
import com.longfor.longjian.measure.app.vo.TaskInfoVo;
import com.longfor.longjian.measure.consts.Enum.BgtaskEnum;
import com.longfor.longjian.measure.consts.Enum.BgtaskStatusEnum;
import com.longfor.longjian.measure.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * wangxs 2019-2-11
 */
@Component
@Slf4j
public class UserTaskNotice extends Productor{

    @Resource
    private RedisUtil redisUtil;

    public String sendToRedis(Integer userId, BgtaskEnum taskType, String s, Map params, String task_cls_name) throws Exception{
//        super.send(item,params,task_cls_name);
        String taskKey=getTaskKey(userId,taskType);
        redisUtil.rpush(getQueueName(taskType), taskKey);//加入任务队列
        params.put("taskTypeId", taskType.getValue());
        params.put("taskTypeValue", taskType.getName());
        String id = Md5Util.md5Encode(taskKey.getBytes());
        params.put("id", id);
//        param.put("fileName", fileName);
        params.put("userId", userId);
        redisUtil.setHash(taskKey, params);//写入任务参数

        TaskInfoVo result=new TaskInfoVo();
        result.setCreate_at(Long.valueOf(new Date().getTime()/1000).intValue());
        result.setId(id);
        result.setName("实测实量:后台创建任务");
        result.setProduct("measure");
        result.setProduct_name("实测实量");
        result.setStatus(BgtaskStatusEnum.QUEUING.getValue());
        result.setStatus_msg(BgtaskStatusEnum.QUEUING.getName());
        result.setTyp(taskType.getValue());
        result.setTyp_name(taskType.getName());
        redisUtil.rpush(getReultQueueName(userId), getTaskResultKey(userId, id));
        redisUtil.setHash(getTaskResultKey(userId, id), ConvertUtil.convertBean(result));
        return id;
    }




    public void updateTaskResultStatus(Integer userId, String taskId, BgtaskStatusEnum status){
        while (!redisUtil.exists(getTaskResultKey(userId, taskId))){
            try {
                Thread.sleep(500l);
            } catch (InterruptedException e) {
                log.error(Thread.currentThread().getName() + " measure_create sleep error ", e);
            }
        }
        TaskInfoVo result = redisUtil.getHashObject(getTaskResultKey(userId, taskId),TaskInfoVo.class);
//        log.info("result:" + JSON.toJSONString(result));
        if (result == null) {
            log.error(
                    String.format("updateTaskResultStatus error Result not exist,userId:%d taskId:%s", userId, taskId));
            return;
        }
        result.setStatus(status.getValue());
        result.setStatus_msg(status.getName());
        if (!"正在执行".equals(status.getName())) {
            result.setFinish_at(new Long(new Date().getTime() / 1000).intValue());
        }
        try {
            redisUtil.setHash(getTaskResultKey(userId, taskId), ConvertUtil.convertBean(result));
        } catch (Exception e) {
            log.error("map转换异常");
        }
    }




    public String getTaskKey(Integer userId,BgtaskEnum taskType) {
        return getQueueName(taskType)+":"+userId+":"+new Date().getTime();
    }

    public String getTaskResultKey(Integer userId, String id) {
        return getReultQueueName(userId)+":"+id;
    }

    public String getQueueName(BgtaskEnum taskType) {
        return "zj_bgtask_create_measure:"+taskType.getValue();
    }

    public String getReultQueueName(Integer userId) {
        return "zj_bgtask_create_measure_result:"+userId;
    }
}
