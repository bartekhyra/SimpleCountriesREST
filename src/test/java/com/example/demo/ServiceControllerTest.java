package com.example.demo;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(ServiceController.class)
class ServiceControllerTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getFinalnd() throws Exception {
        String uri = "/countries/Finland";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertThat(mvcResult.getResponse().getContentAsString().contains("\"name\":\"Finland\",\"topLevelDomain\":[\".fi\"],\"alpha2Code\":\"FI\""));

    }

    @Test
    public void getAllCountries() throws Exception {
        String uri = "/countries/";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertThat(mvcResult.getResponse().getContentAsString().contains("\"name\":\"Afghanistan\",\"topLevelDomain\":[\".af\"],\"alpha2Code\":\"AF\",\"alpha3Code\":\"AFG\",\"callingCodes\":[\"93\"],\"capital\":\"Kabul\",\"altSpellings\":[\"AF\",\"Afġānistān\"],\"region\":\"Asia\""));

    }

}