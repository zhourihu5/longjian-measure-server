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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * wangxs
 * 2019-03-06 18:38
 */

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // 指定我们SpringBoot工程的Application启动类
public class MeasureQuickSearchTest {

    private static final String TOKEN = "rOFM0joOPtQ8_bpX8hZb0MUWRvfjNXqT3URKpEyAAFwuFtymZUD2J-MBj7mvy3WJ";
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(new UrlFilter()).build();
    }


    @Test
    public void testMeasureListJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/measure_list_json/").header("token",TOKEN)
                        .param("project_id","1051")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSquadMeasureStatsJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/squad_measure_stats_json/").header("token",TOKEN)
                        .param("project_id","1051")
                        .param("measure_list_id","5651")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSquadCompletenessJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/squad_completeness_json/").header("token",TOKEN)
                        .param("project_id","1051")
                        .param("measure_list_id","5651")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSquadSpecialPointJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/squad_special_point_json/").header("token",TOKEN)
                        .param("project_id","1051")
                        .param("measure_list_id","5651")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testCategoryDetailsJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/category_details_json/").header("token",TOKEN)
                        .param("project_id","1051")
                        .param("measure_list_id","5651")
                        .param("category_key","")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }
}
