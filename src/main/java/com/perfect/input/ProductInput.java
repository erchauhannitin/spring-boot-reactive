package com.perfect.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInput {

    private String id;
    private String name;
    private double price;
    private int quantity;
}
