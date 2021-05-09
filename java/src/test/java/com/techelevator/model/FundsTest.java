package com.techelevator.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FundsTest {

    Funds funds;

    @Before
    public void setUp() throws Exception {
        this.funds = new Funds();
    }

    @Test
    public void addingFunds() {

        this.funds.addBalance(new BigDecimal(2.00));
        BigDecimal actualBalance = this.funds.getBalance();
        BigDecimal expectedBalance = new BigDecimal(2.00);

        assertEquals(expectedBalance, actualBalance);

    }

    @Test
    public void deductBalance() {

        this.funds.addBalance(new BigDecimal(2.00));
        this.funds.subtractFromBalance(new BigDecimal(0.75));
        BigDecimal actualBalance = this.funds.getBalance();
        BigDecimal expectedBalance = new BigDecimal(1.25);

        assertEquals(expectedBalance, actualBalance);

    }

    @Test
    public void calculateChange() {

        this.funds.addBalance(new BigDecimal(10.00));
        this.funds.subtractFromBalance(new BigDecimal(6.60));

        int [] expectedChangeArr = {13, 1, 1};
        int [] actualChangeArr = this.funds.calculateChange();


        System.out.println(Arrays.toString(actualChangeArr));

        int comparison = Arrays.mismatch(expectedChangeArr, actualChangeArr);
        assertEquals(-1, comparison);

    }

    @Test
    public void balanceReset() {
        this.funds.addBalance(new BigDecimal(10.00));
        this.funds.resetFunds();

        BigDecimal actualBalance = this.funds.getBalance();
        BigDecimal expectedBalance = BigDecimal.ZERO;

        assertEquals(expectedBalance, actualBalance);

    }
}