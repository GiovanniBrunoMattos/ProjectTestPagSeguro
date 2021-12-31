package com.br.bank.repository;

import com.br.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT * FROM CONTAS_BANCARIAS WHERE NOME = ?1", nativeQuery = true)
    List<Account> findByName(String name);

    @Query(value = "SELECT * FROM CONTAS_BANCARIAS WHERE AGENCIA = ?1 AND CHEQUE_ESPECIAL_LIBERADO = ?2 AND NOME = ?3", nativeQuery = true)
    List<Account> findAgencyReleaseOverdraftAndName(String agencia, Boolean cheque_especial, String nome);


}