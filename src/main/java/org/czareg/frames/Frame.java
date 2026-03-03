package org.czareg.frames;

import org.czareg.api.BonusRollCalculator;

public sealed interface Frame permits OpenFrame, SpareFrame, StrikeFrame, TenthFrame, UnfinishedFrame {

    int baseScore();

    int bonusScore(BonusRollCalculator bonusRollCalculator);

    int firstRoll();

    default int score(BonusRollCalculator bonusRollCalculator) {
        return baseScore() + bonusScore(bonusRollCalculator);
    }

    int MAX_FRAMES = 10;
    int MAX_SCORE_PER_FRAME = 10;
}
