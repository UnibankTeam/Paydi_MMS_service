package com.paydi.service;

import com.paydi.repository.MMSUltilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRateService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MMSUltilRepository utilRepository;

    

}
