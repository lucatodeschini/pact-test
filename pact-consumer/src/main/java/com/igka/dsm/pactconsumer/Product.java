package com.igka.dsm.pactconsumer;

import java.util.Objects;

public class Product {
    public String id;
    public String name;
    public String price;
    public String currency;

    public Product() {
    }

    public Product(String id,  String name, String price, String currency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(currency, product.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, currency);
    }
}
