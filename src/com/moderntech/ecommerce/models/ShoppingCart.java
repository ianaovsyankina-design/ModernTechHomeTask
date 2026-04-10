package com.moderntech.ecommerce.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private final List<CartItem> items;
    private static final double VAT_RATE = 0.20; // 20% НДС

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        Optional<CartItem> existing = findItemByProduct(product);

        if (existing.isPresent()) {
            CartItem item = existing.get();
            int index = items.indexOf(item);
            items.set(index, item.increaseQuantity(quantity));
        } else {
            items.add(new CartItem(product, quantity));
        }
    }

    public boolean removeItem(Product product) {
        return items.removeIf(item -> item.product().equals(product));
    }

    public boolean removeItem(Product product, int quantity) {
        Optional<CartItem> existing = findItemByProduct(product);

        if (existing.isPresent()) {
            CartItem item = existing.get();
            int index = items.indexOf(item);
            CartItem newItem = item.decreaseQuantity(quantity);

            if (newItem == null) {
                items.remove(index);
            } else {
                items.set(index, newItem);
            }
            return true;
        }
        return false;
    }

    public void clear() {
        items.clear();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public double getVat() {
        return getSubtotal() * VAT_RATE;
    }

    public double getTotal() {
        return getSubtotal() + getVat();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    private Optional<CartItem> findItemByProduct(Product product) {
        return items.stream()
                .filter(item -> item.product().equals(product))
                .findFirst();
    }

    public void display() {
        if (items.isEmpty()) {
            System.out.println("  Корзина пуста");
            return;
        }

        System.out.println("  Корзина:");
        for (CartItem item : items) {
            System.out.printf("    - %s x%d = %.2f руб.%n",
                    item.product().name(),
                    item.quantity(),
                    item.getTotalPrice());
        }
        System.out.printf("  Товаров на сумму: %.2f руб.%n", getSubtotal());
        System.out.printf("  НДС (20%%): %.2f руб.%n", getVat());
        System.out.printf("  Итого: %.2f руб.%n", getTotal());
    }
}