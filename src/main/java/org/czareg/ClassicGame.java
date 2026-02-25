package org.czareg;

class ClassicGame implements Game {

    int score;

    @Override
    public void roll(int pins) {
        score += pins;
    }

    @Override
    public int score() {
        return score;
    }
}
