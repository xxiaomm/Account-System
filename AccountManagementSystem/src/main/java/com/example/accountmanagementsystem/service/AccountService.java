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


    /**
     * Register a new account: default token expired date is after 30 days.
     * @param id
     * @param name
     * @param tokenContent
     * @param status
     * @return
     */
    public String registerAccount(String id, String name, String tokenContent, String status) {
        Optional<Account> foundAccount = jpaAccountRepository.findById(id);
        if (foundAccount.isPresent() || !isValidTokenStatus(status))
            return "Existed account with the same id or Invalid token status!";

        Token token = new Token(tokenContent);
        EnumStatus s = EnumStatus.valueOf(status);
        Account account = new Account(id, name, token, s);

        jpaAccountRepository.save(account);
//        System.out.println(jpaAccountRepository.getReferenceById(account.getId()).getStatus());
        return "Register account successfully!\n"
                + "the details of account in DB is: \n"
                + account;
    }

    /**
     * Update account: according to the given token, change token status
     * @param tokenContent
     * @param status
     * @return
     */
    public String updateAccount(String tokenContent, String status) {
//        // also is ok
//        Token token = jpaTokenRepository.getById(tokenContent);
//        Optional<Account> foundAccount = jpaAccountRepository.findById(token.getAccount().getId());
        Optional<Account> foundAccount = Optional.ofNullable(jpaAccountRepository.findAccountByToken(tokenContent));
        if (!foundAccount.isPresent())
            return "Account with such token does not existed.";
        Account account = foundAccount.get();
        account.setStatus(EnumStatus.valueOf(status));
        jpaAccountRepository.save(account);

        return "Update account successfully with status " + status +" !";
    }

    //

    /**
     * Delete account: only change status to DELETED, not really remove it from database
     * @param tokenContent
     * @return
     */
    public String deleteAccount(String tokenContent) {
        Optional<Account> foundAccount = Optional.ofNullable(jpaAccountRepository.findAccountByToken(tokenContent));
        if (!foundAccount.isPresent())
            return "Account with such token does not existed.";
        Account account = foundAccount.get();
        account.setStatus(EnumStatus.DELETED);
        jpaAccountRepository.save(account);

        return "Delete account successfully!";
    }


    public EnumStatus validateToken(String name, String tokenContent) {

//        jpaAccountRepository.
        return EnumStatus.ACTIVE;
    }

    public EnumStatus getTokenStatus(String tokenContent) {
        Token token = tokenService.getToken(tokenContent);
        Account account = token.getAccount();
        return account.getStatus();
    }

    public boolean isValidTokenStatus(String status) {
//        EnumStatus st = EnumStatus.valueOf(status);
        for (EnumStatus s: EnumStatus.values()) {
//            System.out.println(s.name());
            if (s.name().equals(status))
                return true;
        }
        return false;
    }

}
