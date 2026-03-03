package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

import java.util.List;
import java.util.OptionalInt;

public final class TenthFrame implements Frame {

    private static final int MAX_ROLLS = 3;

    private final List<Integer> rolls;

    public TenthFrame(List<Integer> rolls) {
        if (rolls == null || rolls.isEmpty() || rolls.size() > MAX_ROLLS) {
            throw new IllegalArgumentException("Invalid rolls: " + rolls);
        }
        this.rolls = rolls;
    }

    @Override
    public int baseScore() {
        return rolls.stream()
                .limit(MAX_ROLLS)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public int bonusScore(BonusRollCalculator unused) {
        return 0;
    }

    public int firstRoll() {
        return rolls.getFirst();
    }

    public OptionalInt secondRoll() {
        return rolls.size() > 1 ? OptionalInt.of(rolls.get(1)) : OptionalInt.empty();
    }

    public boolean isComplete() {
        if (rolls.size() < 2) {
            return false;
        }

        int first = firstRoll();
        int second = rolls.get(1);

        if (first == 10) {
            return rolls.size() == 3;
        }

        if (first + second == 10) {
            return rolls.size() == 3;
        }

        return rolls.size() == 2;
    }
}
