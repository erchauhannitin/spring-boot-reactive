package com.perfect.service;

import com.perfect.dao.CustomerDao;
import com.perfect.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> getCustomersSync() {

        long startTime = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomersSync();
        long endTime = System.currentTimeMillis();
        log.info("Sync Execution time {}", endTime-startTime );

        return customers;
    }

    public Flux<Customer> getCustomersAsync() {

        long startTime = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersAsync();
        long endTime = System.currentTimeMillis();
        log.info("Async Execution time {}", endTime-startTime );

        return customers;
    }
}
