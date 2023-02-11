package com.example.accountmanagementsystem.controller;


import com.example.accountmanagementsystem.service.ConsumerAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class KafkaAccountControllerTest {

    @Autowired
    KafkaAccountController kafkaAccountController;

    @Autowired
    ConsumerAccountService consumerAccountService;

    // Iris
    private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMDQyYThmZDgtMjRkNC00MTcyLWJjNjMtOWNkNGViNGU0YTAwIiwidXNlcl9uYW1lIjoiSXJpcyIsImV4cCI6MTY3ODM5NDg4NX0.Qx4yPyT69yD3n3mWXdw4AOD3dIjwnuhVGwrApgw5_1o";

    @Test
    public void TestValidateToken() {
        String s = kafkaAccountController.validateToken(token);
        assertEquals(s, "Validate token and save post status successfully!");
    }



}
