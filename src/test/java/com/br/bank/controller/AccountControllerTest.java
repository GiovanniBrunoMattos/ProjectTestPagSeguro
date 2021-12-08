package com.br.bank.controller;

import com.br.bank.model.Account;
import com.br.bank.repository.AccountRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static com.br.bank.MockFactory.mockCreateAccount;
import static com.br.bank.MockFactory.mockHeadersRequest;
import static com.br.bank.utils.Paths.CREATE_ACCOUNT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ServiceController.class)
@EnableJpaRepositories(basePackages = "com.br.bank.repository")
public class AccountControllerTest extends AbstractMVCController{

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void before(){
        super.setup();
    }

    @Test
    public void shouldCreateAccountTest() throws Exception {
        when(mockAccountService.generateAccount(any(Account.class))).thenReturn(new Account());
        when(accountRepository.save(any())).thenReturn(mockCreateAccount());

        mockMvc.perform(post(CREATE_ACCOUNT).contentType(APPLICATION_JSON_VALUE) //
         .content(objectMapper.writeValueAsString(mockCreateAccount()))
         .headers(mockHeadersRequest())) //
         .andExpect(status().is2xxSuccessful());

        verify(mockAccountService).generateAccount(any(Account.class));
    }

}
