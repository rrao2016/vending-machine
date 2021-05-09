package com.techelevator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Funds {

    private BigDecimal balance = new BigDecimal(0.0);

    public void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public boolean hasSufficientFunds(BigDecimal amount) {

        int comparison = this.balance.compareTo(amount);
        return comparison >= 0 ? true : false;

    }

    public void subtractFromBalance(BigDecimal amount) {

        this.balance = this.balance.subtract(amount);

    }

    public int [] calculateChange() {

        int [] changeArr = new int[3];
        int numOfQuarters, numOfDimes, numOfNickels = 0;
        int balanceCents = this.balance.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP).intValueExact();

        numOfQuarters = balanceCents/25;
        changeArr[0] = numOfQuarters;
        balanceCents =  balanceCents - (numOfQuarters * 25);

        numOfDimes = balanceCents/10;
        changeArr[1] = numOfDimes;
        balanceCents = balanceCents - (numOfDimes * 10);

        numOfNickels = balanceCents/5;
        changeArr[2] = numOfNickels;

        return changeArr;
    }

    public void resetFunds() {
        this.balance = BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Current Balance: $" + this.balance;
    }


}
