package com.example.accountmanagementsystem.service;


import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Enum.EnumStatus;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;


@Service
public class AccountService {

    @Autowired
    private JPAAccountRepository jpaAccountRepository;

    @Autowired
    private TokenService tokenService;

    // AOP: 25'10''; print -> inside code itself, can not see -> log -> log file
    // log file -> shared -> info, warn, error, debug
    private final Logger logger= LoggerFactory.getLogger(AccountService.class);


    /**
     * Register a new account: default token expired date is after 30 days.
     * https://blog.csdn.net/qq_41450736/article/details/113523308
     * https://blog.csdn.net/zhangchao19890805/article/details/79191177
     * Mary, Maggie, John: 5 days, others: 30 days
     * Mia, Ann, Anna, Lily -> signature "TOKEN_SECRET"
     *
     */
    public String registerAccount(String name){
        Account account = new Account(name);
        String id = account.getId();
        account.setToken(tokenService.createToken(id, name));

        jpaAccountRepository.save(account);
        logger.info("The generated id is: " + account.getId());
        return account.getId();
//        return "Register a new account with id " + account.getId() + "successfully!";

    }



    /**
     * Update account: according to the given token, change token status
     */
    public String updateAccount(String token, String status) {
        if (!tokenService.verifyToken(token) || !verifyAccount(token))
            return "Signature has been tampered or No matched user.";
        Account account = jpaAccountRepository.findById(tokenService.getUserId(token)).get();

        if (!isValidTokenStatus(status)) {
            logger.warn("Invalid status type, please check it!");
            return "Invalid status type, please check it!\n"
                    + "All valid status is ACTIVE, DEACTIVATED, DELETED and SUSPENDED.";
        }
        account.setStatus(EnumStatus.valueOf(status));
        jpaAccountRepository.save(account);
        logger.info("Update account's status to "+status +" successfully!");
        return "Update account successfully!";
    }

    /**
     * Delete account: only change status to DELETED,
     * not really remove it from database
     */
    public String deleteAccount(String token) {
        if (!tokenService.verifyToken(token) || !verifyAccount(token))
            return "Signature has been tampered or No matched user.";
        Account account = jpaAccountRepository.findById(tokenService.getUserId(token)).get();
        account.setStatus(EnumStatus.DELETED);
        jpaAccountRepository.save(account);

        logger.info("Set status as "+ EnumStatus.DELETED +" to delete account successfully!");
        return "Delete account successfully!";
    }


    /**
     * Get token status with given token
     */
    public String getTokenStatus(String token) {
        if (!tokenService.verifyToken(token) || !verifyAccount(token))
            return "Signature has been tampered or No matched user.";
        Account account = jpaAccountRepository.findById(tokenService.getUserId(token)).get();
        return account.getStatus().toString();
//        return account.getStatus().name();

    }

    /**
     * Check the token is active or inactive (expired or others).
     */
    public String validateToken(String token) {
        if (!tokenService.verifyToken(token) || !verifyAccount(token))
            return "Signature has been tampered or No matched user.";
        Account account = jpaAccountRepository.findById(tokenService.getUserId(token)).get();
        Date expiredDate = tokenService.getExpiredDate(token);
        EnumStatus status = account.getStatus();
        if (status.equals(EnumStatus.ACTIVE)) {
            if (expiredDate.compareTo(new Date()) < 0) {
                logger.info("Unfortunately! The token has expired.");
                return "Unfortunately! The token has expired.";
            }
            return "Congratulations! The token is active, expired date is " + expiredDate;
        }

        return "Unfortunately! The token is inactive, and the status of the account is " + status;
    }


    /**
     * Check the input status type is valid.
     */
    public boolean isValidTokenStatus(String status) {
        for (EnumStatus s: EnumStatus.values()) {
            if (s.name().equals(status))
                return true;
        }
        return false;
    }


    public boolean verifyAccount(String token) {
        String id = tokenService.getUserId(token);
        Optional<Account> foundAccount = jpaAccountRepository.findById(id);
        if (!foundAccount.isPresent()) {
            logger.warn("No matched account with given token!");
            return false;
        }
        logger.info("Find matched account, start next step.");
        return true;
    }



}
