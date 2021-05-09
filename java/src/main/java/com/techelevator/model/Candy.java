package com.techelevator.model;

import java.math.BigDecimal;

public class Candy extends Vendable{

    public Candy(String slot, String name, BigDecimal price, String category) {
        super(name, price, category, slot);
    }

    @Override
    public String consume() {
        return "Munch Munch, Yum!";
    }
}
