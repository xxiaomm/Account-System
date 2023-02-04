package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JPATokenRepository extends JpaRepository<Token, String> {

}
