package org.czareg.frames;

import org.czareg.api.BonusCalculator;
import org.czareg.api.Game;

public final class StrikeFrame implements Frame {

    private final int roll;

    public StrikeFrame(int roll) {
        if (roll != Game.MAX_SCORE_PER_FRAME) {
            throw new IllegalStateException("Roll has to be exactly " + Game.MAX_SCORE_PER_FRAME + " for strike frame.");
        }
        this.roll = roll;
    }

    @Override
    public int baseScore() {
        return roll;
    }

    @Override
    public int bonusScore(BonusCalculator bonusCalculator) {
        return bonusCalculator.next(this) + bonusCalculator.nextNext(this);
    }

    @Override
    public int firstRoll() {
        return roll;
    }
}
