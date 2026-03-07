package org.czareg.frames;

import org.czareg.api.BonusCalculator;
import org.czareg.api.Game;

public final class SpareFrame implements Frame {

    private final int firstRoll;
    private final int secondRoll;

    public SpareFrame(int firstRoll, int secondRoll) {
        if (firstRoll + secondRoll != Game.MAX_SCORE_PER_FRAME) {
            throw new IllegalStateException("Sum of rolls has to be exactly " + Game.MAX_SCORE_PER_FRAME + " for spare frame.");
        }
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    @Override
    public int baseScore() {
        return firstRoll + secondRoll;
    }

    @Override
    public int bonusScore(BonusCalculator bonusCalculator) {
        return bonusCalculator.next(this);
    }

    @Override
    public int firstRoll() {
        return firstRoll;
    }

    public int secondRoll() {
        return secondRoll;
    }
}
