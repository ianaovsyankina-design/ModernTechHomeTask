package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public final class DigitalWalletPayment implements PaymentMethod {
    private final String walletId;
    private final String provider;

    public DigitalWalletPayment(String walletId, String provider) {
        this.walletId = walletId;
        this.provider = provider;
    }

    @Override
    public String processPayment(Order order, double amount) {
        if (!validateAmount(amount)) {
            return "Ошибка: неверная сумма платежа";
        }
        return String.format("Оплата через %s (кошелёк: %s) на сумму %.2f руб. прошла успешно",
                provider, walletId, amount);
    }

    @Override
    public String getMethodName() {
        return "Электронный кошелёк (" + provider + ")";
    }
}