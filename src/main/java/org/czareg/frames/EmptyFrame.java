package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

public final class EmptyFrame implements Frame {

    @Override
    public int score(BonusRollCalculator unused) {
        return 0;
    }
}
