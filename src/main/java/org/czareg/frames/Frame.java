package org.czareg.frames;

import org.czareg.api.BonusCalculator;

public sealed interface Frame permits OpenFrame, SpareFrame, StrikeFrame, TenthFrame, UnfinishedFrame {

    int baseScore();

    int bonusScore(BonusCalculator bonusCalculator);

    int firstRoll();

    default int score(BonusCalculator bonusCalculator) {
        return baseScore() + bonusScore(bonusCalculator);
    }
}
