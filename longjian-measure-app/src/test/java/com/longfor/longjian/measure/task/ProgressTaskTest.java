package com.longfor.longjian.measure.task;

/**
 * wangxs
 * 2018-8-22 19:26
 * 定时任务单元测试
 */

import com.longfor.longjian.measure.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class ProgressTaskTest {

//    @Autowired
//    private ProjectProgressMapper projectProgressMapper;
//    @Autowired
//    private ProjectProgressService projectProgressService;
//    @Autowired
//    private ProjectProgressDictionaryService projectProgressDictionaryService;
//
//
//    @Test
//    public void testProgressTask() throws ParseException {
//        ProjectProgressTask projectProgressTask = new ProjectProgressTask(projectProgressMapper,projectProgressService,projectProgressDictionaryService);
////        projectProgressTask.progressInit();
//    }
//
//
//    @Test
//    public void testProgressUpdateStatusTask() throws ParseException {
//        ProjectProgressTask projectProgressTask = new ProjectProgressTask(projectProgressMapper,projectProgressService,projectProgressDictionaryService);
////        projectProgressTask.updateStatus();
//    }

}
