package space_invaders.sprites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShotTest {
    @Test
    void testShotIsNotNull() {
        Shot shot = new Shot();
        assertNotNull(shot);
    }

    @ParameterizedTest
    @CsvSource({
            "357, 349",
            "358, 350",
            "359, 351",
            "179, 175",
            "1, 1",
            "0, 0",
            "-1, -1"
    })
    void testShotConstructor(int x, int y) {
        Shot shot = new Shot(x, y);
        int expectedX = x + 6;
        int expectedY = y - 1;

        assertNotNull(shot.getImage(), "La imagen de Shot no debería de ser null");
        assertEquals(expectedX, shot.getX(), "La posición de Shot debería ser " + x);
        assertEquals(expectedY, shot.getY(), "La posición de Shot debería ser " + y);
    }

    //TODO Por qué suma 6 a 'x' y resta 1 a 'y'?
}
