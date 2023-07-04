package ru.otus;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Multiset;

public class HelloOtus<T> implements Comparable<HelloOtus<T>> {

    private final String str;
    private final int ingr;
    private Multiset<T> counts;

    public HelloOtus(String str, int ingr) {
        this.str = str;
        this.ingr = ingr;
    }

    public Multiset<T> getCounts() {
        return counts;
    }

    public void setCounts(Multiset<T> counts) {
        this.counts = counts;
    }

    @Override
    public int compareTo(HelloOtus<T> other) {
        return ComparisonChain.start()
                .compare(str, other.str)
                .compare(ingr, other.ingr)
                .compare(counts, other.counts, (mlts1, mlts2) -> mlts1.equals(mlts2) ? 1 : 0)
                .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloOtus<?> helloOtus = (HelloOtus<?>) o;
        return ingr == helloOtus.ingr && Objects.equal(str, helloOtus.str) && Objects.equal(counts, helloOtus.counts);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(str, ingr, counts);
    }

    @Override
    public String toString() {
        return "HelloOtus{" +
                "str='" + str + '\'' +
                ", ingr=" + ingr +
                ", counts=" + counts +
                '}';
    }
}
