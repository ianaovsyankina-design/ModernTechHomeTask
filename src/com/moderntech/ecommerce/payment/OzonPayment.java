package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public final class OzonPayment implements PaymentMethod {
    private final PaymentMethod actualPaymentMethod;

    public OzonPayment(PaymentMethod paymentMethod) {
        this.actualPaymentMethod = paymentMethod;
    }

    @Override
    public String processPayment(Order order, double amount) {
        System.out.println("  [OZON] Обработка платежа через маркетплейс...");
        return "[OZON] " + actualPaymentMethod.processPayment(order, amount);
    }

    @Override
    public String getMethodName() {
        return "Ozon (" + actualPaymentMethod.getMethodName() + ")";
    }
}