package com.example.accountmanagementsystem.service;


import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Enum.EnumStatus;
import com.example.accountmanagementsystem.entity.Token;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private JPAAccountRepository jpaAccountRepository;
    // AOP: 25'10''; print -> inside code itself, can not see
    // log file -> shared -> info, warn, error, debug
    //得到日志对象
    private static final Logger logger= LoggerFactory.getLogger(AccountService.class);
//    private static final Logger logger2= LoggerFactory.getLogger("AccountService");


    /**
     * Register a new account: default token expired date is after 30 days.
     * @//param id
     * @param name
     * @param tokenContent
     * @param status
     * @return
     */
    public String registerAccount(String name, String tokenContent, String status) {
        if (!isValidTokenStatus(status)) {
            logger.error("Invalid token status!");
            return "Invalid token status!";
        }

        Token token = new Token(tokenContent);
        EnumStatus s = EnumStatus.valueOf(status);
        Account account = new Account(name, token, s);

        jpaAccountRepository.save(account);
        logger.info("The generated id is: " + account.getId());
        return "Register account successfully with generated id " + account.getId();
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
        if (!foundAccount.isPresent()) {
            logger.warn("No matched account with given token!");
            return "Account with such token does not exist.";
        }

        Account account = foundAccount.get();
        account.setStatus(EnumStatus.valueOf(status));
        jpaAccountRepository.save(account);
        logger.info("Update account's status to "+status +" successfully!");
        return "Update account successfully!";
    }

    /**
     * Delete account: only change status to DELETED, not really remove it from database
     * @param tokenContent
     * @return
     */
    public String deleteAccount(String tokenContent) {
        Optional<Account> foundAccount = Optional.ofNullable(jpaAccountRepository.findAccountByToken(tokenContent));
        if (!foundAccount.isPresent()) {
            logger.warn("No matched account with given token!");
            return "Account with such token does not exist.";
        }
        Account account = foundAccount.get();
        account.setStatus(EnumStatus.DELETED);
        jpaAccountRepository.save(account);

        logger.info("Set status as "+ EnumStatus.DELETED +" to delete account successfully!");
        return "Delete account successfully!";
    }

    /**
     * Get token status with given token
     * @param tokenContent
     * @return
     */

    public String getTokenStatus(String tokenContent) {
        Optional<Account> foundAccount = Optional.ofNullable(jpaAccountRepository.findAccountByToken(tokenContent));
        if (!foundAccount.isPresent()) {
            logger.error("Token does not exist");
            return "Token does not exist.";
        }
        Account account = foundAccount.get();
        return account.getStatus().toString();
//        return account.getStatus().name();

    }


    public EnumStatus validateToken(String name, String tokenContent) {

//        jpaAccountRepository.
        return EnumStatus.ACTIVE;
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
