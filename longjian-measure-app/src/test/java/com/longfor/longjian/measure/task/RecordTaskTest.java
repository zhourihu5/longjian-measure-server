package com.longfor.longjian.measure.task;

/**
 * wangxs
 * 2018-08-23 18:02
 * 通知推送单元测试
 */

import com.longfor.longjian.measure.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class RecordTaskTest {

//    @Autowired
//    private ProjectProgressService projectProgressService;
//    @Autowired
//    private UserInProjectRoleService userInProjectRoleService;
//    @Autowired
//    private INoticeStatRecordService noticeStatRecordService;
//    @Autowired
//    private ProjectProgressDictionaryService projectProgressDictionaryService;
//    @Autowired
//    private ITeamService teamService;
//
//    @Test
//    public void testSendBacklog() throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
//        RecordTask recordTask = new RecordTask(projectProgressService,userInProjectRoleService,noticeStatRecordService,projectProgressDictionaryService,teamService);
////        recordTask.sendBacklog();
//    }
//
//
//    @Test
//    public void testSendPresslog() throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
//        RecordTask recordTask = new RecordTask(projectProgressService,userInProjectRoleService,noticeStatRecordService,projectProgressDictionaryService,teamService);
////        recordTask.sendPresslog();
//    }
}
