package org.czareg;

import org.czareg.api.BonusRollCalculator;
import org.czareg.api.Game;
import org.czareg.api.RollsHandler;
import org.czareg.api.RollsToFramesMapper;
import org.czareg.frames.Frame;
import org.czareg.frames.TenthFrame;

import java.util.Collections;
import java.util.List;

class ClassicGame implements Game {

    private final RollsHandler rollsHandler;
    private final RollsToFramesMapper rollsToFramesMapper;

    private int score;
    private List<Frame> frames;

    ClassicGame(RollsHandler rollsHandler, RollsToFramesMapper rollsToFramesMapper) {
        this.rollsHandler = rollsHandler;
        this.rollsToFramesMapper = rollsToFramesMapper;
        this.score = 0;
        this.frames = Collections.emptyList();
    }

    public static Game create() {
        return new ClassicGame(new ClassicRollsHandler(), new ClassicRollsToFramesMapper());
    }

    @Override
    public void roll(int pins) {
        if (isComplete()) {
            throw new IllegalStateException("Game is over");
        }
        rollsHandler.roll(pins);
        frames = rollsToFramesMapper.create(rollsHandler);
        BonusRollCalculator bonusRollCalculator = new ClassicBonusCalculator(frames);
        score = frames.stream()
                .mapToInt(frame -> frame.score(bonusRollCalculator))
                .sum();
    }

    @Override
    public int score() {
        return score;
    }

    private boolean isComplete() {
        if (frames.size() < 10) {
            return false;
        }
        Frame tenthFrame = frames.get(9);
        return tenthFrame instanceof TenthFrame tf && tf.isComplete();
    }
}
