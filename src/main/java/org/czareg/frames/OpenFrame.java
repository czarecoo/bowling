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
    public int score(BonusRollCalculator unused) {
        return firstRoll + secondRoll;
    }

    public int firstRoll() {
        return firstRoll;
    }

    public int secondRoll() {
        return secondRoll;
    }
}
