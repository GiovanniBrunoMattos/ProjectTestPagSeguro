package com.br.bank.service;

import com.br.bank.enums.ErrorCode;
import com.br.bank.exception.InvalidAccountException;
import com.br.bank.model.Account;
import com.br.bank.model.dto.AccountResponseDTO;
import com.br.bank.model.dto.FailResponseDTO;
import com.br.bank.repository.AccountRepository;
import com.br.bank.repository.AccountsRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.br.bank.enums.MessagesResponse.*;

@Slf4j
@Data
@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountsRepository accountsRepository;

    public static AccountResponseDTO converter(Account p) {
        var account = new AccountResponseDTO();
        account.setName(p.getName());
        account.setAgency(p.getAgency().concat("/").concat(p.getNumberAccount()));
        account.setReleasedOverdraft(converterReleasedOverdraft(p.getReleasedOverdraft()));
        account.setBalance(p.getBalance());
        account.setRate(p.getRate());
        account.setOverdraft(overdraftRate(p.getOverdraft(), p.getRate()).replace(".", ","));
        return account;
    }

    public static String converterReleasedOverdraft(Boolean releasedOverdraft){
        if (releasedOverdraft.equals(true)){
            return "Liberado";
        }
        return "Não Liberado";
    }

    public static String overdraftRate(String overdraft, String rate){
        double overdraftConvert = Double.parseDouble(overdraft.replace(",", "."));
        double rateConvert = Double.parseDouble(rate.replace(",", "."));

        return String.valueOf(overdraftConvert + (rateConvert / 100) * overdraftConvert);
    }

    public Account generateAccount(Account requestGeneratorDTO) {
        return repository.save(requestGeneratorDTO);
    }

    public List<AccountResponseDTO> findAll() {
        var accounts = repository.findAll();
        return accounts
                .stream()
                .map(AccountService::converter)
                .collect(Collectors.toList());
    }

    public Optional<AccountResponseDTO> findByAccountNumber(String id) {
        Optional<Account> account =  accountsRepository.findByAccountNumber(id);
        if (account.isPresent()){
        return account.map(AccountService::converter);
        }
        throw new InvalidAccountException(INVALID_ACCOUNT_NUMBER.getMessage(), //
                new FailResponseDTO(ErrorCode.INVALID_ACCOUNT_NUMBER_CODE, //
                        INVALID_ACCOUNT_NUMBER.getMessage()), //
                        HttpStatus.NOT_FOUND);

    }

    public List<AccountResponseDTO> findByAgencyReleaseOverdraftAndName(String agencia, Boolean cheque_especial, String nome) {
        var account = repository.findAgencyReleaseOverdraftAndName(agencia, cheque_especial, nome);
        if (!account.isEmpty()){
            return account
                    .stream()
                    .map(AccountService::converter)
                    .collect(Collectors.toList());
        }
        throw new InvalidAccountException(INVALID_DATA.getMessage(), //
                new FailResponseDTO(ErrorCode.INVALID_DATA_CODE, //
                        INVALID_DATA.getMessage()), //
                HttpStatus.NOT_FOUND);
    }

    public List<AccountResponseDTO> findByName(String name) {
        var account = repository.findByName(name);
        if(!account.isEmpty()) {
            return account
                    .stream()
                    .map(AccountService::converter)
                    .collect(Collectors.toList());
        }
        throw new InvalidAccountException(INVALID_ACCOUNT_NAME.getMessage(), //
                new FailResponseDTO(ErrorCode.INVALID_ACCOUNT_NAME_CODE, //
                        INVALID_ACCOUNT_NAME.getMessage()), //
                HttpStatus.NOT_FOUND);

    }

    public void deleteAccount(String id) {
        repository.deleteById(id);
    }

    public Account updateAccount(String id, Account createAccountRequestDTO) {
        var p = repository.findById(id);

        if (p.isPresent()) {
            var accountSave = p.get();
            accountSave.setName(createAccountRequestDTO.getName());
            accountSave.setReleasedOverdraft(createAccountRequestDTO.getReleasedOverdraft());
            accountSave.setOverdraft(createAccountRequestDTO.getOverdraft());
            accountSave.setBalance(createAccountRequestDTO.getBalance());
            accountSave.setAgency(createAccountRequestDTO.getAgency());
            accountSave.setRate(createAccountRequestDTO.getRate());
            return repository.save(accountSave);
        }
        throw new InvalidAccountException(INVALID_ACCOUNT_NUMBER.getMessage(), //
                    new FailResponseDTO(ErrorCode.INVALID_ACCOUNT_NUMBER_CODE, //
                                        INVALID_ACCOUNT_NUMBER.getMessage()), //
                                        HttpStatus.NOT_FOUND);

    }


}
