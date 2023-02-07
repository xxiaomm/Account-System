package com.example.kafkatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {


    private final Logger logger= LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "sendId", groupId = "validateToken")
    public void sendId(String id) {
        System.out.println("id is " + id);
//        System.out.println("time is " + token.getTime());
        logger.info("Validate token id: " + id +" and save post status successfully!");
    }

    @KafkaListener(topics = "sendTime", groupId = "validateToken")
    public void sendTime(String time) {
//        System.out.println("id is " + token.getId());
        System.out.println("time is " + time);
        logger.info("Validate token time: " + time +" and save post status successfully!");
    }
}
