package com.moderntech.ecommerce.enums;

public enum ProductCategory {
    SMARTPHONE("Смартфон"),
    LAPTOP("Ноутбук"),
    TABLET("Планшет"),
    ACCESSORY("Аксессуар"),
    CAMERA("Фотоаппарат");

    private final String rusName;

    ProductCategory(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}