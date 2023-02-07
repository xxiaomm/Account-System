package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entity.Pos_Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAPosStatusRepository extends JpaRepository<Pos_Status, String> {
}
