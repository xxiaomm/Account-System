package com.example.accountmanagementsystem.controller;


import com.example.accountmanagementsystem.entity.Enum.EnumStatus;
import com.example.accountmanagementsystem.entity.Token;
import com.example.accountmanagementsystem.service.AccountService;
import com.example.accountmanagementsystem.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE  })
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenService tokenService;

    // Register account and generate new token
    @PostMapping(value="/register/account")
    public String registerAccount(@RequestParam(value = "id")String id,
                                  @RequestParam(value = "name")String name,
                                  @RequestParam(value = "token") String tokenContent,
                                  @RequestParam(value = "status") String status) {
        return accountService.registerAccount(id, name, tokenContent, status);
    }

    @GetMapping(value="/validate/token")
    public EnumStatus validateToken(@RequestParam(value="token")String tokenContent) {
        return EnumStatus.ACTIVE;
    }
//    public EnumStatus validateToken(@RequestParam(value="token")String tokenContent) {
//        return EnumStatus.STATUS1;
//    }

    @GetMapping(value="/get/status")
    public EnumStatus getTokenStatus(@RequestParam(value="token")String tokenContent) {
        return EnumStatus.ACTIVE;
    }
//    public EnumStatus getTokenStatus(@RequestParam(value="token")String tokenContent) {
//        return EnumStatus.STATUS1;
//    }

    // update account for token status
    @PutMapping(value="/update/account")
    public String updateAccount(@RequestParam(value="token")String tokenContent,
                                @RequestParam(value= "status") String status) {
        return accountService.updateAccount(tokenContent, status);
    }

    // only change status to DELETED, not really remove it from database
    @DeleteMapping(value="/delete/account")
    public String deleteAccount(@RequestParam(value="token")String tokenContent) {
        return accountService.deleteAccount(tokenContent);
    }



}
