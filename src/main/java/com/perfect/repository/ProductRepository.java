package com.perfect.repository;

import com.perfect.input.ProductInput;
import com.perfect.model.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<ProductInput> findByPriceBetween(Range<Double> closed);
}
