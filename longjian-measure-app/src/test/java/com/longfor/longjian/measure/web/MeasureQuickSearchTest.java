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

    private static final String TOKEN = "IttoRbVgCglLi6cYI07SqvRrZUXinj2lQJpZ-ZXY2NdIzGCGGwxHIBhMtE5ltg9e";
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
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSquadMeasureStatsJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/squad_measure_stats_json/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("measure_list_id","5526")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSquadCompletenessJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/squad_completeness_json/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("measure_list_id","5526")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSquadSpecialPointJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/squad_special_point_json/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("measure_list_id","5526")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testCategoryDetailsJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/category_details_json/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("measure_list_id","5526")
                        .param("category_key","")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testIssueBriefJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/issue_brief_json/").header("token",TOKEN)
                        .param("project_id","1051")
                        .param("measure_list_id","5651")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testIssueTrendJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/issue_trend_json/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("measure_list_id","5527")
                        .param("begin_on","0")
                        .param("end_on","0")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testIssueDistributionCategoryJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/issue_distribution_category_json/").header("token",TOKEN)
                        .param("project_id","1051")
                        .param("measure_list_id","5651")
                        .param("category_key","")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testGetRootCategory() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/ajax_json/get_root_category/").header("token",TOKEN)
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSubCategoryAreaPercentage() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_statistic/sub_category_area_percentage/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("list_ids","5526")
                        .param("area_ids","2970149")
                        .param("parent_category_key","10204")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }
}
