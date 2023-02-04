package com.example.accountmanagementsystem.service;


import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Enum.EnumStatus;
import com.example.accountmanagementsystem.entity.Token;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import com.example.accountmanagementsystem.repository.JPATokenRepository;
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


    public String registerAccount(String id, String name, String tokenContent, String status) {
        Optional<Account> foundAccount = jpaAccountRepository.findById(id);
        if (foundAccount.isPresent() || !isValidTokenStatus(status))
            return "Existed account with the same id or Invalid token status!";

        Token token = new Token(tokenContent);
        EnumStatus s = EnumStatus.valueOf(status);
        Account account = new Account(id, name, token, s);

        jpaAccountRepository.save(account);
        System.out.println(jpaAccountRepository.getReferenceById(account.getId()).getStatus());
        return "Register account successfully with details: " + account +" !";
    }



//    public EnumStatus validateToken(Token token) {
////        jpaAccountRepository.
//        return EnumStatus.STATUS1;
//    }

    public EnumStatus getTokenStatus(String tokenContent) {
        Token token = tokenService.getToken(tokenContent);
        Account account = token.getAccount();
        return account.getStatus();
    }

    // update account for token status
    public String updateAccount(Token token, EnumStatus status) {
        return "success!";
    }

    // only change status to DELETED, not really remove it from database
    public String deleteAccount(Token token) {
        return "success!";
    }



    public boolean isValidTokenStatus(String status) {
        EnumStatus st = EnumStatus.valueOf(status);
        for (EnumStatus s: EnumStatus.values()) {
            if (!s.equals(st))
                continue;
            else return true;
        }
        return false;
    }

}
