package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public class Payment {
    private final String id;
    private final Order order;
    private final double amount;
    private PaymentStatus status;
    private final PaymentMethod method;

    public Payment(Order order, double amount, PaymentMethod method) {
        this.id = java.util.UUID.randomUUID().toString().substring(0, 8);
        this.order = order;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
        this.method = method;
    }

    public String process() {
        System.out.println("\n  ┌─────────────────────────────────────────────────┐");
        System.out.printf("  │ ПЛАТЁЖ №%-40s│%n", id);
        System.out.println("  ├─────────────────────────────────────────────────┤");

        if (method.validateAmount(amount)) {
            this.status = PaymentStatus.PROCESSING;
            String result = method.processPayment(order, amount);
            this.status = PaymentStatus.SUCCESS;

            System.out.printf("  │ Статус: %-46s│%n", status.getRusName());
            System.out.println("  └─────────────────────────────────────────────────┘");
            return result;
        } else {
            this.status = PaymentStatus.FAILED;
            System.out.printf("  │ Статус: %-46s│%n", status.getRusName());
            System.out.println("  └─────────────────────────────────────────────────┘");
            return "Ошибка: неверная сумма платежа";
        }
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }
}