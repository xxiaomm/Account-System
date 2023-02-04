package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entity.Post_Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAPostStatusRepository extends JpaRepository<Post_Status, String> {
}
