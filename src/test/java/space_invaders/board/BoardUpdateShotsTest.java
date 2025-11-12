package space_invaders.board;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;
import java.util.List;

import main.Board;
import main.Commons;
import space_invaders.sprites.Alien;

public class BoardUpdateShotsTest {

    private Board board;

    @BeforeEach
    void setUp() throws Exception {
        board = new Board();
        Method init = board.getClass().getDeclaredMethod("gameInit");
        init.setAccessible(true);
        init.invoke(board);

        Alien alien = new Alien(30,30); // Pixeles de hit -> x[30, 42], y[30,42]
        board.setAliens(List.of(alien));
    }

    private void invokePrivateUpdateShot() throws Exception {
        Method m = board.getClass().getDeclaredMethod("update_shots");
        m.setAccessible(true);
        m.invoke(board);
    }

    @Test
    @DisplayName("Disparo visible colisiona al alien por el limite izquierdo")
    void shotShouldKillAlienWhenCollisionLeft() throws Exception {
        board.getShot().setX(30);
        board.getShot().setY(30);

        invokePrivateUpdateShot();

        assertTrue(board.getAliens().get(0).isVisible(), "El alien debe ser visible para ser eliminado");
        assertTrue(board.getDeaths() > 0, "Si un alien es eliminado debe aumentar el marcador");
    }

    @Test
    @DisplayName("Disparo visible colisiona al alien por el limite derecho")
    void shotShouldKillAlienWhenCollisionRight() throws Exception {
        board.getShot().setX(42);
        board.getShot().setY(42);

        invokePrivateUpdateShot();

        assertTrue(board.getAliens().get(0).isVisible(), "El alien debe ser visible para ser eliminado");
        assertTrue(board.getDeaths() > 0, "Si un alien es eliminado debe aumentar el marcador");
    }

    @Test
    @DisplayName("Disparo visible en movimiento sin colision")
    void shouldMoveUpWhenNoCollision() throws Exception {
        int lastX = 100;
        int lastY = 100;

        board.getShot().setX(lastX);
        board.getShot().setY(lastY);

        invokePrivateUpdateShot();

        assertEquals(lastY - Commons.SHOT_SPEED, board.getShot().getY(), "Las coordenadas Y deben disminuir");
        assertEquals(lastX, board.getShot().getX(), "Las coordenadas X deben mantenerse durante todo su recorrido");
        assertEquals(0, board.getDeaths(), "Si no se ha eliminado ningun alien el marcador de muertes debe mantenerse a cero");
    }

    @Test
    @DisplayName("Disparo cerca del borde superior debe desaparecer")
    void shotShouldDisappearWhenGetsOut() throws Exception {
        board.getShot().setX(100);
        board.getShot().setY(1);

        invokePrivateUpdateShot();

        assertFalse(board.getShot().isVisible(), "El disparo no debe ser visible en esa posicion");
    }

    @Test
    @DisplayName("Disparo visible colisiona a un alien ya muerto")
    void shotGetsDeadAlien() throws Exception {
        board.getShot().setX(30);
        board.getShot().setY(30);
        board.getAliens().get(0).die();

        invokePrivateUpdateShot();

        assertTrue(board.getDeaths() == 0, "Si el alien ya esta muerto no debe aumentar el marcador");
    }
}
