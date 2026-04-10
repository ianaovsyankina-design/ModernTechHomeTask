package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.ProductCategory;

public record Product(String id, String name, ProductCategory category, double price, int stock) {

    public Product {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID товара не может быть пустым");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Остаток не может быть отрицательным");
        }
    }

    public boolean isInStock() {
        return stock > 0;
    }

    public Product reduceStock(int quantity) {
        if (quantity > stock) {
            throw new IllegalArgumentException("Недостаточно товара на складе");
        }
        return new Product(id, name, category, price, stock - quantity);
    }
}