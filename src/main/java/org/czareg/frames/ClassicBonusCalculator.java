package org.czareg.frames;

import lombok.RequiredArgsConstructor;
import org.czareg.api.BonusCalculator;
import org.czareg.api.Frames;

@RequiredArgsConstructor
class ClassicBonusCalculator implements BonusCalculator {

    private final Frames frames;

    @Override
    public int next(Frame frame) {
        return frames.getNextFrame(frame)
                .map(Frame::firstRoll)
                .orElse(0);
    }

    @Override
    public int nextNext(Frame frame) {
        return frames.getNextFrame(frame)
                .map(this::getSecondRollFrom)
                .orElse(0);
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
