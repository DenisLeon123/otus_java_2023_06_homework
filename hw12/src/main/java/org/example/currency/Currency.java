package org.example.currency;

public enum Currency {
    TEN(10),
    FIFTY(50),
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000);

    private final int rating;

    Currency(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
