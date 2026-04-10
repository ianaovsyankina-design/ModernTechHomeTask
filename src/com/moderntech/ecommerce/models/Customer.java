package com.moderntech.ecommerce.models;

import java.util.UUID;

public class Customer {
    private final String id;
    private final String name;
    private final String email;

    public Customer(String name, String email) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Покупатель: %s (ID: %s, Email: %s)", name, id, email);
    }
}