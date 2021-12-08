package com.br.bank.controller;

import com.br.bank.BankApplication;
import com.br.bank.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(classes = {AnnotationConfigContextLoader.class})
public class AbstractMVCController {

    @Autowired
    protected WebApplicationContext context;

    @MockBean
    protected AccountService mockAccountService;

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
