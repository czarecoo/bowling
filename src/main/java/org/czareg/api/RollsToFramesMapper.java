package org.czareg.api;

import org.czareg.frames.Frame;

import java.util.List;

public interface RollsToFramesMapper {

    List<Frame> create(RollsHandler rollsHandler);
}
