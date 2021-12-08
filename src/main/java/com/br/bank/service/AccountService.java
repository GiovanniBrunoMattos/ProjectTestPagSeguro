package com.br.bank.service;

import com.br.bank.exception.InvalidAccountException;
import com.br.bank.model.Account;
import com.br.bank.model.dto.AccountRequestDTO;
import com.br.bank.model.dto.AccountResponseDTO;
import com.br.bank.repository.AccountRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;


    public Account generateAccount(Account requestGeneratorDTO) {
        return repository.save(requestGeneratorDTO);
    }

    public List<AccountResponseDTO> findAll() {
        var pessoas = repository.findAll();
        return pessoas
                .stream()
                .map(AccountResponseDTO::converter)
                .collect(Collectors.toList());
    }

    public AccountResponseDTO findByAccountNumber(String id) {
        var pessoas =  repository.getById(id);
        return AccountResponseDTO.converter(pessoas);
    }

    public void deleteAccount(String id) {
        repository.deleteById(id);
    }

    public AccountRequestDTO findQRCode(String numberAccount) {
        return (AccountRequestDTO) repository.findAllById(Collections.singleton(numberAccount));
    }

    public void updateAccount(String id, AccountRequestDTO createAccountRequestDTO) {
        var p = repository.findById(id);

        if (p.isPresent()) {
            var accountSave = p.get();
            accountSave.setName(createAccountRequestDTO.getName());
            accountSave.setReleasedOverdraft(Boolean.valueOf(createAccountRequestDTO.getReleasedOverdraft()));
            accountSave.setOverdraft(createAccountRequestDTO.getOverdraft());
            accountSave.setBalance(createAccountRequestDTO.getBalance());
            accountSave.setAgency(createAccountRequestDTO.getAgency());
            accountSave.setRate(createAccountRequestDTO.getRate());
            repository.save(accountSave);
        } else {
            throw new InvalidAccountException();
        }
    }


}
