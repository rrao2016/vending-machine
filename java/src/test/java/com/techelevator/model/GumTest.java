package com.techelevator.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    @Test
    public void makesCorrectSound() {
        Vendable testGum = new Gum("A1", "The Gum", new BigDecimal(1.00), "Gum");
        String sound = testGum.consume();
        assertEquals("Chew Chew, Yum!", sound);
    }


}