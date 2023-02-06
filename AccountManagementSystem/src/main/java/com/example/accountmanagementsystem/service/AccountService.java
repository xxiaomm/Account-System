package com.example.accountmanagementsystem.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Enum.EnumStatus;
//import com.example.accountmanagementsystem.entity.Token;
import com.example.accountmanagementsystem.repository.JPAAccountRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;


/** reference
 * https://www.cnblogs.com/ChromeT/p/10932202.html
 * https://www.cnblogs.com/OnlyOne2048/p/14203654.html
 */

@Service
public class AccountService {

    @Autowired
    private JPAAccountRepository jpaAccountRepository;

    @Autowired
    private TokenService tokenService;

    // AOP: 25'10''; print -> inside code itself, can not see
    // log file -> shared -> info, warn, error, debug
    //得到日志对象
    private Logger logger= LoggerFactory.getLogger(AccountService.class);




    /**
     * Register a new account: default token expired date is after 30 days.
     * https://blog.csdn.net/qq_41450736/article/details/113523308
     * https://blog.csdn.net/zhangchao19890805/article/details/79191177
     */
    public String registerAccount(String name){
        Account account = new Account(name);
        String id = account.getId();
        account.setToken(tokenService.createToken(id, name));

        jpaAccountRepository.save(account);
        logger.info("The generated id is: " + account.getId());
        return "Register account successfully with generated id " + account.getId()
                +" the generated token is " + account.getToken();
    }



    /**
     * Update account: according to the given token, change token status
     */
    public String updateAccount(String tokenContent, String status) {
        String id = tokenService.getAccountId(tokenContent);
        Optional<Account> foundAccount = jpaAccountRepository.findById(id);
        if (!foundAccount.isPresent()) {
            logger.warn("No matched account with given token!");
            return "Account with such token does not exist.";
        }

        Account account = foundAccount.get();
        account.setStatus(EnumStatus.valueOf(status));
        jpaAccountRepository.save(account);
        logger.info("Update account's status to "+status +" successfully!");
        return "Update account successfully!";
    }

    /**
     * Delete account: only change status to DELETED, not really remove it from database
     */
    public String deleteAccount(String tokenContent) {
        String id = tokenService.getAccountId(tokenContent);
        Optional<Account> foundAccount = jpaAccountRepository.findById(id);
        if (!foundAccount.isPresent()) {
            logger.warn("No matched account with given token!");
            return "Account with such token does not exist.";
        }
        Account account = foundAccount.get();
        account.setStatus(EnumStatus.DELETED);
        jpaAccountRepository.save(account);

        logger.info("Set status as "+ EnumStatus.DELETED +" to delete account successfully!");
        return "Delete account successfully!";
    }

    /**
     * Get token status with given token
     */
    public String getTokenStatus(String tokenContent) {
        String id = tokenService.getAccountId(tokenContent);
        Optional<Account> foundAccount = jpaAccountRepository.findById(id);
        if (!foundAccount.isPresent()) {
            logger.error("Token does not exist");
            return "Token does not exist.";
        }
        Account account = foundAccount.get();
        return account.getStatus().toString();
//        return account.getStatus().name();

    }


//    public String validateToken(String tokenContent, String status) {
//        Optional<Account> foundAccount = Optional.ofNullable(jpaAccountRepository.findAccountByToken(tokenContent));
//        if (!foundAccount.isPresent()) {
//            logger.warn("No matched account with given token!");
////            return false;
//            return "No matched account with given token!";
//        }
//        Account account = foundAccount.get();
//        if (!account.getStatus().toString().equals(status)){
//            logger.warn("Status not matched!");
////            return false;
//            return "Status not matched";
//        }
////        return true;
//        return "Match status successfully";
//    }


    public boolean isValidTokenStatus(String status) {
        for (EnumStatus s: EnumStatus.values()) {
            if (s.name().equals(status))
                return true;
        }
        return false;
    }



}
