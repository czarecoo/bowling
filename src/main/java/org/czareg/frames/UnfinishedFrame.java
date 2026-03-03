package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

public final class UnfinishedFrame implements Frame {

    private final int firstRoll;

    public UnfinishedFrame(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    @Override
    public int baseScore() {
        return firstRoll;
    }

    @Override
    public int bonusScore(BonusRollCalculator unused) {
        return 0;
    }

    public int firstRoll() {
        return firstRoll;
    }
}
