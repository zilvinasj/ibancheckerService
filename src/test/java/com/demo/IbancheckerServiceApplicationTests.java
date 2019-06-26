package com.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IbancheckerServiceApplicationTests
{

    private ObjectMapper objectMapper;

    @Before
    public void setup()
    {
        objectMapper = new ObjectMapper();
    }

    @Autowired
    MockMvc mockMvc;

    @Test
    public void successResponse() throws IOException, Exception
    {
        String result = mockMvc
                .perform(post("/ibanValidation").contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.getSamplesJsonString("requestSuccess.json")))
                .andExpect(status().is2xxSuccessful()).andReturn().getResponse().getContentAsString();
        Response response = objectMapper.readValue(result, Response.class);
        assertNull(response.getErrors());
        assertNull(response.getInfos());
        assertNotNull(response.getResults());
        Response expected = TestUtils.getSamplesJsonBean("responseSuccess.json", Response.class);
        assertEquals(expected.getResults().get(0), response.getResults().get(0));
        assertEquals(expected.getResults().get(1), response.getResults().get(1));

    }

    @Test
    public void failureWrongMethod() throws IOException, Exception
    {
        String result = mockMvc
                .perform(get("/ibanValidation").contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.getSamplesJsonString("requestSuccess.json")))
                .andExpect(status().is4xxClientError()).andReturn().getResponse().getContentAsString();
        Response response = objectMapper.readValue(result, Response.class);
        assertNotNull(response.getErrors());
        assertNull(response.getInfos());
        assertNull(response.getResults());
        Response expected = TestUtils.getSamplesJsonBean("responseWrongMethod.json", Response.class);
        assertEquals(expected.getErrors().get(0).getCode(), response.getErrors().get(0).getCode());
        assertEquals(expected.getErrors().get(0).getDescription(), response.getErrors().get(0).getDescription());

    }

    @Test
    public void failureBadRequest() throws IOException, Exception
    {
        String result = mockMvc
                .perform(post("/ibanValidation").contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.getSamplesJsonString("requestBad.json")))
                .andExpect(status().is5xxServerError()).andReturn().getResponse().getContentAsString();
        Response response = objectMapper.readValue(result, Response.class);
        assertNotNull(response.getErrors());
        assertNull(response.getInfos());
        assertNull(response.getResults());

    }

}
