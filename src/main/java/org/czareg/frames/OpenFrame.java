package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

public final class OpenFrame implements Frame {

    private final int firstRoll;
    private final int secondRoll;

    public OpenFrame(int firstRoll, int secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    @Override
    public int baseScore() {
        return firstRoll + secondRoll;
    }

    @Override
    public int bonusScore(BonusRollCalculator unused) {
        return 0;
    }

    @Override
    public int firstRoll() {
        return firstRoll;
    }

    public int secondRoll() {
        return secondRoll;
    }
}
