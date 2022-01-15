package com.perfect.handler;

import com.perfect.input.ProductInput;
import com.perfect.service.ProductService;
import com.perfect.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("all")
    public Flux<ProductInput> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductInput> getProductById(@PathVariable final String id){
        return productService.getProductById(id);
    }

    @GetMapping("range")
    public Flux<ProductInput> findProductInRange(@RequestParam("min") final double min, @RequestParam("max") final double max){
        return productService.findProductInRange(min, max);
    }

    @PostMapping("save")
    public Mono<ProductInput> saveProduct(@RequestBody final Mono<ProductInput> input){
        return productService.saveProduct(input);
    }

    @PutMapping("update/{id}")
    public Mono<ProductInput> updateProduct(@PathVariable final String id, final Mono<ProductInput> input){
        return productService.updateProduct(id, input);

    }

    @DeleteMapping("delete/{id}")
    public Mono<Void> deleteProduct(final String id){
        return productService.deleteProduct(id);

    }
}
