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
 */

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // 指定我们SpringBoot工程的Application启动类
public class MeasureListSearchTest {
    private static final String TOKEN = "0-hG-pc7XlZRpNMfZeWL69l8l9PFnEqVPmdozHQ4UJgy_fMSTGMAN7bxuJ8mmZ_c";
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
                .andReturn();
    }

    @Test
    public void testPaginationSearch() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/zone/pagination_search/").header("token",TOKEN)
                        .param("group_id","4")
                        .param("project_id","927")
                        .param("team_id","25")
                        .param("list_id","5526")
                        .param("category_key_list","")
                        .param("area_id_list","")
                        .param("page","1")
                        .param("page_size","20")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }


    @Test
    public void testRepairerListSearch() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/staff/repairer_list_search/").header("token",TOKEN)
                        .param("group_id","4")
                        .param("project_id","927")
                        .param("team_id","25")
                        .param("list_id","5526")
                        .param("role_type","2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    /*@Test
    public void testGetResult() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/zone/get_result/").header("token",TOKEN)
                        .param("group_id","4")
                        .param("project_id","1051")
                        .param("zone_uuid","dbddaebfd28142278ee43213a84fcebe")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }*/

    @Test
    public void testAllowUserSearch() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/staff/allow_user_search").header("token",TOKEN)
                        .param("group_id","4")
                        .param("project_id","927")
                        .param("list_id","5525")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSubCategorys() throws Exception {
        mockMvc.perform(
                post("/v3/measure/measure_list/sub_categorys/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("key","1605")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testUserList() throws Exception {
        mockMvc.perform(
                post("/v3/measure/ajax_json/user_list/").header("token",TOKEN)
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testUserList2() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/ajax_json/user_list/").header("token",TOKEN)
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

}
