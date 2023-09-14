package org.example.core;

import org.example.currency.Currency;

public class Cell {
    private int count;
    private int sum;
    private Currency currency;

    public Cell(int count, Currency currency) {
        this.count = count;
        this.currency = currency;
        this.sum = calculateSum();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        this.sum = calculateSum();
    }

    public Cell addBanknote(int count) {
        this.count += count;
        this.sum = calculateSum();
        return this;
    }

    public int getSum() {
        return sum;
    }

    public Cell minusBanknote(int count) {
        this.count -= count;
        this.sum = calculateSum();
        return this;
    }

    private int calculateSum() {
        return count * currency.getRating();
    }
}
