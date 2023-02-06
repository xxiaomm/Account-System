package com.example.accountmanagementsystem.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
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

import java.util.Date;
import java.util.Optional;


/**
 * get status from Post_Status DB, send to MasterCardApp
 */

@Service
public class ConsumerService {

    @Autowired
    private JPAAccountRepository jpaAccountRepository;

    @Autowired
    private JPAPostStatusRepository jpaPostStatusRepository;

    @Autowired
    private TokenService tokenService;


    private final Logger logger= LoggerFactory.getLogger(AccountService.class);



//    @KafkaListener(topics = "sendToken")
    public String validateToken(String token) {
//        Account account = jpaAccountRepository.findAccountByToken(token);
        String id = tokenService.getAccountId(token);
        Optional<Account>  foundAccount = jpaAccountRepository.findById(id);
        if (!foundAccount.isPresent()) {
            logger.warn("No matched account with given token!");
            return "No matched account with given token!";
        }
        Account account = foundAccount.get();
        String status = account.getStatus().toString();
        logger.info("the matched account's status is " + status);
        if (!isValidPostStatus(status)) {
            logger.warn("invalid token status!");
            return "invalid token status!";
        }

        Post_Status post_status = new Post_Status(token, EnumPostStatus.valueOf(status));
        jpaPostStatusRepository.save(post_status);

        logger.info("Validate token and save post status successfully!");
        return "Validate token and save post status successfully!";
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
