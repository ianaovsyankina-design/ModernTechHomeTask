package com.moderntech.ecommerce.payment;

public enum PaymentStatus {
    PENDING("В ожидании"),
    SUCCESS("Успешно"),
    FAILED("Ошибка"),
    REFUNDED("Возврат"),
    PROCESSING("В обработке");

    private final String rusName;

    PaymentStatus(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}