package org.czareg;

import org.czareg.api.Frames;
import org.czareg.api.Game;
import org.czareg.api.Rolls;
import org.czareg.api.RollsToFramesMapper;

class ClassicGame implements Game {

    private final Rolls rolls;
    private final RollsToFramesMapper rollsToFramesMapper;

    private int score;
    private Frames frames;

    ClassicGame(Rolls rolls, RollsToFramesMapper rollsToFramesMapper) {
        this.rolls = rolls;
        this.rollsToFramesMapper = rollsToFramesMapper;
        this.score = 0;
        this.frames = new ClassicFrames();
    }

    @Override
    public void roll(int pins) {
        if (frames.isComplete()) {
            throw new IllegalStateException("Game is over");
        }
        rolls.roll(pins);
        try {
            frames = rollsToFramesMapper.map(rolls);
            score = frames.score();
        } catch (IllegalStateException e) {
            rolls.removeLastRoll();
            throw e;
        }
    }

    @Override
    public int score() {
        return score;
    }

    public static Game create() {
        return new ClassicGame(new ClassicRolls(), new ClassicRollsToFramesMapper());
    }
}
