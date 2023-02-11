package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    JPAAccountRepository jpaAccountRepository;



    @Test
    public void TestRegisterAccount() {
        String accountId = accountService.registerAccount("Test");
        String status = jpaAccountRepository.findById(accountId).get().getStatus().toString();
        assertEquals(status, "ACTIVE");
        jpaAccountRepository.deleteById(accountId);
//        assertEquals(s, "Register a new account with id " + account.getId() + "successfully!");
    }

    @Test
    public void TestUpdateAccount() {
        String accountId = accountService.registerAccount("Tom");
        Account account = jpaAccountRepository.findById(accountId).get();
        String token = account.getToken();
        accountService.updateAccount(token,"SUSPENDED");
        assertEquals(account.getStatus(), "SUSPENDED");
        jpaAccountRepository.deleteById(accountId);

    }

    @Test
    public void TestDeleteAccount() {
        String accountId = accountService.registerAccount("Tom2");
//        String accountId = testId;
        Account account = jpaAccountRepository.findById(accountId).get();
        String token = account.getToken();
        accountService.deleteAccount(token);
        assertEquals(account.getStatus(), "DELETED");
        jpaAccountRepository.deleteById(accountId);

    }


    @Test
    public void TestGetTokenStatus() {
        String accountId = accountService.registerAccount("Tom3");
//        String accountId = testId;
        String status = jpaAccountRepository.findById(accountId).get().getStatus().toString();
        assertEquals(status, "ACTIVE");
        jpaAccountRepository.deleteById(accountId);
    }


}
