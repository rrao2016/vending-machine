package com.techelevator.model;

import java.math.BigDecimal;

public class Drink extends Vendable{

    public Drink(String slot, String name, BigDecimal price, String category) {
        super(name, price, category, slot);
    }

    @Override
    public String consume() {
        return "Glug Glug, Yum!";
    }
}
