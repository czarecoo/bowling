package org.czareg;

import lombok.RequiredArgsConstructor;
import org.czareg.api.Game;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Thread-safe implementation.
 */
@RequiredArgsConstructor
class VirtualGame implements Game {

    private final Game game;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void roll(int pins) {
        lock.writeLock().lock();
        try {
            game.roll(pins);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public int score() {
        lock.readLock().lock();
        try {
            return game.score();
        } finally {
            lock.readLock().unlock();
        }
    }

    public static Game create() {
        return new VirtualGame(ClassicGame.create());
    }
}
