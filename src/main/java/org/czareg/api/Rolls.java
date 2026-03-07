package org.czareg.api;

import java.util.List;

public interface Rolls extends Rollable {

    boolean hasRoll(int index);

    default boolean doesNotHaveRoll(int index) {
        return !hasRoll(index);
    }

    int getRollOrThrow(int index);

    List<Integer> rollsFrom(int index);

    void removeLastRoll();
}
