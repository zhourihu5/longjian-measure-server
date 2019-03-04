package com.longfor.longjian.measure.web;


import com.alibaba.fastjson.JSON;
import com.longfor.longjian.measure.Application;
import com.longfor.longjian.measure.app.req.measurelist.ConditionSearchReq;
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
 * 2019-03-04 13:51
 * MeasureListController 单元测试
 */

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class MeasureListSearchTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testConditionSearch() throws Exception {
        ConditionSearchReq conditionSearchReq = new ConditionSearchReq();
        conditionSearchReq.setGroup_id(4);
        conditionSearchReq.setArea_id("");
        conditionSearchReq.setCategory_key("");
        conditionSearchReq.setFinish_status(null);
        conditionSearchReq.setName("");
        conditionSearchReq.setPage(1);
        conditionSearchReq.setPage_size(20);
        conditionSearchReq.setProject_id(927);
        conditionSearchReq.setUser_id_list("");
        mockMvc.perform(
                post("measure/v1/papi/measure_list/condition_search")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSON.toJSONString(conditionSearchReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
