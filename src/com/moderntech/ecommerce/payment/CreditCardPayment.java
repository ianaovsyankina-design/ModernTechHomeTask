package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public final class CreditCardPayment implements PaymentMethod {
    private final String cardNumber;
    private final String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = "****" + cardNumber.substring(cardNumber.length() - 4);
        this.cardHolder = cardHolder;
    }

    @Override
    public String processPayment(Order order, double amount) {
        if (!validateAmount(amount)) {
            return "Ошибка: неверная сумма платежа";
        }
        return String.format("Оплата картой %s (держатель: %s) на сумму %.2f руб. прошла успешно",
                cardNumber, cardHolder, amount);
    }

    @Override
    public String getMethodName() {
        return "Банковская карта";
    }
}