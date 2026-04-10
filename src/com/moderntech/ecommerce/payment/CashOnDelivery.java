package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public final class CashOnDelivery implements PaymentMethod {

    @Override
    public String processPayment(Order order, double amount) {
        if (!validateAmount(amount)) {
            return "Ошибка: неверная сумма платежа";
        }
        return String.format("Наложенный платёж на сумму %.2f руб. будет оплачен при получении заказа %s",
                amount, order.getId());
    }

    @Override
    public String getMethodName() {
        return "Наложенный платёж";
    }
}