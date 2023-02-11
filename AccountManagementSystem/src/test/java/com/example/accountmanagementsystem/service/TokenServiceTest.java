package com.example.accountmanagementsystem.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TokenServiceTest {

    @Autowired
    JPAAccountRepository jpaAccountRepository;


    // Iris
    private final String testId = "042a8fd8-24d4-4172-bc63-9cd4eb4e4a00";

    @Test
    public void TestVerifyToken() {
        Account account = jpaAccountRepository.findById(testId).get();
        String token = account.getToken();
        DecodedJWT jwt = JWT.decode(token);
        assertEquals("Iris", jwt.getClaim("user_name").asString());

    }


}
