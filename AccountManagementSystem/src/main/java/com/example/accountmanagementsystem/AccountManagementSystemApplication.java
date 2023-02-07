package com.example.accountmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableDiscoveryClient
//@EnableEurekaServer
@SpringBootApplication
public class AccountManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagementSystemApplication.class, args);
    }

}

// sudo /usr/local/mysql/support-files/mysql.server start