package org.czareg.frames;

import lombok.RequiredArgsConstructor;
import org.czareg.api.BonusCalculator;
import org.czareg.api.Frames;
import org.czareg.api.Game;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClassicFrames implements Frames {

    private final List<Frame> frames;

    public ClassicFrames() {
        this.frames = Collections.emptyList();
    }

    @Override
    public Optional<Frame> getNextFrame(Frame frame) {
        int index = frames.indexOf(frame);
        if (index == -1 || index + 1 >= frames.size()) {
            return Optional.empty();
        }
        return Optional.of(frames.get(index + 1));
    }

    @Override
    public int score() {
        BonusCalculator bonusCalculator = new ClassicBonusCalculator(this);
        return frames.stream()
                .mapToInt(frame -> frame.score(bonusCalculator))
                .sum();
    }

    @Override
    public boolean isComplete() {
        if (frames.size() < Game.MAX_FRAMES) {
            return false;
        }
        Frame tenthFrame = frames.getLast();
        return tenthFrame instanceof TenthFrame tf && tf.isComplete();
    }
}
