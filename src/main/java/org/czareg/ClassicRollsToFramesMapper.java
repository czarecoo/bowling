package org.czareg;

import org.czareg.api.RollsHandler;
import org.czareg.api.RollsToFramesMapper;
import org.czareg.frames.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ClassicRollsToFramesMapper implements RollsToFramesMapper {

    @Override
    public List<Frame> create(RollsHandler rollsHandler) {
        List<Frame> frames = new ArrayList<>();

        int rollIndex = 0;

        for (int frame = 1; frame <= Frame.MAX_FRAMES; frame++) {
            if (rollsHandler.doesNotHaveRoll(rollIndex)) {
                break;
            }

            if (frame == Frame.MAX_FRAMES) {
                frames.add(new TenthFrame(rollsHandler.rollsFrom(rollIndex)));
                break;
            }

            int firstRoll = rollsHandler.getRollOrThrow(rollIndex);

            if (firstRoll == Frame.MAX_SCORE_PER_FRAME) {
                frames.add(new StrikeFrame(firstRoll));
                rollIndex += 1;
                continue;
            }

            int nextRollIndex = rollIndex + 1;
            if (rollsHandler.doesNotHaveRoll(nextRollIndex)) {
                frames.add(new UnfinishedFrame(firstRoll));
                break;
            }

            int secondRoll = rollsHandler.getRollOrThrow(nextRollIndex);

            if (firstRoll + secondRoll == Frame.MAX_SCORE_PER_FRAME) {
                frames.add(new SpareFrame(firstRoll, secondRoll));
            } else {
                frames.add(new OpenFrame(firstRoll, secondRoll));
            }

            rollIndex += 2;
        }

        return Collections.unmodifiableList(frames);
    }
}