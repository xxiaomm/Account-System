package com.example.accountmanagementsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * - producer: receive token, store in Post_Status DB
 */
@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final Logger logger= LoggerFactory.getLogger(ProducerService.class);


    // return status that read from Post_Status DB to MasterCardApp
    public String sendStatusBack(String status) {
        kafkaTemplate.send("sendStatusBack", status);
        logger.info("Account system send back the status of given token to Master Card App successfully!");
        return "Send status back successfully!";
    }

}
