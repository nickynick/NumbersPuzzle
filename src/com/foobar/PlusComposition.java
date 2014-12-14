package com.foobar;

public class PlusComposition implements Composition {
    @Override
    public Number compose(Number lhs, Number rhs) {
        long value = lhs.getValue() + rhs.getValue();
        if (Math.abs(value) > Config.getMaxNumberValue()) {
            return null;
        }
        return new Number(value, String.format("%s + %s", lhs.getRepresentation(), rhs.getRepresentation()));
    }
}
