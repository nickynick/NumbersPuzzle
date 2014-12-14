package com.foobar;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    private final List<Number> numbers;

    public Sequence(List<Number> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sequence that = (Sequence) o;

        if (!numbers.equals(that.numbers)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return numbers.hashCode();
    }
}
