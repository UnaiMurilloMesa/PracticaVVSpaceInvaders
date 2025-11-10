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
    @DisplayName("Disparo visible sin colision en alien")
    void shouldMoveUpWhenVisibleNoCollision() throws Exception {
        int lastY = 100;

        board.getShot().setY(lastY);

        invokePrivateUpdateShot();

        assertEquals(lastY + Commons.SHOT_SPEED, board.getShot().getY());
        assertEquals(0, board.getDeaths());
    }

    @Test
    @DisplayName("Disparo visible alcanza alien")
    void shotShouldKillAlienWhenCollision() throws Exception {
        board.getShot().setX(30);
        board.getShot().setY(30);

        invokePrivateUpdateShot();

        assertTrue(board.getAliens().get(0).isVisible());
        assertTrue(board.getDeaths() > 0);
    }

    @Test
    @DisplayName("Disparo cerca del borde superior debe desaparecer")
    void shotShouldDisappearSuperiorWhenGetsOut() throws Exception {
        board.getShot().setY(Commons.BOARD_HEIGHT);

        invokePrivateUpdateShot();

        assertTrue(board.getShot().isDying());
    }

    @Test
    @DisplayName("Disparo invisible no debe hacer nada")
    void invisibleShotShouldDie() throws Exception {
        board.getShot().setX(Commons.BOARD_WIDTH);
        board.getShot().setY(Commons.BOARD_HEIGHT);

        invokePrivateUpdateShot();

        assertTrue(board.getShot().isDying());
    }
}
