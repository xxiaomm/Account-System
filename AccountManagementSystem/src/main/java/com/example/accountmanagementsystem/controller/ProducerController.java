package com.example.accountmanagementsystem.controller;


import com.example.accountmanagementsystem.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @PutMapping(value="/account/consumer/send/status")
    public String sendStatusBack(@RequestParam(value="token") String status) {
        return producerService.sendStatusBack(status);
    }
}
