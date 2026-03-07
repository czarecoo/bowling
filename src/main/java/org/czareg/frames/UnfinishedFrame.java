package org.czareg.frames;

import org.czareg.api.BonusCalculator;
import org.czareg.api.Game;

public final class UnfinishedFrame implements Frame {

    private final int firstRoll;

    public UnfinishedFrame(int firstRoll) {
        if (firstRoll >= 10) {
            throw new IllegalStateException("Roll has to be below " + Game.MAX_SCORE_PER_FRAME + " for unfinished frame.");
        }
        this.firstRoll = firstRoll;
    }

    @Override
    public int baseScore() {
        return firstRoll;
    }

    @Override
    public int bonusScore(BonusCalculator unused) {
        return 0;
    }

    public int firstRoll() {
        return firstRoll;
    }
}
