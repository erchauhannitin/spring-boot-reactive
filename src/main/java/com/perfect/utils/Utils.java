package com.perfect.utils;

import com.perfect.input.ProductInput;
import com.perfect.model.Product;
import org.springframework.beans.BeanUtils;

public class Utils {

    public static Product toEntity(final ProductInput input){

        Product product = new Product();
        BeanUtils.copyProperties(input, product);
        return product;
    }

    public static ProductInput toInput(final Product product){

        ProductInput input = new ProductInput();
        BeanUtils.copyProperties(product, input);
        return input;
    }

}
