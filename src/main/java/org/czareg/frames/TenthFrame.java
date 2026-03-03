package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

import java.util.List;
import java.util.OptionalInt;

public final class TenthFrame implements Frame {

    private static final int MAX_ROLLS = 3;

    private final List<Integer> rolls;

    public TenthFrame(List<Integer> rolls) {
        if (rolls == null || rolls.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.rolls = rolls;
    }

    @Override
    public int score(BonusRollCalculator unused) {
        return rolls.stream()
                .limit(MAX_ROLLS)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int firstRoll() {
        return rolls.getFirst();
    }

    public OptionalInt secondRoll() {
        return rolls.size() > 1 ? OptionalInt.of(rolls.get(1)) : OptionalInt.empty();
    }
}
