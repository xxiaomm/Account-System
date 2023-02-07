package com.example.accountmanagementsystem.service;


import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Enum.EnumPostStatus;
import com.example.accountmanagementsystem.entity.Post_Status;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import com.example.accountmanagementsystem.repository.JPAPostStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * get status from Post_Status DB, send to MasterCardApp
 */

@Service
public class ConsumerAccountService {

    @Autowired
    private JPAAccountRepository jpaAccountRepository;

    @Autowired
    private JPAPostStatusRepository jpaPostStatusRepository;

    @Autowired
    private TokenService tokenService;

    private final Logger logger= LoggerFactory.getLogger(ConsumerAccountService.class);


//    @KafkaListener(topics = "matchToken")
//    public String matchToken(String token) {
//        String id = tokenService.getAccountId(token);
//        Optional<Account>  foundAccount = jpaAccountRepository.findById(id);
//        if (!foundAccount.isPresent()) {
//            logger.warn("No matched account with given token!");
//            return "No matched account with given token!";
//        }
//        Account account = foundAccount.get();
//        System.out.println("Matched user is " + account);
//        return "Matched user is "+ account;
//    }
//
//    @KafkaListener(topics = "showStatus")
//    public String showStatus(String token) {
//        String id = tokenService.getAccountId(token);
//        Optional<Account>  foundAccount = jpaAccountRepository.findById(id);
//        Account account = foundAccount.get();
//        System.out.println("Matched user's status is " + account.getStatus());
//        return "Matched user's status is " + account.getStatus();
//    }



    @KafkaListener(topics = "sendTokenToAccount", groupId = "validateToken")
    public void validateToken(String token) {
        String id = tokenService.getAccountId(token);
        Optional<Account>  foundAccount = jpaAccountRepository.findById(id);
        if (!foundAccount.isPresent()) {
            logger.warn("No matched account with given token!");
        }
        Account account = foundAccount.get();
        String status = account.getStatus().toString();
        logger.info("the matched account's status is " + status);

        EnumPostStatus validStatus = EnumPostStatus.valueOf(getValidPostStatus(status));
        Post_Status post_status = new Post_Status(token, validStatus);
        jpaPostStatusRepository.save(post_status);

        logger.info("Validate token and save post status successfully!");
        System.out.println(post_status);
    }


    public String getValidPostStatus(String status) {
        for (EnumPostStatus s: EnumPostStatus.values()) {
            if (s.name().equals(status))
                return status;
        }
        return "INACTIVE";
    }

    public boolean isValidPostStatus(String status) {
        for (EnumPostStatus s: EnumPostStatus.values()) {
            if (s.name().equals(status))
                return true;
        }
        return false;
    }





//    public Account validateToken(String tokenContent) {
//        Optional<Account> foundAccount = Optional.ofNullable(jpaAccountRepository.findAccountByToken(tokenContent));
//        if (!foundAccount.isPresent()) {
//            logger.warn("No matched account with given token!");
//        }
//        logger.info("Match account successfully!");
//        return foundAccount.get();
//    }
}
