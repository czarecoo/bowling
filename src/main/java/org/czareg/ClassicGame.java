package org.czareg;

import java.util.ArrayList;
import java.util.List;

class ClassicGame implements Game {

    static final int MAX_SCORE_PER_FRAME = 10;
    static final int ROLLS_PER_GAME = 21;

    List<Integer> rolls = new ArrayList<>(ROLLS_PER_GAME);

    @Override
    public void roll(int pins) {
        rolls.addLast(pins);
    }

    @Override
    public int score() {
        int result = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                result = result + (MAX_SCORE_PER_FRAME + nextTwoRollScores(frameIndex));
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                result = result + (MAX_SCORE_PER_FRAME + nextRollScore(frameIndex));
                frameIndex += 2;
            } else {
                result = result + getRollOrMax(frameIndex) + getRollOrMax(frameIndex + 1);
                frameIndex += 2;
            }
        }
        return result;
    }

    private boolean isStrike(int frameIndex) {
        return getRollOrMax(frameIndex) == MAX_SCORE_PER_FRAME;
    }

    private int nextTwoRollScores(int frameIndex) {
        return getRollOrMax(frameIndex + 1) + (isStrike(frameIndex + 2) ? MAX_SCORE_PER_FRAME : getRollOrMax(frameIndex + 2));
    }

    private boolean isSpare(int frameIndex) {
        return (getRollOrMax(frameIndex) + getRollOrMax(frameIndex + 1)) == MAX_SCORE_PER_FRAME;
    }

    private int nextRollScore(int frameIndex) {
        return getRollOrMax(frameIndex + 2);
    }

    private int getRollOrMax(int frameIndex) {
        return frameIndex < rolls.size() ? rolls.get(frameIndex) : MAX_SCORE_PER_FRAME;
    }
}
