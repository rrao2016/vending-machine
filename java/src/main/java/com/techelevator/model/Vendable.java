package com.techelevator.model;

import java.math.BigDecimal;

public abstract class Vendable {

    private String slot;
    private String name;
    private BigDecimal price;
    private String category;
    private int stock;

    public Vendable(String name, BigDecimal price, String category, String slot) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.slot = slot;
        this.stock = 5;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasStock() {
        return this.stock != 0;
    }

    public int getStock() {
        return stock;
    }

    public abstract String consume();

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getSlot() {
        return this.slot;
    }

    public void sell() {

        this.stock--;
    }

    @Override
    public String toString() {
        return this.slot + "\t\t" + this.price + "\t\t" + this.stock + "\t\t" + this.name ;
    }

}
