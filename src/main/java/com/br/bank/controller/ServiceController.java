package com.br.bank.controller;

import com.br.bank.enums.ErrorCode;
import com.br.bank.model.Account;
import com.br.bank.exception.InvalidAccountException;
import com.br.bank.model.dto.AccountResponseDTO;
import com.br.bank.model.dto.FailResponseDTO;
import com.br.bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.br.bank.enums.MessagesResponse.INVALID_ACCOUNT_NUMBER;
import static com.br.bank.utils.Paths.V1_CREATE_ACCOUNT;
import static com.br.bank.utils.Paths.V1_DELETE_BY_ACCOUNT_NUMBER;
import static com.br.bank.utils.Paths.V1_FIND_BY_ALL;
import static com.br.bank.utils.Paths.V1_QUERY_BY_ACCOUNT_NUMBER;
import static com.br.bank.utils.Paths.V1_QUERY_BY_AGENCY_RELEASEDOVERDRAFT_NAME;
import static com.br.bank.utils.Paths.V1_QUERY_BY_NAME;
import static com.br.bank.utils.Paths.V1_UPDATE_BY_ACCOUNT_NUMBER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@Validated
public class ServiceController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = V1_CREATE_ACCOUNT)
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account accountRequest) {
        Account generateResponse = accountService.generateAccount(accountRequest);
        return new ResponseEntity<>(generateResponse, HttpStatus.OK);
    }


    @GetMapping(value = V1_FIND_BY_ALL)
    public ResponseEntity<AccountResponseDTO> findAll() {
        List<AccountResponseDTO> findAllAccounts =  accountService.findAll();
        return new ResponseEntity(findAllAccounts, HttpStatus.OK);
    }

    @GetMapping(value = V1_QUERY_BY_NAME)
    public ResponseEntity<AccountResponseDTO> findByName(@PathVariable("id") String id) {
        List<AccountResponseDTO> findAccountsByName = accountService.findByName(id);
        return new ResponseEntity(findAccountsByName, HttpStatus.OK);
    }


    @GetMapping(value = V1_QUERY_BY_ACCOUNT_NUMBER)
    public ResponseEntity<AccountResponseDTO> findByAccountNumber(@PathVariable("id") String id) {
        Optional<AccountResponseDTO> findAccountsByAccountNumber = accountService.findByAccountNumber(id);
        return new ResponseEntity(findAccountsByAccountNumber, HttpStatus.OK);
    }

    @GetMapping(value = V1_QUERY_BY_AGENCY_RELEASEDOVERDRAFT_NAME)
    public ResponseEntity<AccountResponseDTO> findByAgencyReleasedOverdraftAndName(@RequestParam("agency") String agencia,
                                                                                   @RequestParam("cheque_especial_liberado") Boolean cheque_especial,
                                                                                   @RequestParam("nome") String nome) {
        List<AccountResponseDTO> findAccountsByAgency = accountService.findByAgencyReleaseOverdraftAndName(agencia, cheque_especial, nome);
        return new ResponseEntity(findAccountsByAgency, HttpStatus.OK);
    }

    @DeleteMapping(value = V1_DELETE_BY_ACCOUNT_NUMBER)
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") String id) {
        try {
            accountService.deleteAccount(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new InvalidAccountException(INVALID_ACCOUNT_NUMBER.getMessage(), new FailResponseDTO(ErrorCode.INVALID_ACCOUNT_NUMBER_CODE, //
                            INVALID_ACCOUNT_NUMBER.getMessage()), //
                            HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = V1_UPDATE_BY_ACCOUNT_NUMBER)
    public ResponseEntity<Account> updateAccount(@Valid @PathVariable("id") String id, @RequestBody Account createAccountRequestDTO) {
        Account updateAccount = accountService.updateAccount(id, createAccountRequestDTO);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }

}
