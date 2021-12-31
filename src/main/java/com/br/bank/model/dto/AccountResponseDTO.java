package com.br.bank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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


    @Override
    public String toString() {
        return "AccountResponseDTO{" +
                "numberAccount='" + numberAccount + '\'' +
                ", name='" + name + '\'' +
                ", agency='" + agency + '\'' +
                ", releasedOverdraft='" + releasedOverdraft + '\'' +
                ", balance='" + balance + '\'' +
                ", overdraft='" + overdraft + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}

