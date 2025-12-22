package integration_test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlienBombIntegrationTest {

    @Test
    @DisplayName("Alien inicializa correctamente su Bomba")
    void testAlienInitializesBomb() {
        int startX = 100;
        int startY = 50;

        Alien alien = new Alien(startX, startY);
        Alien.Bomb bomb = alien.getBomb();

        assertNotNull(bomb, "El Alien debería tener una instancia de Bomba asociada");

        assertTrue(bomb.isDestroyed(), "La bomba debería inicializarse en estado 'destroyed'");
    }

    @Test
    @DisplayName("Sincronización de coordenadas (Simulación de disparo)")
    void testAlienBombCoordinateSync() {
        Alien alien = new Alien(200, 200);
        Alien.Bomb bomb = alien.getBomb();

        bomb.setDestroyed(false);
        bomb.setX(alien.getX());
        bomb.setY(alien.getY());

        assertEquals(alien.getX(), bomb.getX(), "La X de la bomba debe coincidir con la del Alien al disparar");
        assertEquals(alien.getY(), bomb.getY(), "La Y de la bomba debe coincidir con la del Alien al disparar");
    }
}
