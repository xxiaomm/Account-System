package com.example.kafkatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Logger logger= LoggerFactory.getLogger(Producer.class);

    public void sendTokenToAccount (Token token) {
        logger.info("Master Card App send token to Account system!");
        kafkaTemplate.send("sendId", token.getId());

        kafkaTemplate.send("sendTime", token.getTime());

    }
}
