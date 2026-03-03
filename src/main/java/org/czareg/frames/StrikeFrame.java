package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

public final class StrikeFrame implements Frame {

    private final int roll;

    public StrikeFrame(int roll) {
        this.roll = roll;
    }

    @Override
    public int score(BonusRollCalculator bonusRollCalculator) {
        return roll + bonusRollCalculator.next(this) + bonusRollCalculator.nextNext(this);
    }

    public int roll() {
        return roll;
    }
}
