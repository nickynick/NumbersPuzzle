package com.foobar;

public class PowerComposition implements Composition {
    @Override
    public Number compose(Number lhs, Number rhs) {
        Long value = getValue(lhs, rhs);
        if (value != null) {
            return new Number(value, getRepresentation(lhs, rhs));
        } else {
            return null;
        }
    }

    private Long getValue(Number lhs, Number rhs) {
        long base = lhs.getValue();
        long power = rhs.getValue();
        if (base == 0 || power <= 0) {
            return null;
        }

        long value = 1;
        for (long i = 0; i < power; ++i) {
            value *= base;
            if (Math.abs(value) > Config.getMaxNumberValue()) {
                return null;
            }
        }
        return value;
    }

    private String getRepresentation(Number lhs, Number rhs) {
        String lhsRepresentation = lhs.isPure() ? lhs.getRepresentation() : String.format("(%s)", lhs.getRepresentation());
        String rhsRepresentation = rhs.isPure() ? rhs.getRepresentation() : String.format("(%s)", rhs.getRepresentation());
        return String.format("%s^%s", lhsRepresentation, rhsRepresentation);
    }
}
