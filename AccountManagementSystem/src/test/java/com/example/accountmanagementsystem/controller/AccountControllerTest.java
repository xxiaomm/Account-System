package com.example.accountmanagementsystem.controller;

import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import com.example.accountmanagementsystem.service.AccountService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountControllerTest {

    @Autowired
    AccountController accountController;

    @Autowired
    AccountService accountService;

    @Autowired
    JPAAccountRepository jpaAccountRepository;

    // Iris
//    private final String testId = "042a8fd8-24d4-4172-bc63-9cd4eb4e4a00";

//    @Before
//    public void setUp() {
//        Mockito.when(accountService.getTokenStatus())
//                .thenReturn();
//    }

    @Test
    public void TestRegisterAccount() {
        String accountId = accountController.registerAccount("Test");
        String status = jpaAccountRepository.findById(accountId).get().getStatus().toString();
        assertEquals(status, "ACTIVE");
        jpaAccountRepository.deleteById(accountId);
//        assertEquals(s, "Register a new account with id " + account.getId() + "successfully!");
    }

    @Test
    public void TestUpdateAccount() {
        String accountId = accountController.registerAccount("Tom");
//        String accountId = testId;
        Account account = jpaAccountRepository.findById(accountId).get();
        String token = account.getToken();
        accountService.updateAccount(token,"SUSPENDED");
        assertEquals(account.getStatus(), "SUSPENDED");
        jpaAccountRepository.deleteById(accountId);
    }

    @Test
    public void TestDeleteAccount() {
        String accountId = accountController.registerAccount("Tom2");
//        String accountId = testId;
        Account account = jpaAccountRepository.findById(accountId).get();
        String token = account.getToken();
        accountService.deleteAccount(token);
        assertEquals(account.getStatus(), "DELETED");
        jpaAccountRepository.deleteById(accountId);
    }


    @Test
    public void TestGetTokenStatus() {
        String accountId = accountController.registerAccount("Tom3");
//        String accountId = testId;
        String status = jpaAccountRepository.findById(accountId).get().getStatus().toString();
        assertEquals(status, "ACTIVE");
        jpaAccountRepository.deleteById(accountId);
    }


}


/**
 * Cover corresponding every public method
 */