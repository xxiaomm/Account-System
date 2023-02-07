package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.repository.JPAPostStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * - producer: receive token, store in Post_Status DB
 */
@Service
public class ProducerAccountService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private JPAPostStatusRepository jpaPostStatusRepository;

    @Autowired
    private PostService postService;

    private final Logger logger= LoggerFactory.getLogger(ProducerAccountService.class);


    // return status that read from Post_Status DB to MasterCardApp
    public String sendStatusBack(String token) {
        String status = postService.getPostStatus(token);
        kafkaTemplate.send("sendStatusBack", status);
        logger.info("Account system send back the status of given token to Master Card App successfully!");
        return "Send status back successfully!";
    }

}
