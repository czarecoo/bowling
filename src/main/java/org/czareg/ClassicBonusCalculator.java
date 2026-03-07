package org.czareg;

import org.czareg.api.BonusCalculator;
import org.czareg.frames.*;

import java.util.List;
import java.util.Optional;

public class ClassicBonusCalculator implements BonusCalculator {

    final List<Frame> frames;

    public ClassicBonusCalculator(List<Frame> frames) {
        this.frames = frames;
    }

    @Override
    public int next(Frame frame) {
        return getNextFrame(frame)
                .map(Frame::firstRoll)
                .orElse(0);
    }

    @Override
    public int nextNext(Frame frame) {
        return getNextFrame(frame)
                .map(this::getSecondRollFrom)
                .orElse(0);
    }

    private Optional<Frame> getNextFrame(Frame frame) {
        int index = frames.indexOf(frame);
        if (index == -1 || index + 1 >= frames.size()) {
            return Optional.empty();
        }
        return Optional.of(frames.get(index + 1));
    }

    private int getSecondRollFrom(Frame frame) {
        return switch (frame) {
            case OpenFrame openFrame -> openFrame.secondRoll();
            case SpareFrame spareFrame -> spareFrame.secondRoll();
            case StrikeFrame ignored -> next(frame);
            case TenthFrame tenthFrame -> tenthFrame.secondRoll().orElse(0);
            case UnfinishedFrame ignored -> 0;
        };
    }
}
