package org.czareg;

class ClassicGame implements Game {

    static final int MAX_SCORE_PER_FRAME = 10;
    static final int ROLLS_PER_GAME = 21;

    int currentFrameIndex = 0;
    int[] frames = new int[ROLLS_PER_GAME];

    @Override
    public void roll(int pins) {
        frames[currentFrameIndex] = pins;
        if (pins == MAX_SCORE_PER_FRAME && !isLastFrame()) {
            currentFrameIndex += 2;
        } else {
            currentFrameIndex++;
        }
    }

    private boolean isLastFrame() {
        return currentFrameIndex == 18 || currentFrameIndex == 19 || currentFrameIndex == 20;
    }

    @Override
    public int score() {
        int result = 0;
        for (int frame = 0; frame < 10; frame++) {
            int frameIndex = frame * 2;
            if (isStrike(frameIndex)) {
                result = result + (MAX_SCORE_PER_FRAME + nextTwoRollScores(frameIndex));
            } else if (isSpare(frameIndex)) {
                result = result + (MAX_SCORE_PER_FRAME + nextRollScore(frameIndex));
            } else {
                result = result + frames[frameIndex] + getRollOrMax(frameIndex + 1);
            }
        }
        return result;
    }

    private boolean isStrike(int frameIndex) {
        return getRollOrMax(frameIndex) == MAX_SCORE_PER_FRAME;
    }

    private int nextTwoRollScores(int frameIndex) {
        return nextRollScore(frameIndex) + (isStrike(frameIndex + 4) ? MAX_SCORE_PER_FRAME : getRollOrMax(frameIndex + 3));
    }

    private boolean isSpare(int frameIndex) {
        return (getRollOrMax(frameIndex) + getRollOrMax(frameIndex + 1)) == MAX_SCORE_PER_FRAME;
    }

    private int nextRollScore(int frameIndex) {
        return getRollOrMax(frameIndex + 2);
    }

    private int getRollOrMax(int frameIndex) {
        return frameIndex < frames.length ? frames[frameIndex] : MAX_SCORE_PER_FRAME;
    }
}
