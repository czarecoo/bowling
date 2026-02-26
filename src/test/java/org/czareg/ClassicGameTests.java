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

    void rollMany(int times, int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }
}
