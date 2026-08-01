package com.jorgeortiz.productservice.producto;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String message) {
        super(message);
    }
}
