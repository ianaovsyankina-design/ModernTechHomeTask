package com.moderntech.ecommerce.enums;

public enum OrderStatus {
    PENDING("Ожидает"),
    CONFIRMED("Подтверждён"),
    PROCESSING("Обрабатывается"),
    SHIPPED("Отправлен"),
    DELIVERED("Доставлен"),
    CANCELLED("Отменён");

    private final String rusName;

    OrderStatus(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }

    public OrderStatus next() {
        return switch (this) {
            case PENDING -> CONFIRMED;
            case CONFIRMED -> PROCESSING;
            case PROCESSING -> SHIPPED;
            case SHIPPED -> DELIVERED;
            default -> this;
        };
    }
}