package org.czareg;

import org.czareg.api.BonusRollCalculator;
import org.czareg.frames.*;

import java.util.List;

public class ClassicBonusCalculator implements BonusRollCalculator {

    final List<Frame> frames;

    public ClassicBonusCalculator(List<Frame> frames) {
        this.frames = frames;
    }

    @Override
    public int next(Frame frame) {
        Frame nextFrame = getNextFrame(frame);
        return getFirstRollFrom(nextFrame);
    }

    @Override
    public int nextNext(Frame frame) {
        Frame nextFrame = getNextFrame(frame);
        return getSecondRollFrom(nextFrame);
    }

    private Frame getNextFrame(Frame frame) {
        int index = frames.indexOf(frame);
        if (index == -1 || index + 1 >= frames.size()) {
            return Frame.EMPTY_FRAME;
        }
        return frames.get(index + 1);
    }

    private int getFirstRollFrom(Frame frame) {
        return switch (frame) {
            case EmptyFrame ignored -> 0;
            case OpenFrame openFrame -> openFrame.firstRoll();
            case SpareFrame spareFrame -> spareFrame.firstRoll();
            case StrikeFrame strikeFrame -> strikeFrame.roll();
            case TenthFrame tenthFrame -> tenthFrame.rolls().getFirst();
            case UnfinishedFrame unfinishedFrame -> unfinishedFrame.firstRoll();
        };
    }

    private int getSecondRollFrom(Frame frame) {
        return switch (frame) {
            case EmptyFrame ignored -> 0;
            case OpenFrame openFrame -> openFrame.secondRoll();
            case SpareFrame spareFrame -> spareFrame.secondRoll();
            case StrikeFrame ignored -> {
                Frame nextFrame = getNextFrame(frame);
                if (nextFrame == null) {
                    yield 0;
                }
                yield getFirstRollFrom(nextFrame);
            }
            case TenthFrame tenthFrame -> {
                List<Integer> rolls = tenthFrame.rolls();
                if (rolls.size() > 1) {
                    yield rolls.get(1);
                } else {
                    yield 0;
                }
            }
            case UnfinishedFrame ignored -> 0;
        };
    }
}
