package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final Customer customer;
    private final List<OrderItem> items;
    private final LocalDateTime orderDate;
    private OrderStatus status;
    private double totalAmount;

    public Order(Customer customer, ShoppingCart cart) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.customer = customer;
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.product();
            items.add(new OrderItem(product, cartItem.quantity()));
        }

        this.totalAmount = cart.getTotal();
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setStatus(OrderStatus status) {
        System.out.printf("  Заказ %s: статус изменён с %s на %s%n",
                id, this.status.getRusName(), status.getRusName());
        this.status = status;
    }

    public void nextStatus() {
        setStatus(status.next());
    }

    public void display() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.printf("║  ЗАКАЗ №%s%40s║%n", id, "");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║  Покупатель: %-45s║%n", customer.getName());
        System.out.printf("║  Дата: %-50s║%n", orderDate.format(formatter));
        System.out.printf("║  Статус: %-48s║%n", status.getRusName());
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  Товары:                                                  ║");

        for (OrderItem item : items) {
            System.out.printf("║    - %s x%d = %.2f руб.%-24s║%n",
                    item.product().name(),
                    item.quantity(),
                    item.getTotalPrice(),
                    "");
        }

        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║  Итого к оплате: %-37.2f руб.║%n", totalAmount);
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}