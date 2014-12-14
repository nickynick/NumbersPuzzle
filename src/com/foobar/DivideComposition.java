package com.foobar;

public class DivideComposition implements Composition {
    @Override
    public Number compose(Number lhs, Number rhs) {
        if (rhs.getValue() == 0 || lhs.getValue() % rhs.getValue() != 0) {
            return null;
        }
        long value = lhs.getValue() / rhs.getValue();
        if (Math.abs(value) > Config.getMaxNumberValue()) {
            return null;
        }
        String lhsRepresentation = lhs.isPure() ? lhs.getRepresentation() : String.format("(%s)", lhs.getRepresentation());
        String rhsRepresentation = rhs.isPure() ? rhs.getRepresentation() : String.format("(%s)", rhs.getRepresentation());
        return new Number(value, String.format("%s / %s", lhsRepresentation, rhsRepresentation));
    }
}
