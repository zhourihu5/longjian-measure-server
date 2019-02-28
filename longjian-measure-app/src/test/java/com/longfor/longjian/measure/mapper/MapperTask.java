package com.longfor.longjian.measure.mapper;


import com.longfor.longjian.measure.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * wangxs
 * 2018-8-22 19:26
 * 定时任务单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class MapperTask {

//    @Autowired
//    private RoleService roleService;
//
//    @Test
//    public void testRoleMapper(){
//        roleService.findSpeciaRoleIds();
//    }
}
