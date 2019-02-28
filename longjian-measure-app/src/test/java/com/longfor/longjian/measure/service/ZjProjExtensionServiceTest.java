package com.longfor.longjian.measure.service;

import com.longfor.longjian.common.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * wangxs
 * 2018-09-04 10:40
 * 智建航道信息
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class ZjProjExtensionServiceTest {

//    @Autowired
//    private ProjectProgressMapper projectProgressMapper;
//    @Autowired
//    private ZjProjExtensionService zjProjExtensionService;
//
//    @Test
//    public void insertHangdaoInfo(){
//        String[] str = { "C1,C2", "C1,C3", "C2,C3", "C1", "C2" };
//        ProjectProgress example = new ProjectProgress();
//        example.setRecordDate(DateUtil.getNowDateShort());
//        example.setGroupId(Constants.LHJT_ID);
//        List<ProjectProgress> projectProgresses = projectProgressMapper.select(example);
//        List<ZjProjExtension> zjProjExtensions = new ArrayList<>();
//        for (ProjectProgress p: projectProgresses
//             ) {
//            ZjProjExtension zjProjExtension = new ZjProjExtension();
//            zjProjExtension.setGroupId(p.getGroupId());
//            zjProjExtension.setProjectId(p.getProjectId());
//            zjProjExtension.setLhHangDao(str[(int) ( Math.random () * 5 )]);
//            zjProjExtension.setCreateAt(new Date());
//            zjProjExtension.setUpdateAt(new Date());
//            zjProjExtensions.add(zjProjExtension);
//        }
//        zjProjExtensionService.insertList(zjProjExtensions);
//    }
}
