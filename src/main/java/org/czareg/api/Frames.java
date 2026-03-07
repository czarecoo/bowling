package org.czareg.api;

import org.czareg.frames.Frame;

import java.util.Optional;

public interface Frames extends Scorable {

    Optional<Frame> getNextFrame(Frame frame);

    boolean isComplete();
}
