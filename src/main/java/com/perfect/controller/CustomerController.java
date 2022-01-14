package com.perfect.controller;

import com.perfect.model.Customer;
import com.perfect.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired CustomerService customerService;

    @GetMapping("sync")
    public List<Customer> getCustomersSync(){
        return customerService.getCustomersSync();
    }

    @GetMapping(value = "async", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersAsync(){
        return customerService.getCustomersAsync();
    }
}
