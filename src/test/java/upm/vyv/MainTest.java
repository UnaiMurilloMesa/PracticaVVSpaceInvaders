package upm.vyv;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
class MainTest {

    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {}

    @Test
    void pruebaTest() {
        Alien alien = new Alien(10, 10);
        boolean resultado = alien.getX() == 10 && alien.getY() == 10;
        assertTrue(resultado);
    }
}