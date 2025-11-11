package space_invaders.board;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;

import main.Board;
import main.Commons;

public class BoardUpdateTest {

    private Board board;

    @BeforeEach
    void setUp() throws Exception {
        board = new Board();
        Method init = board.getClass().getDeclaredMethod("gameInit");
        init.setAccessible(true);
        init.invoke(board);
    }

    private void invokePrivateUpdate() throws Exception {
        Method m = board.getClass().getDeclaredMethod("update");
        m.setAccessible(true);
        m.invoke(board);
    }

    @Test
    @DisplayName("El juego se gana cuando se han destruido los 24 aliens")
    void shouldEndGameWithVictoryWhenAllAliensDestroyed() throws Exception {
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);

        invokePrivateUpdate();

        assertFalse(board.isInGame(), "El juego debe finalizar");
        assertFalse(board.getTimer().isRunning(), "El temporizador debe finalizar" );
        assertEquals("Game won!", board.getMessage(), "Debe mostrar el mensaje de victoria");
    }

    @Test
    @DisplayName("El juego continua si hay menos de 24 aliens destruidos")
    void shouldntEndGameWhenThereAreAliens() throws Exception {
        board.setDeaths(10);

        invokePrivateUpdate();

        assertTrue(board.isInGame(), "El juego debe continuar");
        assertTrue(board.getTimer().isRunning(), "El temporizador debe continuar");
        assertNotEquals("Game Won!", board.getMessage(), "No debe mostrar ningun mensaje");
    }
}
