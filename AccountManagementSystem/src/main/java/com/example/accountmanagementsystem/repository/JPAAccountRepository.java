package com.example.accountmanagementsystem.repository;


import com.example.accountmanagementsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface JPAAccountRepository extends JpaRepository<Account, String> {

    @Query(value="select acct from Account acct where acct.token = :token")
    Account findAccountByToken(@Param("token") String token);

    @Query(value="select acct from Account acct where acct.token = :token")
    Account dele(@Param("token") String token);




}
