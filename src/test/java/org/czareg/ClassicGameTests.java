package org.czareg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassicGameTests {

    Game game;

    @BeforeEach
    void setUp() {
        game = new ClassicGame();
    }

    @Test
    void testGutterGame() {
        rollMany(20, 0);

        assertEquals(0, game.score());
    }

    @Test
    void testAllOnesGame() {
        rollMany(20, 1);

        assertEquals(20, game.score());
    }

    @Test
    void testOneSpareGame() {
        game.roll(5);
        game.roll(5); // spare
        game.roll(3); // doubled
        rollMany(17, 0);

        assertEquals(16, game.score());
    }

    @Test
    void testOneStrikeGame() {
        game.roll(10); // strike
        game.roll(3); // doubled
        game.roll(4); // doubled
        rollMany(16, 0);

        assertEquals(24, game.score());
    }

    @Test
    void testPerfectGame() {
        rollMany(12,10);

        assertEquals(300, game.score());
    }

    @Test
    void testRealNormalGame() {
        // Frame 1: 10          -> strike
        game.roll(10);
        // Frame 2: 7, 3        -> spare
        game.roll(7);
        game.roll(3);
        // Frame 3: 9, 0        -> open
        game.roll(9);
        game.roll(0);
        // Frame 4: 10          -> strike
        game.roll(10);
        // Frame 5: 0, 8        -> open (gutter + 8)
        game.roll(0);
        game.roll(8);
        // Frame 6: 8, 2        -> spare
        game.roll(8);
        game.roll(2);
        // Frame 7: 0, 6        -> open
        game.roll(0);
        game.roll(6);
        // Frame 8: 10          -> strike
        game.roll(10);
        // Frame 9: 10          -> strike
        game.roll(10);
        // Frame 10: 10, 8, 1   -> strike + bonus rolls
        game.roll(10);
        game.roll(8);
        game.roll(1);

        assertEquals(167, game.score());
    }

    @Test
    void testRealChaoticGame() {
        // Frame 1: 1,4
        game.roll(1);
        game.roll(4);
        // Frame 2: 0,5
        game.roll(0);
        game.roll(5);
        // Frame 3: 3,6
        game.roll(3);
        game.roll(6);
        // Frame 4: 0,0
        game.roll(0);
        game.roll(0);
        // Frame 5: 2,8  -> spare
        game.roll(2);
        game.roll(8);
        // Frame 6: 1,0
        game.roll(1);
        game.roll(0);
        // Frame 7: 10   -> strike
        game.roll(10);
        // Frame 8: 2,3
        game.roll(2);
        game.roll(3);
        // Frame 9: 0,7
        game.roll(0);
        game.roll(7);
        // Frame 10: 1,1
        game.roll(1);
        game.roll(1);

        assertEquals(60, game.score());
    }

    @Test
    void testRealAlmostPerfectGame() {
        // Frames 1–9: strikes
        rollMany(9, 10);
        // Frame 10: strike + bonus rolls
        game.roll(10);
        game.roll(7);
        game.roll(2);

        assertEquals(286, game.score());
    }

    void rollMany(int times, int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }
}
