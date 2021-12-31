package com.br.bank.controller;

import com.br.bank.repository.AccountRepository;
import com.br.bank.service.AccountService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static com.br.bank.MockFactory.mockCreateAccount;
import static com.br.bank.MockFactory.mockCreateAccountResponse;
import static com.br.bank.MockFactory.mockGetAccountResponse;
import static com.br.bank.MockFactory.mockHeadersRequest;
import static com.br.bank.utils.Paths.DELETE_ACCOUNT;
import static com.br.bank.utils.Paths.FIND_ACCOUNT_NUMBER;
import static com.br.bank.utils.Paths.UPDATE_ACCOUNT;
import static com.br.bank.utils.Paths.V1;
import static com.br.bank.utils.Paths.V1_CREATE_ACCOUNT;
import static com.br.bank.utils.Paths.V1_FIND_BY_ALL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ServiceController.class)
@EnableJpaRepositories(basePackages = "com.br.bank.repository")
public class AccountControllerTest extends AbstractMVCController{

    private final static String INVALID_ACCOUNT_NUMBER = "Número de conta não encontrado!";

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private ServiceController serviceController;
    @MockBean
    private AccountService mockAccountService;

    @Before
    public void before(){
        RestAssuredMockMvc.standaloneSetup(this.serviceController);
        super.setup();
    }

    @Test
    public void shouldCreateAccountTest() throws Exception {
        when(mockAccountService.generateAccount(any())).thenReturn(mockCreateAccountResponse());
        when(accountRepository.save(any())).thenReturn(mockCreateAccount());

        mockMvc.perform(post(V1_CREATE_ACCOUNT).contentType(APPLICATION_JSON_VALUE) //
                         .content(objectMapper.writeValueAsString(mockCreateAccount()))
                         .headers(mockHeadersRequest())) //
                         .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void shouldFindAllAccountsTest() throws Exception {
        when(mockAccountService.findAll()).thenReturn(Collections.singletonList(mockGetAccountResponse()));

        mockMvc.perform(get(V1_FIND_BY_ALL).contentType(APPLICATION_JSON_VALUE) //
                        .headers(mockHeadersRequest())) //
                        .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void shouldFindByAccountNumberTest() throws Exception {
        when(mockAccountService.findByAccountNumber(any())).thenReturn(Optional.ofNullable(mockGetAccountResponse()));

        mockMvc.perform(get(V1 + FIND_ACCOUNT_NUMBER + "/258456").contentType(APPLICATION_JSON_VALUE) //
                        .pathInfo("/account_number") //
                        .headers(mockHeadersRequest())) //
                        .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void shouldFindByNameTest() throws Exception {
        when(mockAccountService.findByName(any())).thenReturn(Collections.singletonList(mockGetAccountResponse()));

        mockMvc.perform(get("/v1/findAccountByName/Bruno").contentType(APPLICATION_JSON_VALUE) //
                        .pathInfo("/name") //
                        .headers(mockHeadersRequest())) //
                        .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldFindByAgencyReleaseOverdraftAndNameTest() throws Exception {
        when(mockAccountService.findByAgencyReleaseOverdraftAndName(any(), any(), any())).thenReturn(Collections.singletonList(mockGetAccountResponse()));

        mockMvc.perform(get("/v1/findAccount?agency=1299&cheque_especial_liberado=1&nome=Severino Pessoa").contentType(APPLICATION_JSON_VALUE) //
                        .param(mockCreateAccount().getAgency(), mockGetAccountResponse().getReleasedOverdraft(), mockCreateAccountResponse().getName())
                        .headers(mockHeadersRequest())) //
                        .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldDeleteAccountByAccountNumberTest() throws Exception {
        mockAccountService.deleteAccount("894198");

        mockMvc.perform(delete(V1 + DELETE_ACCOUNT + "/894198").contentType(APPLICATION_JSON_VALUE) //
                        .pathInfo("/account_number")) //
                        .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldUpdateAccountByAccountNumberTest() throws Exception {
        when(mockAccountService.updateAccount(any(), any())).thenReturn(mockCreateAccountResponse());

        mockMvc.perform(put(V1 + UPDATE_ACCOUNT + "/256859").contentType(APPLICATION_JSON_VALUE) //
                        .pathInfo("/account_number") //
                        .content(objectMapper.writeValueAsString(mockCreateAccount()))
                        .headers(mockHeadersRequest())) //
                        .andExpect(status().is2xxSuccessful());
    }


}
