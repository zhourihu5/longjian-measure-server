package com.longfor.longjian.measure.web;


import com.longfor.longjian.common.filter.UrlFilter;
import com.longfor.longjian.measure.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * wangxs
 * 2019-03-04 13:51
 * MeasureListController 单元测试
 */

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // 指定我们SpringBoot工程的Application启动类
public class MeasureListSearchTest {
    private static final String TOKEN = "r2h5dl4sH5oQORZ0IYeVfuFXjskVCeyG_8-R_kv3XZ1q4pvPAck1z-fWJFpnk9pA";
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(new UrlFilter()).build();
    }

    @Test
    public void testConditionSearch() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/measure_list/condition_search").header("token",TOKEN)
                        .param("group_id","4")
                        .param("area_id","")
                        .param("category_key","")
                        .param("finish_status","")
                        .param("name","")
                        .param("page","1")
                        .param("page_size","20")
                        .param("project_id","927")
                        .param("user_id_list","")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testSquadSearch() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/staff/squad_search/").header("token",TOKEN)
                        .param("group_id","4")
                        .param("project_id","927")
                        .param("team_id","25")
                        .param("list_id","5526")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
