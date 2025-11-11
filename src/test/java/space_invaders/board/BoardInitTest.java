package space_invaders.board;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;
import java.util.List;

import main.Board;
import main.Commons;
import space_invaders.sprites.Alien;


public class BoardInitTest {

    private Board board;

    @BeforeEach
    void setUp() throws Exception {
        board = new Board();
    }

    // Para invocar los metodos en la clase sin tener que cambiar el jar
    private void invokePrivateMethod() throws Exception {
        Method init = board.getClass().getDeclaredMethod("gameInit");
        init.setAccessible(true);
        init.invoke(board);
    }

    @Test
    @DisplayName("Añade 24 aliens al iniciar la partida")
    void shouldCreate24AliensOnInit() throws Exception {
        invokePrivateMethod();

        assertEquals(Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS, board.getAliens().size());
    }

    @Test
    @DisplayName("Primer alien inicializado en primera posicion esperada")
    void shouldCreateFirstAlienAtInitialPosition() throws Exception {
        invokePrivateMethod();

        List<Alien> aliens = board.getAliens();

        Alien firstAlien = aliens.get(0);
        int x = firstAlien.getX();
        int y = firstAlien.getY();

        assertEquals(Commons.ALIEN_INIT_X, x);
        assertEquals(Commons.ALIEN_INIT_Y, y);
    }

    @Test
    @DisplayName("Ultimo alien inicializado en la posicion limite esperada de la matriz")
    void shouldCreateLastAlienAtExpectedBoundary() throws Exception {
        invokePrivateMethod();

        List<Alien> aliens = board.getAliens();

        Alien lastAlien = aliens.get(aliens.size() - 1);
        int x = lastAlien.getX();
        int y = lastAlien.getY();

        int expectedX = Commons.ALIEN_INIT_X + (Commons.ALIEN_COLUMNS - 1) * Commons.ALIEN_SEPARATOR;
        int expectedY = Commons.ALIEN_INIT_Y + (Commons.ALIEN_ROWS - 1) * Commons.ALIEN_SEPARATOR;

        assertEquals(expectedX, x);
        assertEquals(expectedY, y);
    }

    @Test
    @DisplayName("Los elementos deben inicializarse al iniciar el juego")
    void shouldCreatePlayer() throws Exception {
        invokePrivateMethod();

        assertNotNull(board.getPlayer(), "El jugador debe existir");
        assertNotNull(board.getShot(), "El disparo debe existir");
    }

    @Test
    @DisplayName("No duplica entidades si se llama dos veces")
    void shouldNotDuplicateEntitiesOnDoubleInit() throws Exception {
        invokePrivateMethod();
        invokePrivateMethod();

        assertEquals(Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS, board.getAliens().size(), "La lista de aliens no debe duplicarse al reiniciar");
    }
}
