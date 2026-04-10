package com.moderntech.ecommerce.models;

public record OrderItem(Product product, int quantity, double priceAtOrder) {

    public OrderItem {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }
        if (priceAtOrder < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }

    public OrderItem(Product product, int quantity) {
        this(product, quantity, product.price());
    }

    public double getTotalPrice() {
        return priceAtOrder * quantity;
    }
}