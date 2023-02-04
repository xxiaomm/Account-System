package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Token;
import com.example.accountmanagementsystem.repository.JPATokenRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private JPATokenRepository jpaTokenRepository;


    public Token getToken(String tokenContent) {
        Token token = jpaTokenRepository.getById(tokenContent);
        System.out.println(token);
        return token;
    }

}
