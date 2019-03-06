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
 * @Date 2019/3/6 17:26
 * Created by Jiazhongmin
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // 指定我们SpringBoot工程的Application启动类
public class AppSyncTest {

    private static final String TOKEN = "7MCigh6Ia1ZQiViXRlQWnIv6QG1ftlV1xYFIE9jYO6NPwY2DN59T3AM2O-t4e4Bb";
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(new UrlFilter()).build();
    }

    @Test
    public void testMeasureRule() throws Exception {
        mockMvc.perform(
                post("/v3/api/info/measure_rule/").header("token",TOKEN)
                        .param("category_keys","1605")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureRegion() throws Exception {
        mockMvc.perform(
                post("/v3/api/info/measure_region/").header("token",TOKEN)
                        .param("project_ids","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureRegionV2() throws Exception {
        mockMvc.perform(
                post("/v3/api/info/measure_region_v2/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("last_id","0")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureRegionV2Total() throws Exception {
        mockMvc.perform(
                post("/v3/api/info/measure_region_v2_total/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }


    @Test
    public void testMeasureRegionRelV2() throws Exception {
        mockMvc.perform(
                post("/v3/api/info/measure_region_rel_v2/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("last_id","0")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }


    @Test
    public void testMyTask() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/my_task/").header("token",TOKEN)
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureZone() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/measure_zone/").header("token",TOKEN)
                        .param("list_ids","1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureZoneV2() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/measure_zone_v2/").header("token",TOKEN)
                        .param("list_id","1")
                        .param("last_id","0")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureZoneV2Total() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/measure_zone_v2_total/").header("token",TOKEN)
                        .param("list_id","1")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureSquadAndRepairer() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/measure_squad_and_repairer/").header("token",TOKEN)
                        .param("list_id","1")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureZoneResult() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/measure_zone_result/").header("token",TOKEN)
                        .param("list_ids","1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureZoneResultV2() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/measure_zone_result_v2/").header("token",TOKEN)
                        .param("list_id","1")
                        .param("last_id","0")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testMeasureZoneResultV2Total() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/measure_zone_result_v2_total/").header("token",TOKEN)
                        .param("list_id","1")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testIssue() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/issue/").header("token",TOKEN)
                        .param("list_id","1")
                        .param("last_id","0")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testIssueLog() throws Exception {
        mockMvc.perform(
                post("/v3/api/measure/issue_log/").header("token",TOKEN)
                        .param("list_id","1")
                        .param("last_id","0")
                        .param("timestamp","1420041600")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }
}
