package com.example.kafkatest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    Producer producer;


    @PutMapping(value = "master/producer")
    public String sendMessage(@RequestParam("id") String id,
                              @RequestParam("time") String time) {
        producer.sendTokenToAccount(new Token(id, time));
        return "Send token from MasterCardApp to Account system!";
    }

}
