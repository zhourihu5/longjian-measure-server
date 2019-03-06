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
    private static final String TOKEN = "rOFM0joOPtQ8_bpX8hZb0MUWRvfjNXqT3URKpEyAAFwuFtymZUD2J-MBj7mvy3WJ";
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
}
