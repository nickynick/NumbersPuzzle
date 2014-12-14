package com.foobar;

public class RootTransformation implements Transformation {
    @Override
    public Number transform(Number number) {
        long squareRoot = (long) Math.sqrt(number.getValue());
        if (squareRoot * squareRoot == number.getValue()) {
            String representationFormat = number.isPure() ? "√%s" : "√(%s)";
            String representation = String.format(representationFormat, number.getRepresentation());
            return new Number(squareRoot, representation);
        } else {
            return null;
        }
    }
}
