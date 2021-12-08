package com.br.bank.model.dto;

import com.br.bank.model.Account;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDTO {
    private String numberAccount;

    private String name;

    private String agency;

    private String releasedOverdraft;

    private String balance;

    private String overdraft;

    private String rate;


    public static AccountResponseDTO converter(Account p) {
        var pessoa = new AccountResponseDTO();
        pessoa.setName(p.getName());
        pessoa.setAgency(p.getAgency().concat("/").concat(p.getNumberAccount()));
        pessoa.setReleasedOverdraft(converterReleasedOverdraft((p.getReleasedOverdraft())));
        pessoa.setBalance(p.getBalance());
        pessoa.setOverdraft(p.getOverdraft());
        pessoa.setRate(p.getRate());
        return pessoa;
    }

    public static String converterReleasedOverdraft(Boolean releasedOverdraft){
        if (releasedOverdraft.equals(true)){
            return "Liberado";
        }
        return "Não Liberado";
    }

}

