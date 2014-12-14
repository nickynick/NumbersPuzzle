package com.foobar;

public class MinusTransformation implements Transformation {
    @Override
    public Number transform(Number number) {
        String representationFormat = number.isPure() ? "-%s" : "-(%s)";
        String representation = String.format(representationFormat, number.getRepresentation());
        return new Number(-number.getValue(), representation);
    }
}
