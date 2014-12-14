package com.foobar;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class HugePowerComposition implements Composition {
    private final Map<Pair<Number, Number>, ComputationResult> cache;

    public HugePowerComposition() {
        this.cache = new HashMap<>();
    }

    @Override
    public Number compose(Number lhs, Number rhs) {
        ComputationResult result = cache.get(new Pair<>(lhs, rhs));
        if (result == null) {
            result = computeResult(lhs, rhs);
            cache.put(new Pair<>(lhs, rhs), result);
        }

        if (result.getValue() == null) {
            return null;
        }

        String lhsRepresentation = lhs.isPure() ? lhs.getRepresentation() : String.format("(%s)", lhs.getRepresentation());
        String rhsRepresentation = rhs.isPure() ? rhs.getRepresentation() : String.format("(%s)", rhs.getRepresentation());
        String representation = String.format(result.representationFormat, lhsRepresentation, rhsRepresentation);

        return new Number(result.getValue(), representation);
    }

    ComputationResult computeResult(Number lhs, Number rhs) {
        long base = lhs.getValue();
        long power = rhs.getValue();

        if (base == 0 || power <= 0) {
            return new ComputationResult(null, null);
        }

        double resultValue = Math.pow(base, power);
        int numberOfSquareRoots = 0;

        while (Math.abs(resultValue) > Config.getMaxNumberValue() && power % 2 == 0) {
            numberOfSquareRoots++;
            power /= 2;
            resultValue = Math.pow(base, power);
        }

        if (Math.abs(resultValue) > Config.getMaxNumberValue()) {
            return new ComputationResult(null, null);
        }

        String representationFormat = "";
        for (int i = 0; i < numberOfSquareRoots; ++i) {
            representationFormat += "√";
        }
        representationFormat += (numberOfSquareRoots > 0) ? "(%s^%s)" : "%s^%s";

        return new ComputationResult((long)resultValue, representationFormat);
    }

    private static class ComputationResult {
        private final Long value;
        private final String representationFormat;

        public ComputationResult(Long value, String representationFormat) {
            this.value = value;
            this.representationFormat = representationFormat;
        }

        public Long getValue() {
            return value;
        }

        public String getRepresentationFormat() {
            return representationFormat;
        }
    }
}
