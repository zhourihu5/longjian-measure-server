package com.longfor.longjian.measure.web;


import com.longfor.longjian.measure.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * wangxs
 * 2018-08-24 11:41
 * 历史填报接口单元测试
 */

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class ProgressSearchTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testGetBasicSourceInit() throws Exception {
//        String json = "{\"companyId\":5,\"searchStartTime\":\"2018-08-14\",\"searchEndTime\":\"2018-08-16\"}";
//        mockMvc.perform(
//                post("/v1/api/app/progressSearch/getBasicSourceInit")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("success"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }

    @Test
    public void testGetTeamOrProDetails() throws Exception {
//        String json = "{\"groupId\":4}";
//        mockMvc.perform(
//                post("/v1/api/app/progressSearch/getTeamOrProDetails")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("success"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }
}
