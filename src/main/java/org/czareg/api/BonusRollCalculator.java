package org.czareg.api;

import org.czareg.frames.Frame;

public interface BonusRollCalculator {

    int next(Frame frame);

    int nextNext(Frame frame);
}
