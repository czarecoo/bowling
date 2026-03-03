package org.czareg;

import org.czareg.api.*;
import org.czareg.frames.Frame;

import java.util.List;

class ClassicGame implements Game {

    final RollsHandler rollsHandler = new ClassicRollsHandler();
    final RollsToFramesMapper rollsToFramesMapper = new ClassicRollsToFramesMapper();

    @Override
    public void roll(int pins) {
        rollsHandler.roll(pins);
    }

    @Override
    public int score() {
        List<Frame> frames = rollsToFramesMapper.create(rollsHandler);
        BonusRollCalculator bonusRollCalculator = new ClassicBonusCalculator(frames);
        return frames.stream()
                .mapToInt(frame -> frame.score(bonusRollCalculator))
                .sum();
    }
}
