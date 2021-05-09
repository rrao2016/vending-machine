package com.techelevator.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    @Test
    public void makesCorrectSound() {
        Vendable testCandy = new Candy("A1", "The Candy", new BigDecimal(1.00), "Candy");
        String sound = testCandy.consume();
        assertEquals("Munch Munch, Yum!", sound);
    }


}