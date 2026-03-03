package org.czareg;

import org.czareg.api.RollsHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ClassicRollsHandler implements RollsHandler {

    private final List<Integer> rolls = new ArrayList<>();

    @Override
    public void roll(int pins) {
        rolls.addLast(pins);
    }

    @Override
    public List<Integer> getRolls() {
        return Collections.unmodifiableList(rolls);
    }
}
