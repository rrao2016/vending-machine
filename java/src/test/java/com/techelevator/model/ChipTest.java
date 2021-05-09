package com.techelevator.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChipTest {

    @Test
    public void makesCorrectSound() {
        Vendable testChip = new Chip("A1", "The Chip", new BigDecimal(1.00), "Chip");
        String sound = testChip.consume();
        assertEquals("Crunch Crunch, Yum!", sound);
    }

}