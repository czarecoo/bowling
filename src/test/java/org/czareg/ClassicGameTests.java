package org.czareg;

import org.czareg.api.Game;
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
        assertEquals(5, game.score());
        game.roll(5); // spare
        assertEquals(10, game.score());
        game.roll(3); // doubled
        assertEquals(16, game.score());
        rollMany(17, 0);

        assertEquals(16, game.score());
    }

    @Test
    void testOneStrikeGame() {
        game.roll(10); // strike
        assertEquals(10, game.score());
        game.roll(3); // doubled
        assertEquals(16, game.score());
        game.roll(4); // doubled
        assertEquals(24, game.score());
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
        assertEquals(10, game.score());
        // Frame 2: 7, 3        -> spare
        game.roll(7);
        assertEquals(24, game.score());
        game.roll(3);
        assertEquals(30, game.score());
        // Frame 3: 9, 0        -> open
        game.roll(9);
        assertEquals(48, game.score());
        game.roll(0);
        assertEquals(48, game.score());
        // Frame 4: 10          -> strike
        game.roll(10);
        assertEquals(58, game.score());
        // Frame 5: 0, 8        -> open (gutter + 8)
        game.roll(0);
        assertEquals(58, game.score());
        game.roll(8);
        assertEquals(74, game.score());
        // Frame 6: 8, 2        -> spare
        game.roll(8);
        assertEquals(82, game.score());
        game.roll(2);
        assertEquals(84, game.score());
        // Frame 7: 0, 6        -> open
        game.roll(0);
        assertEquals(84, game.score());
        game.roll(6);
        assertEquals(90, game.score());
        // Frame 8: 10          -> strike
        game.roll(10);
        assertEquals(100, game.score());
        // Frame 9: 10          -> strike
        game.roll(10);
        assertEquals(120, game.score());
        // Frame 10: 10, 8, 1   -> strike + bonus rolls
        game.roll(10);
        assertEquals(150, game.score());
        game.roll(8);
        assertEquals(166, game.score());
        game.roll(1);

        assertEquals(167, game.score());
    }

    @Test
    void testRealChaoticGame() {
        // Frame 1: 1,4
        game.roll(1);
        assertEquals(1, game.score());
        game.roll(4);
        assertEquals(5, game.score());
        // Frame 2: 0,5
        game.roll(0);
        assertEquals(5, game.score());
        game.roll(5);
        assertEquals(10, game.score());
        // Frame 3: 3,6
        game.roll(3);
        assertEquals(13, game.score());
        game.roll(6);
        assertEquals(19, game.score());
        // Frame 4: 0,0
        game.roll(0);
        assertEquals(19, game.score());
        game.roll(0);
        assertEquals(19, game.score());
        // Frame 5: 2,8 (spare)
        game.roll(2);
        assertEquals(21, game.score());
        game.roll(8);
        assertEquals(29, game.score());
        // Frame 6: 1,0
        game.roll(1);
        assertEquals(31, game.score());
        game.roll(0);
        assertEquals(31, game.score());
        // Frame 7: 10 (strike)
        game.roll(10);
        assertEquals(41, game.score());
        // Frame 8: 2,3
        game.roll(2);
        assertEquals(45, game.score());
        game.roll(3);
        assertEquals(51, game.score());
        // Frame 9: 0,7
        game.roll(0);
        assertEquals(51, game.score());
        game.roll(7);
        assertEquals(58, game.score());
        // Frame 10: 1,1
        game.roll(1);
        assertEquals(59, game.score());
        game.roll(1);
        assertEquals(60, game.score());

        // Final assertion
        assertEquals(60, game.score());
    }

    @Test
    void testRealAlmostPerfectGame() {
        // Frames 1–9: strikes
        rollMany(9, 10);
        // Frame 10: strike + bonus rolls
        assertEquals(240, game.score());
        game.roll(10);
        assertEquals(270, game.score());
        game.roll(7);
        assertEquals(284, game.score());
        game.roll(2);

        assertEquals(286, game.score());
    }

    void rollMany(int times, int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }
}
