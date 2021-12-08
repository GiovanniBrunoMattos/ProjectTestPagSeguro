package com.br.bank.model;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="contas_bancarias")
public class Account {

    @Id
    @Column(name = "numero_conta")
    private String numberAccount;

    @Column(name = "nome")
    private String name;

    @Column(name = "agencia")
    private String agency;

    @Column(name = "cheque_especial_liberado")
    private Boolean releasedOverdraft;

    @Column(name = "saldo")
    private String balance;

    @Column(name = "cheque_especial")
    private String overdraft;

    @Column(name = "taxa")
    private String rate;

    @PrePersist
    public void prePersist(){
        numberAccount = idGenerator();
        overdraft = String.valueOf(overdraftRate(overdraft, rate));
    }

    private String idGenerator(){
        return RandomStringUtils.randomNumeric(6);
    }

    private String overdraftRate(String overdraft, String rate){
       if(overdraft != null){
           return String.valueOf(Integer.parseInt(overdraft) + ((Integer.parseInt(rate) / 100) * 100));
       }
       return null;
    }

    @Override
    public String toString() {
        return "CreateAccountRequestDTO{" +
                "name='" + name + '\'' +
                ", number_account='" + idGenerator() + '\'' +
                ", agency='" + agency + '\'' +
                ", released_overdraft=" + releasedOverdraft +
                ", balance='" + balance + '\'' +
                ", overdraft='" + overdraft + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }



}
