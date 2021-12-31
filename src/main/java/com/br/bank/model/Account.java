package com.br.bank.model;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull(message = "O campo nome não pode ser nulo!")
    @Size(min = 3, max = 100)
    private String name;

    @Column(name = "agencia")
    @NotBlank(message = "O campo agência não pode ser nulo!")
    @Size(min = 4, max = 4)
    private String agency;

    @Column(name = "cheque_especial_liberado")
    @NotNull
    private Boolean releasedOverdraft;

    @Column(name = "saldo")
    @NotNull(message = "O campo saldo não pode ser nulo!")
    @NotEmpty(message = "O campo saldo não pode estar vazio!")
    private String balance;

    @Column(name = "taxa")
    @NotNull(message = "O campo taxa não pode ser nulo!")
    @NotEmpty(message = "O campo taxa não pode estar vazio!")
    private String rate;

    @Column(name = "cheque_especial")
    @NotNull(message = "O campo cheque especial não pode ser nulo!")
    @NotEmpty(message = "O campo cheque especial não pode estar vazio!")
    private String overdraft;

    @PrePersist
    public void prePersist(){
        numberAccount = idGenerator();
    }

    private String idGenerator(){
        return RandomStringUtils.randomNumeric(6);
    }


    @Override
    public String toString() {
        return "CreateAccountRequestDTO{" +
                "name='" + name + '\'' +
                ", number_account='" + idGenerator() + '\'' +
                ", agency='" + agency + '\'' +
                ", released_overdraft=" + releasedOverdraft +
                ", balance='" + balance + '\'' +
                ", overdraft='" + overdraft +
                ", rate='" + rate + '\'' +
                '}';
    }



}
