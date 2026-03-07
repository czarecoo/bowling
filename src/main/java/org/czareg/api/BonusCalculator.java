package org.czareg.api;

import org.czareg.frames.Frame;

public interface BonusCalculator {

    int next(Frame frame);

    int nextNext(Frame frame);
}
