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
 * home接口测试
 *
 * Created by Wang on 2018/8/24.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class HomeTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

//
//    /**
//     * 测试home接口
//     */
//    @Test
//    public void testHome() throws Exception {
//        String json = "{\"orgId\":10,\"type\":\"30\"}";
//        mockMvc.perform(
//                post("/v1/api/app/home").header("token","SllISExFNUJJVDI3OEtKRjdMTkdZN042RFQyNVVIT0M")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("success"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
}
