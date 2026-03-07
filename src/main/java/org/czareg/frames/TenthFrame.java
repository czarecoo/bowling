package org.czareg.frames;

import org.czareg.api.BonusCalculator;
import org.czareg.api.Game;

import java.util.List;
import java.util.OptionalInt;

public final class TenthFrame implements Frame {

    private static final int MAX_ROLLS = 3;
    private static final int MAX_SCORE = Game.MAX_SCORE_PER_FRAME * MAX_ROLLS;

    private final List<Integer> rolls;

    public TenthFrame(List<Integer> rolls) {
        if (rolls == null || rolls.isEmpty() || rolls.size() > MAX_ROLLS || rolls.stream().mapToInt(Integer::intValue).sum() > MAX_SCORE) {
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
    public int bonusScore(BonusCalculator unused) {
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

        if (first == Game.MAX_SCORE_PER_FRAME) {
            return rolls.size() == MAX_ROLLS;
        }

        if (first + second == Game.MAX_SCORE_PER_FRAME) {
            return rolls.size() == MAX_ROLLS;
        }

        return rolls.size() == 2;
    }
}
