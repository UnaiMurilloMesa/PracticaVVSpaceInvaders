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

    @Test
    @DisplayName("Alien alcanza el borde derecho cambia dirección a -1 y baja")
    void shouldChangeDirectionToLeftWhenAtRightLimit() throws Exception {
        Alien alien = new Alien(Commons.BORDER_RIGHT, 100); // Alien en el borde derecho
        board.setAliens(List.of(alien));

        board.setDirection(1);

        int yBefore = alien.getY();
        invokePrivateUpdateAliens();

        assertEquals(-1, board.getDirection());
        assertEquals(yBefore + Commons.GO_DOWN, alien.getY());
    }

    @Test
    @DisplayName("Alien alcanza el borde izquierdo cambia dirección a 1 y baja")
    void shouldChangeDirectionToRightWhenAtLeftLimit() throws Exception {
        Alien alien = new Alien(Commons.BORDER_LEFT, 100); // Alien en el borde izquierdo
        board.setAliens(List.of(alien));

        board.setDirection(-1);

        int yBefore = alien.getY();
        invokePrivateUpdateAliens();

        assertEquals(1, board.getDirection());
        assertEquals(yBefore + Commons.GO_DOWN, alien.getY());
    }

    @Test
    @DisplayName("Alien por debajo de GROUND activa invasión")
    void shouldTriggerInvasionWhenAlienPassesYlimit() throws Exception {
        Alien alien = new Alien(100, Commons.GROUND + Commons.ALIEN_HEIGHT);
        board.setAliens(List.of(alien));

        invokePrivateUpdateAliens();

        assertFalse(board.isInGame());
        assertEquals("Invasion!", board.getMessage());
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

        assertTrue(initialX < alien.getX());
        assertEquals(initialY, alien.getY()); // no se debe mover en vertical
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

        assertTrue(initialX > alien.getX());
        assertEquals(initialY, alien.getY()); // no se debe mover en vertical
    }
}