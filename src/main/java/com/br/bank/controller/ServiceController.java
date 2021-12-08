package com.br.bank.controller;

import com.br.bank.model.Account;
import com.br.bank.model.dto.AccountRequestDTO;
import com.br.bank.exception.InvalidAccountException;
import com.br.bank.model.dto.AccountResponseDTO;
import com.br.bank.repository.AccountRepository;
import com.br.bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.br.bank.utils.Paths.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@Validated
public class ServiceController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = CREATE_ACCOUNT)
    public Account createAccount(@RequestBody Account accountRequest) {
        return accountService.generateAccount(accountRequest);
    }


    @GetMapping(value = FIND_BY_ALL)
    public List<AccountResponseDTO> findAll() {
        return accountService.findAll();
    }


    @GetMapping(value = QUERY_BY_ACCOUNT_NUMBER)
    public AccountResponseDTO findByAccountNumber(@PathVariable("id") String id) {
        return accountService.findByAccountNumber(id);
    }


    @DeleteMapping(value = DELETE_BY_ACCOUNT_NUMBER)
    public ResponseEntity<String> deleteAccount(@PathVariable("id") String id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);

    }

    @PutMapping(value = UPDATE_BY_ACCOUNT_NUMBER)
    public void updateAccount(@PathVariable("id") String id, @RequestBody AccountRequestDTO createAccountRequestDTO) {
        accountService.updateAccount(id, createAccountRequestDTO);

    }

}
