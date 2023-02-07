package com.example.mastercardapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerMasterService {

    private final Logger logger= LoggerFactory.getLogger(ConsumerMasterService.class);
    @KafkaListener(topics="sendStatusBack", groupId = "grabStatus")
    public String grabTokenStatus(String status) {
        logger.info("The returned status is " + status +".");
        System.out.println("The returned status is " + status);
        return "The returned status is " + status;
    }

}