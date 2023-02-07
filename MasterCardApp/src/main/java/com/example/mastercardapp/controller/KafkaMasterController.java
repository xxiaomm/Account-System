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
    private ConsumerMasterService consumerMasterService;

    @Autowired
    private ProducerMasterService producerMasterService;

    private Logger logger= LoggerFactory.getLogger(KafkaMasterController.class);

    @PutMapping(value = "master/producer")
    public String sendMessage(@RequestParam("token") String token) {
        producerMasterService.sendTokenToAccount(token);
        return "Send token from MasterCardApp to Account system!";
    }

//    @GetMapping(value="master/consumer")
//    public String grabTokenStatus(@RequestParam("status") String status) {
//        return customerService.grabTokenStatus(status);
//    }

}
