package com.example.accountmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//@EnableEurekaServer
public class AccountManagementSystemApplication {

    public static void main(String[] args) {
//        LocalDate myObj = LocalDate.now();  // Create a date object
//        LocalDate expiredDate = myObj.plusDays(60);
//        System.out.println(myObj.plusDays(30));
//        System.out.println(expiredDate);
//        // true
//        System.out.println(myObj.plusDays(30).isBefore(expiredDate));
        SpringApplication.run(AccountManagementSystemApplication.class, args);
    }

}
