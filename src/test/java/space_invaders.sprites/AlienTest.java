package space_invaders.sprites;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.Commons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import space_invaders.sprites.Alien;

class AlienTest {
    private Alien alien;

    @ParameterizedTest(name = "[{index}] initAlien({0}, {1}) => expected ({2}, {3})")
    @DisplayName("Valores límite por robust para init de Alien")
    @CsvSource({
            "0, 0, 0, 0",
            "358, 350, 358, 350",
            "-1, 0, 0, 0",         // x fuera del límite inferior
            "0, -1, 0, 0",         // y fuera del límite inferior
            "359, 350, 358, 350",  // x fuera del límite superior
            "358, 351, 358, 350"   // y fuera del límite superior
    })
    void testAlienInit_robustBoundary(int x, int y, int expectedX, int expectedY) {
        alien = new Alien(x, y);
        assertEquals(expectedX, alien.getX(), "X no coincide con valor esperado");
        assertEquals(expectedY, alien.getY(), "Y no coincide con valor esperado");
    }

    @ParameterizedTest(name = "[{index}] initAlien({0}, 0) => alien.act({1}) => expectedX ({2})")
    @DisplayName("Valores límite por robust para act de Alien")
    @CsvSource({
            "0, -1, 0",   // Movimiento fuera del límite inferior
            "179, 1, 1",    // Movimiento válido
            "358, 1, 358" // Movimiento fuera del límite superior
    })
    void testAlienAct_robustBoundary(int direction, int expectedX) {
        alien = new Alien(0, 0);
        alien.act(direction);
        assertEquals(expectedX, alien.getX(), "X no coincide con el valor esperado");
    }
}
