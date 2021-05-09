package com.techelevator.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendableTest {

    @Test
    public void objectInstantiatesCorrectly() {

        Vendable snack = new Chip("X1", "Volcano" ,new BigDecimal(4.00), "Chip");

        Assert.assertEquals("X1", snack.getSlot());
        Assert.assertEquals(new BigDecimal(4.00), snack.getPrice());
        Assert.assertEquals("Volcano", snack.getName());
        Assert.assertEquals(5, snack.getStock());
        Assert.assertEquals("Chip", snack.getCategory());
    }


    @Test
    public void productStockDecreasesCorrectly() {

        Vendable snack = new Chip("X1", "Volcano" ,new BigDecimal(4.00), "Chip");
        snack.sell();
        Assert.assertEquals(4, snack.getStock());
    }

    @Test
    public void hasStockReturnsFalseWhenInventoryIsZero() {

        Vendable snack = new Chip("X1", "Volcano" ,new BigDecimal(4.00), "Chip");
        for (int i=0; i < 5; i++) {
            snack.sell();
        }

        Assert.assertFalse(snack.hasStock());

    }
}