package com.example.mastercardapp.controller;


import com.example.mastercardapp.service.ConsumerMasterService;
import com.example.mastercardapp.service.ProducerMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaMasterController {

    @Autowired
    private ProducerMasterService producerMasterService;

    private final Logger logger= LoggerFactory.getLogger(KafkaMasterController.class);


    /**
     * Send token to AccountManagementSystem to validate.
     */
    @PutMapping(value = "master/producer")
    public String sendMessage(@RequestParam("token") String token) {
        producerMasterService.sendTokenToAccount(token);
        logger.info("Start sending token from MasterCardApp to Account system:");
        return "Start sending token...";
    }


}
