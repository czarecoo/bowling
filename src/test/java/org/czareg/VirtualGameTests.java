package org.czareg;

import org.czareg.api.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VirtualGameTests {

    Game game;

    @BeforeEach
    void setUp() {
        game = VirtualGame.create();
    }

    @RepeatedTest(1000)
    void testConcurrentRollsCausingIncorrectScoring() throws Exception {
        int threads = 10;
        int rollingTasks = 20;
        try (ExecutorService executor = Executors.newFixedThreadPool(threads)) {
            CountDownLatch startLatch = new CountDownLatch(1);
            CountDownLatch doneLatch = new CountDownLatch(rollingTasks);
            for (int i = 0; i < rollingTasks; i++) {
                executor.submit(() -> {
                    try {
                        startLatch.await();
                        game.roll(1);
                    } catch (Exception ignored) {
                        // ignore runtime exceptions from race conditions
                    } finally {
                        doneLatch.countDown();
                    }
                });
            }
            startLatch.countDown();
            doneLatch.await();
        }

        assertEquals(20, game.score());
    }
}
