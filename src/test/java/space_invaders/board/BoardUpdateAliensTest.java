package space_invaders.board;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;
import java.util.List;

import main.Board;
import main.Commons;
import space_invaders.sprites.Alien;

public class BoardUpdateAliensTest {

    private Board board;

    @BeforeEach
    void setUp() throws Exception {
        board = new Board();
        Method init = board.getClass().getDeclaredMethod("gameInit");
        init.setAccessible(true);
        init.invoke(board);
    }

    private void invokePrivateUpdateAliens() throws Exception {
        Method updateAliensMethod = board.getClass().getDeclaredMethod("update_aliens");
        updateAliensMethod.setAccessible(true);
        updateAliensMethod.invoke(board);
    }

    // CAJA BLANCA ->
    @Test
    @DisplayName("Alien por debajo de GROUND activa invasión")
    void shouldTriggerInvasionWhenAlienPassesGround() throws Exception {
        Alien alien = new Alien(20, Commons.GROUND + Commons.ALIEN_HEIGHT);
        board.setAliens(List.of(alien));

        invokePrivateUpdateAliens();

        assertFalse(board.isInGame(), "El juego debe terminar si hay invasión");
        assertEquals("Invasion!", board.getMessage(), "El mensaje del juego debe ser el de Invasión");
    }

    @Test
    @DisplayName("Alien alcanza el borde derecho cambia dirección a -1 y baja")
    void shouldChangeDirectionToLeftWhenAtRightLimit() throws Exception {
        Alien alien = new Alien(Commons.BORDER_RIGHT, 100); // Alien en el borde derecho
        board.setAliens(List.of(alien));

        board.setDirection(1);

        int yBefore = alien.getY();
        invokePrivateUpdateAliens();

        assertEquals(-1, board.getDirection(), "El alien debe ir hacia la izquierda");
        assertEquals(yBefore + Commons.GO_DOWN, alien.getY(), "El alien debe haber bajado Commons.GO_DOWN pixeles");
    }

    @Test
    @DisplayName("Alien alcanza el borde izquierdo cambia dirección a 1 y baja")
    void shouldChangeDirectionToRightWhenAtLeftLimit() throws Exception {
        Alien alien = new Alien(Commons.BORDER_LEFT, 100); // Alien en el borde izquierdo
        board.setAliens(List.of(alien));

        board.setDirection(-1);

        int yBefore = alien.getY();
        invokePrivateUpdateAliens();

        assertEquals(1, board.getDirection(), "El alien debe ir hacia la derecha");
        assertEquals(yBefore + Commons.GO_DOWN, alien.getY(),"El alien debe haber bajado Commons.GO_DOWN pixeles");
    }

    @Test
    @DisplayName("Aliens se mueven a la derecha cuando la dirección es 1")
    void visibleAliensShouldMoveRightWhenDirectionIsPositive() throws Exception {
        Alien alien = new Alien(20, 100);
        board.setAliens(List.of(alien));

        int initialX = alien.getX();
        int initialY = alien.getY();

        board.setDirection(1);
        invokePrivateUpdateAliens();

        assertTrue(board.getAliens().get(0).isVisible(), "El alien debe ser visible");
        assertTrue(initialX < alien.getX(), "El alien debe moverse hacia la derecha si la dirección es 1");
        assertEquals(initialY, alien.getY(), "El alien no debe moverse en vertical");
    }

    @Test
    @DisplayName("Aliens se mueven a la izquierda si la dirección es -1")
    void visibleAliensShouldMoveLeftWhenDirectionIsNegative() throws Exception {
        Alien alien = new Alien(20, 100);
        board.setAliens(List.of(alien));

        int initialX = alien.getX();
        int initialY = alien.getY();

        board.setDirection(-1);
        invokePrivateUpdateAliens();

        assertTrue(board.getAliens().get(0).isVisible(), "El alien debe ser visible");
        assertTrue(initialX > alien.getX(), "EL alien debe moverse a la izquierda si la posicion es -1");
        assertEquals(initialY, alien.getY(), "El alien no debe moverse en vertical");
    }
}