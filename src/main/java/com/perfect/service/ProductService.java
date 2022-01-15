package com.perfect.service;

import com.perfect.input.ProductInput;
import com.perfect.model.Product;
import com.perfect.repository.ProductRepository;
import com.perfect.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Flux<ProductInput> getProducts(){
        return productRepository.findAll().map(Utils::toInput);
    }

    public Mono<ProductInput> getProductById(final String id){
        return productRepository.findById(id).map(Utils::toInput);
    }

    public Flux<ProductInput> findProductInRange(final double min, final double max){
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }
    
    public Mono<ProductInput> saveProduct(final Mono<ProductInput> input){

        return input.map(Utils::toEntity)
                .flatMap(productRepository::insert)
                .map(Utils::toInput);
    }

    public Mono<ProductInput> updateProduct(final String id, final Mono<ProductInput> input){

        return productRepository.findById(id)
                .flatMap(product -> input.map(Utils::toEntity))
                .doOnNext(e -> e.setId(id))
                .flatMap(productRepository::save)
                .map(Utils::toInput);

    }

    public Mono<Void> deleteProduct(final String id){

        return productRepository.deleteById(id);

    }

}
