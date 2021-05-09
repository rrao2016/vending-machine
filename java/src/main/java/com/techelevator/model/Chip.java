package com.techelevator.model;

import java.math.BigDecimal;

public class Chip extends Vendable{
    public Chip(String slot, String name, BigDecimal price, String category) {
        super(name, price, category, slot);
    }

    @Override
    public String consume() {
        return "Crunch Crunch, Yum!";
    }
}
