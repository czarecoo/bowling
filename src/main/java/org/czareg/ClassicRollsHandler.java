package org.czareg;

import org.czareg.api.RollsHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ClassicRollsHandler implements RollsHandler {

    private final List<Integer> rolls = new ArrayList<>();

    @Override
    public void roll(int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException("Pins count has to be <0,10>");
        }
        rolls.addLast(pins);
    }

    @Override
    public boolean hasRoll(int index) {
        return index < rolls.size();
    }

    @Override
    public int getRollOrThrow(int index) {
        if (doesNotHaveRoll(index)) {
            throw new IllegalStateException("Roll with given index doesn't exist");
        }
        return rolls.get(index);
    }

    @Override
    public List<Integer> rollsFrom(int index) {
        return Collections.unmodifiableList(rolls.subList(index, rolls.size()));
    }
}
