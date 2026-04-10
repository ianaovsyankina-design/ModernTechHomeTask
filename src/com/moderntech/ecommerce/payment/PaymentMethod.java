package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public sealed interface PaymentMethod permits CreditCardPayment, DigitalWalletPayment, CashOnDelivery, OzonPayment, WildberriesPayment {

    String processPayment(Order order, double amount);

    String getMethodName();

    default boolean validateAmount(double amount) {
        return amount > 0;
    }
}