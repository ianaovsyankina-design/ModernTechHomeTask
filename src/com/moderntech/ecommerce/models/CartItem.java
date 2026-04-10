package com.moderntech.ecommerce.models;

public record CartItem(Product product, int quantity) {

    public CartItem {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }
    }

    public double getTotalPrice() {
        return product.price() * quantity;
    }

    public CartItem increaseQuantity(int amount) {
        return new CartItem(product, quantity + amount);
    }

    public CartItem decreaseQuantity(int amount) {
        int newQuantity = quantity - amount;
        if (newQuantity <= 0) {
            return null;
        }
        return new CartItem(product, newQuantity);
    }
}