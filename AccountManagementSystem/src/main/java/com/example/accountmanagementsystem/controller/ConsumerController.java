package com.example.accountmanagementsystem.controller;


import com.example.accountmanagementsystem.service.ConsumerService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @PutMapping(value="/store/post/status")
    public String storePostStatus(@RequestParam(value="token") String tokenContent){
        return consumerService.validateTokenAndStorePostStatus(tokenContent);
    }
}
