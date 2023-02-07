//package com.example.accountmanagementsystem.service;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProducerMasterService {
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private Logger logger= LoggerFactory.getLogger(ProducerMasterService.class);
//
//    public void sendTokenToAccount (String token) {
//        logger.info("Master Card App send token to Account system!");
//        kafkaTemplate.send("sendTokenToAccount", token);
//
//    }
//
//}
