package com.example.mastercardapp.controller;


import com.example.mastercardapp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaMessageController {

    @Autowired
    private CustomerService customerService;

    private Logger logger= LoggerFactory.getLogger(KafkaMessageController.class);

    @PutMapping(value = "master/producer")
    public String sendMessage(@RequestParam("token") String tokenContent) {
//        kafkaProducer.sendMessageToTopic(token);
        return "Message sent Successfully to the your code decode topic ";
    }

    @GetMapping(value="master/consumer")
    public String grabTokenStatus(@RequestParam("status") String status) {
        return customerService.grabTokenStatus(status);
    }

}
