package com.example.accountmanagementsystem.controller;


import com.example.accountmanagementsystem.service.ConsumerAccountService;
import com.example.accountmanagementsystem.service.ProducerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaAccountController {

    @Autowired
    private ConsumerAccountService consumerAccountService;

    @Autowired
    private ProducerAccountService producerAccountService;

    @PutMapping(value="/account/producer/validate/token")
    public String validateToken(@RequestParam(value="token") String tokenContent){
        consumerAccountService.validateToken(tokenContent);
        return "Validate token and save post status successfully!";
    }


    @GetMapping(value="/account/consumer/send/status")
    public String sendStatusBack(@RequestParam(value="token") String token) {
        return producerAccountService.sendStatusBack(token);
    }

}
