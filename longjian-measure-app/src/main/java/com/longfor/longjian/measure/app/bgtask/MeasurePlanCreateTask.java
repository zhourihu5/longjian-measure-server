package com.longfor.longjian.measure.app.bgtask;

import com.longfor.longjian.common.util.RedisUtil;
import com.longfor.longjian.measure.app.appService.appService.IAPPMeasureListService;
import com.longfor.longjian.measure.consts.Enum.BgtaskEnum;
import com.longfor.longjian.measure.consts.Enum.BgtaskStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * meause issue消费任务
 *
 * @author yanglei
 */
@Slf4j
@Service
public class MeasurePlanCreateTask implements ExportTask {

    @Resource
    private IAPPMeasureListService measureListService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserTaskNotice userTaskNotice;
    private static final String USERID = "userId";
    @Override
    public void run() {
        while (true) {
            try {
                String queueName = "zj_bgtask_create_measure:" + BgtaskEnum.MEASURE_LIST_CREATE.getValue();
                if (redisUtil.exists(queueName)) {
                    String taskKey = (String) redisUtil.lpop(queueName);
                    if (taskKey == null || "".equals(taskKey)) {
                        return;
                    }
                    while (!redisUtil.exists(taskKey)) {
                        try {
                            Thread.sleep(500l);
                        } catch (InterruptedException e) {
                            log.error(Thread.currentThread().getName() + " measure_create sleep error ", e);
                            Thread.currentThread().interrupt();
                        }
                    }
                    Map params = redisUtil.getHashObject(taskKey, Map.class);
//                    log.info("params: " + JSON.toJSONString(params));
                    userTaskNotice.updateTaskResultStatus(Integer.parseInt(params.get(USERID).toString()), params.get("id").toString(), BgtaskStatusEnum.PROCESSING);
                    try {
                        measureListService.add(params);
                        userTaskNotice.updateTaskResultStatus(Integer.parseInt(params.get(USERID).toString()), params.get("id").toString(), BgtaskStatusEnum.DONE);
//                        Thread.sleep(500l);
                    } catch (Exception e) {
                        userTaskNotice.updateTaskResultStatus(Integer.parseInt(params.get(USERID).toString()), params.get("id").toString(), BgtaskStatusEnum.PROCESSING);
                        log.error(Thread.currentThread().getName() + " measure_create error ", e);
                    }
                }
            } catch (Exception e) {
                log.error("measure_create error ,thread destory,", e);

            }
        }
    }
}
