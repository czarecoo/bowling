package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

public final class UnfinishedFrame implements Frame {

    private final int firstRoll;

    public UnfinishedFrame(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    @Override
    public int score(BonusRollCalculator unused) {
        return firstRoll;
    }

    public int firstRoll() {
        return firstRoll;
    }
}
