package org.czareg;

class ClassicGame implements Game {

    static final int MAX_SCORE_PER_FRAME = 10;
    static final int ROLLS_PER_GAME = 21;

    int currentFrameIndex = 0;
    int[] frames = new int[ROLLS_PER_GAME];

    @Override
    public void roll(int pins) {
        if (pins == MAX_SCORE_PER_FRAME) {
            frames[currentFrameIndex] = MAX_SCORE_PER_FRAME;
            currentFrameIndex += 2;
        } else {
            frames[currentFrameIndex] = pins;
            currentFrameIndex++;
        }
    }

    @Override
    public int score() {
        int result = 0;
        int frameIndex = 0;
        while (frameIndex < currentFrameIndex) {
            if (isStrike(frameIndex)) {
                result = (MAX_SCORE_PER_FRAME + nextTwoRollScores(frameIndex));
                frameIndex += 2;
            } else if (isSpare(frameIndex)) {
                result = (MAX_SCORE_PER_FRAME + nextRollScore(frameIndex));
                frameIndex += 2;
            } else {
                result += frames[frameIndex];
                frameIndex++;
            }
        }
        return result;
    }

    private boolean isStrike(int frameIndex) {
        return frames[frameIndex] == MAX_SCORE_PER_FRAME;
    }

    private int nextTwoRollScores(int frameIndex) {
        return nextRollScore(frameIndex) + (isStrike(frameIndex + 3)? MAX_SCORE_PER_FRAME: frames[frameIndex+3]);
    }

    private boolean isSpare(int frameIndex) {
        return (frames[frameIndex] + frames[frameIndex + 1]) == MAX_SCORE_PER_FRAME;
    }

    private int nextRollScore(int frameIndex) {
        return frames[frameIndex + 2];
    }
}
