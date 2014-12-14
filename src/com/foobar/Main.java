package com.foobar;

import javafx.util.Pair;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Number> numbers = new ArrayList<>();
        numbers.add(new Number(1));
        numbers.add(new Number(9));
        numbers.add(new Number(8));
        numbers.add(new Number(9));

        SequenceComposer composer = new SequenceComposer();
        composer.registerTransformation(new RootTransformation());
        composer.registerTransformation(new MinusTransformation());
        composer.registerTransformation(new FactorialTransformation());
        composer.registerComposition(new JoinComposition());
        composer.registerComposition(new PlusComposition());
        composer.registerComposition(new MinusComposition());
        composer.registerComposition(new MultiplyComposition());
        composer.registerComposition(new DivideComposition());
//        composer.registerComposition(new PowerComposition());
        composer.registerComposition(new HugePowerComposition());

        Sequence startingSequence = new Sequence(numbers);

        HashSet<Sequence> visitedSequences = new HashSet<>();

        Deque<Pair<Sequence, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(startingSequence, 0));

        ArrayList<Number> results = new ArrayList<>();

        while (queue.size() > 0) {
            Sequence sequence = queue.getFirst().getKey();
            int depth = queue.getFirst().getValue();
            queue.removeFirst();

            List<Sequence> composedSequences = composer.compose(sequence);

            for (Sequence composedSequence : composedSequences) {
                if (visitedSequences.contains(composedSequence)) {
                    continue;
                }

                visitedSequences.add(composedSequence);

                if (composedSequence.getNumbers().size() == 1) {
                    Number number = composedSequence.getNumbers().get(0);
                    if (number.getValue() >= 0) {
                        results.add(number);
                    }
                }

                if (depth <= Config.getMaxDepth()) {
                    queue.addLast(new Pair<>(composedSequence, depth + 1));
                }
            }
        }

        Collections.sort(results, new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                return Long.compare(o1.getValue(), o2.getValue());
            }
        });

        for (Number number : results) {
            System.out.format("%d = %s\n", number.getValue(), number.getRepresentation());
        }
    }
}
