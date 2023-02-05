package com.example.accountmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;




//    private void sendEmail(SignUpRequest request) {
//        String email = request.getEmail();
//        String phoneNumber = request.getPhoneNumber();
//
//        // What’s the topic of kafka here? - topic is what’s the message for.
//        kafkaTemplate.send("topicEmail", email);
//        kafkaTemplate.send("topicPhoneNumber", phoneNumber);
//
//    }

}
