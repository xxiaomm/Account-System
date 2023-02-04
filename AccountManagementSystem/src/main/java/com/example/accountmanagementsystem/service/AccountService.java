package com.example.accountmanagementsystem.service;


import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Enum.Status;
import com.example.accountmanagementsystem.entity.Token;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import com.example.accountmanagementsystem.repository.JPATokenRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private JPAAccountRepository jpaAccountRepository;

    @Autowired
    private JPATokenRepository jpaTokenRepository;

    @Autowired
    private TokenService tokenService;


    public String registerAccount(String id, String name, String tokenContent, Status status) {
        Optional<Account> foundAccount = jpaAccountRepository.findById(id);
        if (foundAccount.isPresent() || !isValidTokenStatus(status))
            return "Existed account with the same id or Invalid token status!";

        Token token = new Token(tokenContent);
        Account account = new Account(id, name, token, status);

        jpaAccountRepository.save(account);
        return "Register account successfully with details: " + account +" !";
    }



    public Status validateToken(Token token) {
//        jpaAccountRepository. 
        return Status.ACTIVE;
    }

    public Status getTokenStatus(String tokenContent) {
        Token token = tokenService.getToken(tokenContent);
        Account account = token.getAccount();
        return account.getStatus();
    }

    // update account for token status
    public String updateAccount(Token token, Status status) {
        return "success!";
    }

    // only change status to DELETED, not really remove it from database
    public String deleteAccount(Token token) {
        return "success!";
    }



    public boolean isValidTokenStatus(Status status) {
        for (Status s: Status.values()) {
            if (!status.equals(s))
                continue;
            else return true;
        }
        return false;
    }

}
