package com.br.bank.repository;

import com.br.bank.exception.ExternalServiceException;
import com.br.bank.model.Account;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Logger
@Slf4j
public class AccountsRepository {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> findByAccountNumber(String accountNumber) {
        try {
            return accountRepository.findById(accountNumber);
        }catch (ExternalServiceException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }


}
