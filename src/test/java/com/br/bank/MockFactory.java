package com.br.bank;

import com.br.bank.model.Account;
import org.springframework.http.HttpHeaders;

public class MockFactory {

    public static Account mockCreateAccount(){
        return Account.builder()
                .agency("12345") //
                .balance("200,00") //
                .name("CredCard") //
                .overdraft("2000") //
                .rate("2,99") //
                .releasedOverdraft(true) //
                .build();
    }

    public static Account mockCreateAccountResponse(){
        return Account.builder()
                .agency("12345") //
                .numberAccount("123456") //
                .balance("200,00") //
                .name("CredCard") //
                .overdraft("2000") //
                .rate("2,99") //
                .releasedOverdraft(true) //
                .build();
    }

    public static HttpHeaders mockHeadersRequest(){
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }
}
