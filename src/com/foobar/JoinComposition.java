package com.foobar;

public class JoinComposition implements Composition {
    @Override
    public Number compose(Number lhs, Number rhs) {
        if (lhs.isPure() && rhs.isPure()) {
            int rhsNumberOfDigits = (int)(Math.log10(rhs.getValue()) + 1);
            return new Number(lhs.getValue() * (long)Math.pow(10, rhsNumberOfDigits) + rhs.getValue());
        } else {
            return null;
        }
    }
}
