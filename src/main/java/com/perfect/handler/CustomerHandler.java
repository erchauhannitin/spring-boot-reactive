package com.perfect.handler;

import com.perfect.dao.CustomerDao;
import com.perfect.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomerHandler {

    @Autowired
    CustomerDao customerDao;

    public Mono<ServerResponse> getCustomerHandler(ServerRequest request) {

        Flux<Customer> customers = customerDao.getCustomerHandler();
        return ServerResponse.ok().body(customers, Customer.class);
    }

    public Mono<ServerResponse> getCustomerStreamHandler(ServerRequest request) {

        Flux<Customer> customers = customerDao.getCustomerHandler();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customers, Customer.class);
    }

    public Mono<ServerResponse> findCustomerStreamHandler(ServerRequest request) {

        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<Customer> customers = customerDao.getCustomerHandler().filter(customer -> id == customer.getId()).next();
        return ServerResponse.ok()
                .body(customers, Customer.class);
    }

    public Mono<ServerResponse> saveCustomerStreamHandler(ServerRequest request) {

        final Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        final Mono<String> savedMono =
                customerMono.map(customer -> customer.getId() + " " + customer.getName());
        return ServerResponse.ok()
                .body(savedMono, String.class);
    }
}
