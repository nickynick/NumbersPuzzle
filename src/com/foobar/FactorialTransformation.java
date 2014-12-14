package com.foobar;

public class FactorialTransformation implements Transformation {
    @Override
    public Number transform(Number number) {
        if (number.getValue() >= 3) {
            Long value = getValue(number);
            if (value != null) {
                return new Number(value, getRepresentation(number));
            }
        }
        return null;
    }

    private Long getValue(Number number) {
        long value = 2;
        for (long i = 3; i <= number.getValue(); ++i) {
            value *= i;

            if (value > Config.getMaxNumberValue()) {
                return null;
            }
        }
        return value;
    }

    private String getRepresentation(Number number) {
        String representationFormat = number.isPure() ? "%s!" : "(%s)!";
        return String.format(representationFormat, number.getRepresentation());
    }
}
