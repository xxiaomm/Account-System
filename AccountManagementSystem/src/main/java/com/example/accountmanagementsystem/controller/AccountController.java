package com.example.accountmanagementsystem.controller;

import com.example.accountmanagementsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE  })
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Register a new account, generate random id and encode token
     */
    @PostMapping(value="/register/account")
    public String registerAccount(@RequestParam(value = "name")String name){
        return accountService.registerAccount(name);
    }


    /**
     * Update account's token status according to given token
     */
    @PutMapping(value="/update/account")
    public String updateAccount(@RequestParam(value="token")String token,
                                @RequestParam(value= "status") String status) {
        return accountService.updateAccount(token, status);
    }


    /**
     * Delete account: only change status to DELETED, not really remove it from database
     */
    @DeleteMapping(value="/delete/account")
    public String deleteAccount(@RequestParam(value="token")String token) {
        return accountService.deleteAccount(token);
    }

    /**
     * Get the token status with the given token.
     */
    @GetMapping(value="/get/token/status")
    public String getTokenStatus(@RequestParam(value="token")String token) {
        return accountService.getTokenStatus(token);
    }

    /**
     * Check the token is active or inactive (expired or others).
     */
    @GetMapping(value="/validate/token")
    public String validateToken(@RequestParam(value="token")String token) {
        return accountService.validateToken(token);
    }


}
