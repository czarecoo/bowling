package org.czareg;

import org.czareg.api.RollsHandler;
import org.czareg.api.RollsToFramesMapper;
import org.czareg.frames.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ClassicRollsToFramesMapper implements RollsToFramesMapper {

    private static final int MAX_SCORE_PER_FRAME = 10;

    @Override
    public List<Frame> create(RollsHandler rollsHandler) {
        List<Integer> rolls = rollsHandler.getRolls();
        List<Frame> frames = new ArrayList<>();

        int rollIndex = 0;

        for (int frame = 1; frame <= 10; frame++) {
            if (doesNotHaveRoll(rolls, rollIndex)) {
                break;
            }

            if (frame == 10) {
                frames.add(new TenthFrame(rolls.subList(rollIndex, rolls.size())));
                break;
            }

            int firstRoll = getRollOrThrow(rolls, rollIndex);

            if (firstRoll == MAX_SCORE_PER_FRAME) {
                frames.add(new StrikeFrame(firstRoll));
                rollIndex += 1;
                continue;
            }

            int nextRollIndex = rollIndex + 1;
            if (doesNotHaveRoll(rolls, nextRollIndex)) {
                frames.add(new UnfinishedFrame(firstRoll));
                break;
            }

            int secondRoll = getRollOrThrow(rolls, nextRollIndex);

            if (firstRoll + secondRoll == MAX_SCORE_PER_FRAME) {
                frames.add(new SpareFrame(firstRoll, secondRoll));
            } else {
                frames.add(new OpenFrame(firstRoll, secondRoll));
            }

            rollIndex += 2;
        }

        return Collections.unmodifiableList(frames);
    }

    private boolean doesNotHaveRoll(List<Integer> rolls, int index) {
        return index >= rolls.size();
    }

    private int getRollOrThrow(List<Integer> rolls, int index) {
        if (doesNotHaveRoll(rolls, index)) {
            throw new IllegalStateException("Not enough rolls to build frames");
        }
        return rolls.get(index);
    }
}