package com.example.accountmanagementsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * - producer: receive token, store in Pos_Status DB
 */
@Service
public class ProducerAccountService {

    @Autowired
    private KafkaTemplate <String, String> kafkaTemplate;


    private final Logger logger= LoggerFactory.getLogger(ProducerAccountService.class);


    /**
     * Send status back read from Pos_Status DB to MasterCardApp
     */
    public void sendStatusBack(String id, String name, String status) {
        kafkaTemplate.send("sendIdBack", id);
        kafkaTemplate.send("sendNameBack", name);
        kafkaTemplate.send("sendStatusBack", status);
        logger.info("Account system send back the status of given token to Master Card App successfully!");
    }

}
