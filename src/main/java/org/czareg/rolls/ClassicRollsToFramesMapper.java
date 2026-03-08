package org.czareg.rolls;

import org.czareg.api.Frames;
import org.czareg.api.Game;
import org.czareg.api.Rolls;
import org.czareg.api.RollsToFramesMapper;
import org.czareg.frames.*;

import java.util.ArrayList;
import java.util.List;

public class ClassicRollsToFramesMapper implements RollsToFramesMapper {

    @Override
    public Frames map(Rolls rolls) {
        List<Frame> frames = new ArrayList<>();

        int rollIndex = 0;

        for (int frame = 1; frame <= Game.MAX_FRAMES; frame++) {
            if (rolls.doesNotHaveRoll(rollIndex)) {
                break;
            }

            if (frame == Game.MAX_FRAMES) {
                frames.add(new TenthFrame(rolls.rollsFrom(rollIndex)));
                break;
            }

            int firstRoll = rolls.getRollOrThrow(rollIndex);

            if (firstRoll == Game.MAX_SCORE_PER_FRAME) {
                frames.add(new StrikeFrame(firstRoll));
                rollIndex += 1;
                continue;
            }

            int nextRollIndex = rollIndex + 1;
            if (rolls.doesNotHaveRoll(nextRollIndex)) {
                frames.add(new UnfinishedFrame(firstRoll));
                break;
            }

            int secondRoll = rolls.getRollOrThrow(nextRollIndex);

            if (firstRoll + secondRoll == Game.MAX_SCORE_PER_FRAME) {
                frames.add(new SpareFrame(firstRoll, secondRoll));
            } else {
                frames.add(new OpenFrame(firstRoll, secondRoll));
            }

            rollIndex += 2;
        }

        return new ClassicFrames(frames);
    }
}