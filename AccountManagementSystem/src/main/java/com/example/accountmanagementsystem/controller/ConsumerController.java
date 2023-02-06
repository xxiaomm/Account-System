package com.example.accountmanagementsystem.controller;


import com.example.accountmanagementsystem.service.ConsumerService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PutMapping(value="/account/producer/validate/token")
    public String validateToken(@RequestParam(value="token") String tokenContent){
        return consumerService.validateToken(tokenContent);
    }
}
