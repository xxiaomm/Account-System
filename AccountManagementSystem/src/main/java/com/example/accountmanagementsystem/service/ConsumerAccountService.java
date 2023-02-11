package com.example.accountmanagementsystem.service;


import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Enum.EnumPosStatus;
import com.example.accountmanagementsystem.entity.Pos_Status;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import com.example.accountmanagementsystem.repository.JPAPosStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


/**
 * get status from Pos_Status DB, send to MasterCardApp
 */

@Service
public class ConsumerAccountService {

    @Autowired
    private JPAAccountRepository jpaAccountRepository;

    @Autowired
    private JPAPosStatusRepository jpaPosStatusRepository;

    @Autowired
    private AccountService accountService;
    @Autowired
    private TokenService tokenService;

    @Autowired ProducerAccountService producerAccountService;

    private final Logger logger= LoggerFactory.getLogger(ConsumerAccountService.class);


    /**
     * KafkaListener:
     * receive token from MasterCardApp, find corresponding status, and store in Pos_Status DataBase.
     */
    @KafkaListener(topics = "sendTokenToAccount", groupId = "validateToken")
    public void validateTokenAndSendStatusBack(String token) {
        logger.info("Start validating token...");
        if ( !tokenService.verifyToken(token) || !accountService.verifyAccount(token)) {
            logger.error("Signature has been tampered or No matched user.");
            return;
        }
        Account account = jpaAccountRepository.findById(tokenService.getUserId(token)).get();
        String status = account.getStatus().toString();
        logger.info("the matched account's status is " + status);

        Date expired_Date = tokenService.getExpiredDate(token);
        EnumPosStatus validStatus = EnumPosStatus.valueOf(transeferToPosStatus(status, expired_Date));
        logger.info("the validStatus is " + validStatus);
        Pos_Status pos_status = new Pos_Status(token, validStatus);
        jpaPosStatusRepository.save(pos_status);
        logger.info("the pos_status is " + pos_status);
        logger.info("Validate token and save pos_status: " + pos_status + " successfully!");

        System.out.println("pos_status: " + pos_status);


        logger.info("Start sending pos_status back to MasterCardApp...");
        producerAccountService.sendStatusBack(account.getId(), account.getName(), pos_status.getPos_token_status().toString());
    }


    /**
     * Get valid Pos_Status
     * if active and within expired date -> active,
     * others -> inactive.
     */
    public String transeferToPosStatus(String status, Date expired_Date) {
        logger.info("Start getting valid pos_status...");

        for (EnumPosStatus s: EnumPosStatus.values()) {
            // the status is active and the expired_date is after the current date
            if (s.name().equals(status) && expired_Date.compareTo(new Date()) > 0) {
                logger.info("the token is active, and the expired date is: " + expired_Date);
                return status;
            }
        }

        return "INACTIVE";
    }

}
