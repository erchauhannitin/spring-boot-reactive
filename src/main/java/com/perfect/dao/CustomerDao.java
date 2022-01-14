package com.perfect.dao;

import com.perfect.model.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void processingTime(int i) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Customer> getCustomersSync(){

        return IntStream.rangeClosed(1,50)
                .peek(CustomerDao::processingTime)
                .mapToObj(i -> new Customer(i, "Customer " + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersAsync(){

        return Flux.range(1,50)
                .delayElements(Duration.ofMillis(100))
                .map(i -> new Customer(i, "Customer " + i));
    }
}
