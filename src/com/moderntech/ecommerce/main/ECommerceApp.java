package com.moderntech.ecommerce.main;

import com.moderntech.ecommerce.enums.*;
import com.moderntech.ecommerce.models.*;
import com.moderntech.ecommerce.payment.*;

import java.util.*;

public class ECommerceApp {

    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║           ИНТЕРНЕТ-МАГАЗИН - ДЕМОНСТРАЦИЯ РАБОТЫ              ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

        // 1. КАТАЛОГ
        System.out.println("📦 КАТАЛОГ ТОВАРОВ");
        System.out.println("─────────────────────────────────────────────────────────────────");
        List<Product> catalog = createCatalog();
        displayCatalog(catalog);

        // 2. ПОКУПАТЕЛЬ
        System.out.println("\n👤 ПОКУПАТЕЛЬ");
        System.out.println("─────────────────────────────────────────────────────────────────");
        Customer customer = new Customer("Яна Овсянкина", "yana@example.com");
        System.out.println(customer);

        // 3. КОРЗИНА
        System.out.println("\n🛒 КОРЗИНА");
        System.out.println("─────────────────────────────────────────────────────────────────");
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(catalog.get(0), 1);
        cart.addItem(catalog.get(2), 2);
        cart.addItem(catalog.get(4), 1);
        cart.display();

        // 4. ОФОРМЛЕНИЕ ЗАКАЗА
        System.out.println("\n📋 ОФОРМЛЕНИЕ ЗАКАЗА");
        System.out.println("─────────────────────────────────────────────────────────────────");
        Order order = new Order(customer, cart);
        order.display();

        // 5. ИЗМЕНЕНИЕ СТАТУСА
        System.out.println("\n🔄 ИЗМЕНЕНИЕ СТАТУСА ЗАКАЗА");
        System.out.println("─────────────────────────────────────────────────────────────────");
        order.nextStatus();
        order.nextStatus();

        // 6. ТРИ СЦЕНАРИЯ ОПЛАТЫ
        System.out.println("\n💳 ПЛАТЁЖНЫЕ СЦЕНАРИИ");
        System.out.println("─────────────────────────────────────────────────────────────────");

        // Сценарий 1: Ozon + Банковская карта
        System.out.println("\n▶ СЦЕНАРИЙ 1: Ozon + Банковская карта");
        PaymentMethod cardPayment = new CreditCardPayment("1234567890123456", "YANA OVSYANKINA");
        PaymentMethod ozonCard = new OzonPayment(cardPayment);
        Payment payment1 = new Payment(order, order.getTotalAmount(), ozonCard);
        System.out.println(payment1.process());

        // Сценарий 2: Wildberries + Электронный кошелёк
        System.out.println("\n▶ СЦЕНАРИЙ 2: Wildberries + Электронный кошелёк");
        PaymentMethod walletPayment = new DigitalWalletPayment("yawallet_2025", "YooMoney");
        PaymentMethod wbWallet = new WildberriesPayment(walletPayment);
        Payment payment2 = new Payment(order, order.getTotalAmount(), wbWallet);
        System.out.println(payment2.process());

        // Сценарий 3: Ozon + Наложенный платёж
        System.out.println("\n▶ СЦЕНАРИЙ 3: Ozon + Наложенный платёж");
        PaymentMethod cashPayment = new CashOnDelivery();
        PaymentMethod ozonCash = new OzonPayment(cashPayment);
        Payment payment3 = new Payment(order, order.getTotalAmount(), ozonCash);
        System.out.println(payment3.process());

        // 7. ИТОГОВАЯ СВОДКА
        System.out.println("\n📊 ИТОГОВАЯ СВОДКА");
        System.out.println("═════════════════════════════════════════════════════════════════");
        order.display();
        System.out.println("\n✅ РАБОТА ЗАВЕРШЕНА УСПЕШНО!");
        System.out.println("═════════════════════════════════════════════════════════════════\n");
    }

    private static List<Product> createCatalog() {
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product("P001", "iPhone 15", ProductCategory.SMARTPHONE, 79999, 10));
        catalog.add(new Product("P002", "MacBook Air", ProductCategory.LAPTOP, 119999, 5));
        catalog.add(new Product("P003", "Samsung Tablet", ProductCategory.TABLET, 35999, 8));
        catalog.add(new Product("P004", "Наушники AirPods", ProductCategory.ACCESSORY, 15999, 15));
        catalog.add(new Product("P005", "Sony Camera", ProductCategory.CAMERA, 54999, 3));
        return catalog;
    }

    private static void displayCatalog(List<Product> catalog) {
        System.out.printf("%-10s %-25s %-15s %-10s %-8s%n", "ID", "Название", "Категория", "Цена", "Остаток");
        System.out.println("─────────────────────────────────────────────────────────────────");
        for (Product p : catalog) {
            System.out.printf("%-10s %-25s %-15s %-10.2f %-8d%n",
                    p.id(), p.name(), p.category().getRusName(), p.price(), p.stock());
        }
    }
}