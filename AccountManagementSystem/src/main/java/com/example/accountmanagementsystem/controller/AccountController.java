package com.example.accountmanagementsystem.controller;


import com.example.accountmanagementsystem.entity.Account;
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

    /**
     * Register a new account, generate random id and corresponding token
     * @param name
     * @param tokenContent
     * @param status
     * @return
     */
    @PostMapping(value="/register/account")
    public String registerAccount(@RequestParam(value = "name")String name,
                                  @RequestParam(value = "token") String tokenContent,
                                  @RequestParam(value = "status") String status) {
        return accountService.registerAccount(name, tokenContent, status);
    }


    /**
     * Update account's token status according to given token
     * @param tokenContent
     * @param status
     * @return
     */
    @PutMapping(value="/update/account")
    public String updateAccount(@RequestParam(value="token")String tokenContent,
                                @RequestParam(value= "status") String status) {
        return accountService.updateAccount(tokenContent, status);
    }

    /**
     * Delete account: only change status to DELETED, not really remove it from database
     * @param tokenContent
     * @return
     */
    @DeleteMapping(value="/delete/account")
    public String deleteAccount(@RequestParam(value="token")String tokenContent) {
        return accountService.deleteAccount(tokenContent);
    }

    @GetMapping(value="/get/token/status")
    public String getTokenStatus(@RequestParam(value="token")String tokenContent) {
        return accountService.getTokenStatus(tokenContent);
    }

    @GetMapping(value="/validate/token")
    public EnumStatus validateToken(@RequestParam(value="token")String tokenContent) {
        return EnumStatus.ACTIVE;
    }
//    public EnumStatus validateToken(@RequestParam(value="token")String tokenContent) {
//        return EnumStatus.STATUS1;
//    }


}
