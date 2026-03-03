package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

public sealed interface Frame permits EmptyFrame, OpenFrame, SpareFrame, StrikeFrame, TenthFrame, UnfinishedFrame {

    int score(BonusRollCalculator bonusRollCalculator);

    Frame EMPTY_FRAME = new EmptyFrame();
}
