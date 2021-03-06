package com.longfor.longjian.measure.web;

import com.longfor.longjian.common.filter.UrlFilter;
import com.longfor.longjian.measure.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
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

    private static final String TOKEN = "IttoRbVgCglLi6cYI07SqvRrZUXinj2lQJpZ-ZXY2NdIzGCGGwxHIBhMtE5ltg9e";
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
    @Test
    @Transactional
    @Rollback
    public void testAddOnProj() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/measure_region_tag/add_on_proj/").header("token",TOKEN)
                        .param("group_id","1")
                        .param("project_id","1")
                        .param("name_list","测试专用33,测试专用44")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @Transactional
    @Rollback
    public void testEditByProjId() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/measure_region_tag/add_on_group/").header("token",TOKEN)
                        .param("group_id","3")
                        .param("name_list","测试专用1,测试专用2,测试专用3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @Transactional
    @Rollback
    public void testIssueDel() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue/issue_del/").header("token",TOKEN)
                        .param("uuid","23e0c8132d0d4812bd297c3a28934e68")
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @Transactional
    @Rollback
    public void testIssueCloseStatus() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue/issue_close_status/").header("token",TOKEN)
                        .param("uuid","23e0c8132d0d4812bd297c3a28934e68")
                        .param("project_id","927")
                        .param("close_status","4")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @Transactional
    @Rollback
    public void testUpdateRepairer() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue_detail/update_repairer/").header("token",TOKEN)
                        .param("uuid","23e0c8132d0d4812bd297c3a28934e68")
                        .param("project_id","927")
                        .param("group_id","4")
                        .param("page_level","project")
                        .param("team_id","25")
                        .param("repairer_id","19865")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @Transactional
    @Rollback
    public void testMeasureRegionAdd() throws Exception {
        mockMvc.perform(
                post("/measure/v1/papi/measure_region/delete/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("group_id","4")
                        .param("page_level","project")
                        .param("team_id","25")
                        .param("region_id_list","12001747,12001748")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
}
