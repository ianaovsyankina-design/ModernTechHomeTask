package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public final class WildberriesPayment implements PaymentMethod {
    private final PaymentMethod actualPaymentMethod;

    public WildberriesPayment(PaymentMethod paymentMethod) {
        this.actualPaymentMethod = paymentMethod;
    }

    @Override
    public String processPayment(Order order, double amount) {
        System.out.println("  [WILDBERRIES] Обработка платежа через маркетплейс...");
        return "[WB] " + actualPaymentMethod.processPayment(order, amount);
    }

    @Override
    public String getMethodName() {
        return "Wildberries (" + actualPaymentMethod.getMethodName() + ")";
    }
}