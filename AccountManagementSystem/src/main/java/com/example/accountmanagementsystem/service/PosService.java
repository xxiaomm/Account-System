package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.repository.JPAPosStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosService {

    @Autowired
    private JPAPosStatusRepository jpaPosStatusRepository;

    /**
     * Get pos_status with given token
     */
    public String getPosStatus(String token) {
        return jpaPosStatusRepository.findById(token).get().getPos_token_status().toString();
    }
}
