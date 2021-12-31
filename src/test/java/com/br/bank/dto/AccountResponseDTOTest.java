package com.br.bank.dto;

import com.br.bank.model.dto.AccountResponseDTO;
import org.junit.Test;

import static com.br.bank.MockFactory.mockCreateAccountResponse;
import static com.br.bank.service.AccountService.converter;
import static org.junit.Assert.assertEquals;

public class AccountResponseDTOTest {


    @Test
    public void shouldConvertAccountResponseTest(){
        AccountResponseDTO resp = converter(mockCreateAccountResponse());
        assertEquals("Unexpected Agency value", "1234/12345", resp.getAgency()); //
        assertEquals("Unexpected Balance value", "200,00", resp.getBalance());
        assertEquals("Unexpected CredCard value", "CredCard", resp.getName());
        assertEquals("Unexpected OverDraft value", "2059,8", resp.getOverdraft());
        assertEquals("Unexpected Rate value", "2,99", resp.getRate());
        assertEquals("Unexpected ReleasedOverdraft value", "Liberado", resp.getReleasedOverdraft());

    }
}
