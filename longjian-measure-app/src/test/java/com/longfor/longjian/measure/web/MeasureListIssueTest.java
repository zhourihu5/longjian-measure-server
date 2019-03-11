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
 * @Date 2019/3/6 15:08
 * Created by Jiazhongmin
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // 指定我们SpringBoot工程的Application启动类
public class MeasureListIssueTest {
    private static final String TOKEN = "dDpocEnoBSCRXUrE-60vIroGFtdtV_TqzI-KMrv14pC3uyrUSBdyf3IdSGDf4K5K";
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(new UrlFilter()).build();
    }

    @Test
    public void testIssueQueryJson() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue/issue_query_json/").header("token",TOKEN)
                        .param("project_id","927")
                        .param("page_level","")
                        .param("group_id","4")
                        .param("team_id","25")
                        .param("limit","10")
                        .param("page","1")
                        .param("category_key","")
                        .param("area_ids","927")
                        .param("measure_list_ids","")
                        .param("create_at_range","")
                        .param("is_overdue","false")
                        .param("repairer_id","")
                        .param("status","")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testIssueInfo() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue_detail/issue_info/").header("token",TOKEN)
                        .param("uuid","d99fdd4f5d4340849eba65f48e50038f")
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testIssueStatus() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue_detail/issue_status/").header("token",TOKEN)
                        .param("uuid","d99fdd4f5d4340849eba65f48e50038f")
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testHistoryLogs() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue_detail/history_logs/").header("token",TOKEN)
                        .param("uuid","d99fdd4f5d4340849eba65f48e50038f")
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testZoneInfo() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue_detail/zone_info/").header("token",TOKEN)
                        .param("uuid","d99fdd4f5d4340849eba65f48e50038f")
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testRepairLogs() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue_detail/repair_logs/").header("token",TOKEN)
                        .param("uuid","d99fdd4f5d4340849eba65f48e50038f")
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testRepairList() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/measure/measure_list_issue_detail/repair_list/").header("token",TOKEN)
                        .param("uuid","d99fdd4f5d4340849eba65f48e50038f")
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }

    @Test
    public void testSimpleList() throws Exception {
        mockMvc.perform(
                post("/oapi/v3/project/user/simple_list/").header("token",TOKEN)
                        .param("project_id","927")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }
    @Test
    public void testExportExcel() throws Exception {
        mockMvc.perform(
                post("/v3/measure/measure_detail/export_excel/").header("token",TOKEN)
                        .param("project_id","1051")
                        .param("page_level","project")
                        .param("group_id","4")
                        .param("team_id","25")
                        .param("list_id","5651")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andReturn();
    }
}
