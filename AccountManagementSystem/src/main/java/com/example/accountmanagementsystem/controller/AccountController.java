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
     * Register a new account, generate random id and corresponding token
     */
    @PostMapping(value="/register/account")
    public String registerAccount(@RequestParam(value = "name")String name){
//                                  @RequestParam(value = "token") String tokenContent,
//                                  @RequestParam(value = "status") String status) {
        return accountService.registerAccount(name);
    }


    /**
     * Update account's token status according to given token
     */
    @PutMapping(value="/update/account")
    public String updateAccount(@RequestParam(value="token")String tokenContent,
                                @RequestParam(value= "status") String status) {
        return accountService.updateAccount(tokenContent, status);
    }

    /**
     * Delete account: only change status to DELETED, not really remove it from database
     */
    @DeleteMapping(value="/delete/account")
    public String deleteAccount(@RequestParam(value="token")String tokenContent) {
        return accountService.deleteAccount(tokenContent);
    }

    @GetMapping(value="/get/token/status")
    public String getTokenStatus(@RequestParam(value="token")String tokenContent) {
        return accountService.getTokenStatus(tokenContent);
    }

//    @GetMapping(value="/validate/token")
//    public String validateToken(@RequestParam(value="token")String tokenContent,
//                                 @RequestParam(value="status")String status) {
//        return accountService.validateToken(tokenContent, status);
//    }


}
