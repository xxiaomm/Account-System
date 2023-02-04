package com.example.accountmanagementsystem.controller;


import com.example.accountmanagementsystem.entity.Enum.Status;
import com.example.accountmanagementsystem.entity.Token;
import com.example.accountmanagementsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Register account and generate new token
    @PostMapping(value="/register/account")
    public String registerAccount(@RequestParam(value = "id")String id,
                                  @RequestParam(value = "name")String name,
                                  @RequestParam(value = "token") String tokenContent,
                                  @RequestParam(value = "status") Status status) {
        return accountService.registerAccount(id, name, tokenContent, status);
    }

    @GetMapping(value="/validate/token")
    public Status validateToken(@RequestParam(value="token")Token token) {
        return Status.ACTIVE;
    }

    @GetMapping(value="/get/status")
    public Status getTokenStatus(@RequestParam(value="token")Token token) {
        return Status.ACTIVE;
    }

    // update account for token status
    @PutMapping(value="/update/account")
    public String updateAccount(@RequestParam(value="token")Token token,
                                @RequestParam(value= "status") Status status) {
        return "success!";
    }

    // only change status to DELETED, not really remove it from database
    @DeleteMapping(value="/delete/account")
    public String deleteAccount(@RequestParam(value="token")Token token) {
        return "success!";
    }



}
