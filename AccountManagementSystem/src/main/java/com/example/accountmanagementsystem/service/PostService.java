package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.repository.JPAPostStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private JPAPostStatusRepository jpaPostStatusRepository;

    public void storeStatus() {

    }
}
