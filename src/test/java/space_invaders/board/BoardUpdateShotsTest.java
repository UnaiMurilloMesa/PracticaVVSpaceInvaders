package space_invaders.board;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;

import main.Board;
import main.Commons;

public class BoardUpdateShotsTest {

    private Board board;

    @BeforeEach
    void setUp() throws Exception {
        board = new Board();
        Method init = board.getClass().getDeclaredMethod("gameInit");
        init.setAccessible(true);
        init.invoke(board);
    }

    private void invokePrivateUpdateShot() throws Exception {
        Method m = board.getClass().getDeclaredMethod("updateShot");
        m.setAccessible(true);
        m.invoke(board);
    }

    @Test
    @DisplayName("El disparo debería ser visible")
    void shotShouldBeVisible() throws Exception {
        invokePrivateUpdateShot();

        assertTrue(board.getShot().isVisible());
    }
}
