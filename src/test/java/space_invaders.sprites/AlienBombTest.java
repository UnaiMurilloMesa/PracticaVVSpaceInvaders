package space_invaders.sprites;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlienBombTest {
    private Alien.Bomb alienBomb;
    private Alien alien;

    @ParameterizedTest(name = "[{index}] initBomb({0}, {1}) => expected ({2}, {3})")
    @DisplayName("Valores límite por robust para init de Bomb")
    @CsvSource({
            "0, 0, 0, 0",
            "358, 350, 358, 350",
            "-1, 0, 0, 0",
            "0, -1, 0, 0",
            "359, 350, 358, 350",
            "358, 351, 358, 350"
    })
    void testAlienInit_robustBoundary(int x, int y, int expectedX, int expectedY) {
        alien = new Alien(0, 0);
        alienBomb = alien.new Bomb(x, y);
        assertEquals(alienBomb.getX(), expectedX);
        assertEquals(alienBomb.getY(), expectedY);
    }
}
