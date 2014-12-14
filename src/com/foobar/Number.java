package com.foobar;

public class Number {
    private final long value;
    private final String representation;
    private final boolean pure;

    public Number(long value, String representation) {
        this.value = value;
        this.representation = representation;
        this.pure = false;
    }

    public Number(long value) {
        this.value = value;
        this.representation = Long.toString(value);
        this.pure = true;
    }

    public long getValue() {
        return value;
    }

    public boolean isPure() {
        return pure;
    }

    public String getRepresentation() {
        return representation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Number number = (Number) o;

        if (value != number.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }
}
