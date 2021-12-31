package com.br.bank.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequestDTO {

    private String name;

    private String agency;

    private String releasedOverdraft;

    private String balance;

    private String overdraft;

    private String rate;



}
