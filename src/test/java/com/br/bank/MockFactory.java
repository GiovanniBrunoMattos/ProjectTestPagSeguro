package com.br.bank;

import com.br.bank.model.Account;
import com.br.bank.model.dto.AccountResponseDTO;
import com.br.bank.model.dto.FailResponseDTO;
import org.springframework.http.HttpHeaders;

import static com.br.bank.enums.ErrorCode.INVALID_ACCOUNT_NUMBER_CODE;

public class MockFactory {

    private final static String BAD_REQUEST = "Bad Request";
    private final static String INVALID_ACCOUNT_NUMBER = "Número de conta não encontrado!";

    public static Account mockCreateAccount(){
        return Account.builder()
                .agency("1234") //
                .balance("200,00") //
                .name("CredCard") //
                .overdraft("2000") //
                .rate("2,99") //
                .releasedOverdraft(true) //
                .build();
    }

    public static Account mockCreateAccountResponse(){
        return Account.builder()
                .numberAccount("12345")
                .agency("1234") //
                .balance("200,00") //
                .name("CredCard") //
                .overdraft("2000") //
                .rate("2,99") //
                .releasedOverdraft(true) //
                .build();
    }

    public static AccountResponseDTO mockGetAccountResponse(){
        return AccountResponseDTO.builder()
                .agency("1234") //
                .balance("200,00") //
                .name("CredCard") //
                .overdraft("2000") //
                .rate("2,99") //
                .releasedOverdraft("Liberado") //
                .build();
    }

    public static FailResponseDTO mockMessageError(){
        return FailResponseDTO.builder()
                .code(INVALID_ACCOUNT_NUMBER_CODE)
                .description(INVALID_ACCOUNT_NUMBER)
                .build();
    }

    public static HttpHeaders mockHeadersRequest(){
        return new HttpHeaders();
    }
}
