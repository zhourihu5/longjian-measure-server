package com.longfor.longjian.measure.web;


import com.longfor.longjian.measure.Application;
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

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class AnalysisTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testAnalysis(){

//        String json ="{\"orgId\":4,\"lhHangDao\":\"c1\",\"endTime\":\"2018-09-09\",\"type\":\"10\",\"query\":\"{proj{constructionDelay,normalConstruction,stopWork,noWrite,reasonDate{laborShortage,materialEquipmentShortage,adverseWeatherEffect,environmentalAirControl},stopReasonDate{materialEquipmentShortage,adverseWeatherEffect}}}\",\"queryTotal\":\"{proTotal{normalSupply,outstandingDelivery,deliveryRisk,deliveryNormal,commercialOpeningRisk,normalBusinessOpening,crownOpeningRisk}}\"}";
//        mockMvc.perform(
//                post("/v1/api/app/homeTotalMsg").header("token","Wlk4TFYyRzJaQUE4S0NDUjFSU1VJWFZPOVBKMTBZWDc")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("success"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }
}
