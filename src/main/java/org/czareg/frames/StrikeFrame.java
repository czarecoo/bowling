package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

public final class StrikeFrame implements Frame {

    private final int roll;

    public StrikeFrame(int roll) {
        this.roll = roll;
    }

    @Override
    public int baseScore() {
        return roll;
    }

    @Override
    public int bonusScore(BonusRollCalculator bonusRollCalculator) {
        return bonusRollCalculator.next(this) + bonusRollCalculator.nextNext(this);
    }

    @Override
    public int firstRoll() {
        return roll;
    }
}
