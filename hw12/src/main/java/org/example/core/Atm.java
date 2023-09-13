package org.example.core;

import org.example.currency.Currency;

import java.util.HashMap;
import java.util.Map;

import static org.example.currency.Currency.*;

public class Atm {
    private static Map<Currency, Cell> cellHashMap;
    private int currentRemainder = 0;

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int COUNT_ONE_HUNDRED = 100;

    public void init(HashMap<Currency, Cell> initCellHashMap) {
        cellHashMap = initCellHashMap;
    }

    public Atm defaultInit() {
        cellHashMap = new HashMap<>();

        cellHashMap.put(Currency.TEN, new Cell(COUNT_ONE_HUNDRED, Currency.TEN));
        cellHashMap.put(Currency.FIFTY, new Cell(COUNT_ONE_HUNDRED, Currency.FIFTY));
        cellHashMap.put(ONE_HUNDRED, new Cell(COUNT_ONE_HUNDRED, ONE_HUNDRED));
        cellHashMap.put(FIVE_HUNDRED, new Cell(COUNT_ONE_HUNDRED, FIVE_HUNDRED));
        cellHashMap.put(ONE_THOUSAND, new Cell(COUNT_ONE_HUNDRED, ONE_THOUSAND));

        this.currentRemainder = 166000;

        return this;
    }

    public Atm enterMoney(Currency currency, int count) {
        cellHashMap.put(currency, cellHashMap.get(currency).addBanknote(count));
        this.currentRemainder += currency.getRating() * count;
        return this;
    }

    public void getBanknote(int sum) {
        int remainder = sum;

        if (this.currentRemainder >= remainder) {
            if (remainder / ONE_THOUSAND.getRating() >= ONE) {
                remainder = minusBanknote(ONE_THOUSAND, remainder);
            }

            if (this.currentRemainder >= remainder && remainder / FIVE_HUNDRED.getRating() >= ONE && remainder > ZERO) {
                remainder = minusBanknote(FIVE_HUNDRED, remainder);
            }

            if (this.currentRemainder >= remainder && remainder / ONE_HUNDRED.getRating() >= ONE && remainder > ZERO) {
                remainder = minusBanknote(ONE_HUNDRED, remainder);
            }

            if (this.currentRemainder >= remainder && remainder / FIFTY.getRating() >= ONE && remainder > ZERO) {
                remainder = minusBanknote(FIFTY, remainder);
            }

            if (this.currentRemainder >= remainder && remainder / TEN.getRating() >= ONE && remainder > ZERO) {
                minusBanknote(TEN, remainder);
            }
        }
    }

    private int minusBanknote(Currency currency, int remainder) {
        int countBanknote = remainder / currency.getRating();

        remainder = remainder - countBanknote * currency.getRating();
        this.currentRemainder = this.currentRemainder - countBanknote * currency.getRating();

        cellHashMap.put(currency, cellHashMap.get(currency).minusBanknote(countBanknote));

        return remainder;
    }

    public int getBalance() {
        return this.currentRemainder;
    }
}
