package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

import java.util.List;

public final class TenthFrame implements Frame {

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
                .mapToInt(Integer::intValue)
                .sum();
    }

    public List<Integer> rolls() {
        return rolls;
    }
}
