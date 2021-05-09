package com.techelevator.model;

import java.math.BigDecimal;

public class Gum extends Vendable {
    public Gum(String slot, String name, BigDecimal price, String category ) {
        super(name, price, category, slot);
    }

    @Override
    public String consume() {
        return "Chew Chew, Yum!";
    }
}
