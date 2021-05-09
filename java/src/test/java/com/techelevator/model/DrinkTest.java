package com.techelevator.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {

    @Test
    public void makesCorrectSound() {
        Vendable testDrink = new Drink("A1", "The Drink", new BigDecimal(1.00), "Drink");
        String sound = testDrink.consume();
        assertEquals("Glug Glug, Yum!", sound);
    }

}