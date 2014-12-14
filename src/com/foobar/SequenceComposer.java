package com.foobar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SequenceComposer {
    private final List<Transformation> transformations;
    private final List<Composition> compositions;

    public SequenceComposer() {
        transformations = new LinkedList<>();
        compositions = new LinkedList<>();
    }

    public void registerTransformation(Transformation transformation) {
        transformations.add(transformation);
    }

    public void registerComposition(Composition composition) {
        compositions.add(composition);
    }

    public List<Sequence> compose(Sequence sequence) {
        LinkedList<Sequence> results = new LinkedList<>();

        for (int i = 0; i < sequence.getNumbers().size(); ++i) {
            Number number = sequence.getNumbers().get(i);
            ArrayList<Number> numbers = new ArrayList<>(sequence.getNumbers());

            for (Transformation transformation : transformations) {
                Number transformedNumber = transformation.transform(number);
                if (transformedNumber != null) {
                    numbers.set(i, transformedNumber);
                    results.add(new Sequence(numbers));
                }
            }
        }

        for (int i = 0; i < sequence.getNumbers().size() - 1; ++i) {
            Number lhs = sequence.getNumbers().get(i);
            Number rhs = sequence.getNumbers().get(i + 1);

            ArrayList<Number> numbers = new ArrayList<>(sequence.getNumbers());
            numbers.remove(i + 1);

            for (Composition composition : compositions) {
                Number composedNumber = composition.compose(lhs, rhs);
                if (composedNumber != null) {
                    numbers.set(i, composedNumber);
                    results.add(new Sequence(numbers));
                }
            }
        }

        return results;
    }
}
