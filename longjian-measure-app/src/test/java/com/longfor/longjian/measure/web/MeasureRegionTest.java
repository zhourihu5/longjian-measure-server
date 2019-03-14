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
 * 2019-03-06 18:30
 */

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // 指定我们SpringBoot工程的Application启动类
public class MeasureRegionTest {
    private static final String TOKEN = "IttoRbVgCglLi6cYI07SqvRrZUXinj2lQJpZ-ZXY2NdIzGCGGwxHIBhMtE5ltg9e";
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(new UrlFilter()).build();
    }

    @Test
    public void testSearchByGroupId() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/measure_region_tag/search_by_group_id/").header("token",TOKEN)
                        .param("group_id","4")
                        .param("project_id","1051")
                        .param("proj_id","1051")
                        .param("team_id","17")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSearchByProjId() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/measure_region_tag/search_by_proj_id/").header("token",TOKEN)
                        .param("group_id","4")
                        .param("project_id","1051")
                        .param("proj_id","1051")
                        .param("team_id","17")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSearchByAreaId() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/measure_region/search_by_area_id/").header("token",TOKEN)
                        .param("group_id","4")
                        .param("project_id","1051")
                        .param("proj_id","1051")
                        .param("team_id","17")
                        .param("area_id","2952214")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSearchByRegionUuid() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/measure_region_rel/search_by_region_uuid/").header("token",TOKEN)
                        .param("group_id","4")
                        .param("proj_id","927")
                        .param("region_uuid","f77b5597e70243f1a37a238513fe83ef")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }
}
