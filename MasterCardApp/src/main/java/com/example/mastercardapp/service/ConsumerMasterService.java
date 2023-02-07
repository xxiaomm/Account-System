package com.example.mastercardapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerMasterService {

    private final Logger logger= LoggerFactory.getLogger(ConsumerMasterService.class);


    /**
     * KafkaListener:
     * Grab the status sent from AccountManagementSystem and print it out.
     */
    @KafkaListener(topics="sendStatusBack", groupId = "grabStatus")
    public void grabId(String status) {
        logger.info("The returned status is " + status +".");
        System.out.println("The returned status is " + status);

    }
    @KafkaListener(topics="sendIdBack", groupId = "grabStatus")
    public void grabName(String id) {
        logger.info("The returned id is " + id +".");
        System.out.println("The returned id is " + id);
    }
    @KafkaListener(topics="sendNameBack", groupId = "grabStatus")
    public void grabTokenStatus(String name) {
        logger.info("The returned name is " + name +".");
        System.out.println("The returned name is " + name);
    }

}
