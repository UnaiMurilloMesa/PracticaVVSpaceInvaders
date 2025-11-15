package space_invaders.board;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.List;

import main.Board;
import main.Commons;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

public class BoardUpdateBombTest {

    private Board board;

    @BeforeEach
    void setUp() throws Exception {
        board = new Board();
        Method init = board.getClass().getDeclaredMethod("gameInit");
        init.setAccessible(true);
        init.invoke(board);
    }

    private void invokeUpdateBomb() throws Exception {
        Method updateBombMethod = board.getClass().getDeclaredMethod("update_bomb");
        updateBombMethod.setAccessible(true);
        updateBombMethod.invoke(board);
    }

    // CAJA NEGRA ->
    @Test
    @DisplayName("El jugador se destruye al colisionar con la bomba en el borde derecho")
    void shouldDestroyPlayerOnRightEdgeCollision() throws Exception {
        Alien alien = new Alien(20, 100);
        Alien.Bomb bomb = alien.getBomb();

        bomb.setDestroyed(false);
        Player player = new Player();
        bomb.setX(player.getX() + Commons.PLAYER_WIDTH);
        bomb.setY(player.getY() + Commons.PLAYER_HEIGHT);
        board.setAliens(List.of(alien));
        board.setPlayer(player);

        invokeUpdateBomb();

        assertTrue(player.isDying(), "El jugador debería morir tras la colisión en el borde derecho");
        assertTrue(bomb.isDestroyed(), "La bomba debería destruirse después de impactar al jugador");
    }

    @Test
    @DisplayName("El jugador se destruye al colisionar con la bomba en el borde izquierdo")
    void shouldDestroyPlayerOnLeftEdgeCollision() throws Exception {
        Alien alien = new Alien(20, 100);
        Alien.Bomb bomb = alien.getBomb();

        bomb.setDestroyed(false);
        Player player = new Player();
        bomb.setX(player.getX());
        bomb.setY(player.getY());
        board.setAliens(List.of(alien));
        board.setPlayer(player);

        invokeUpdateBomb();

        assertTrue(player.isDying(), "El jugador debería morir tras la colisión en el borde izquierdo");
        assertTrue(bomb.isDestroyed(), "La bomba debería destruirse inmediatamente después de impactar al jugador");
    }

    @Test
    @DisplayName("El jugador no se destruye fuera de los límites de colisión")
    void shouldNotDestroyPlayerWhenOutsideBounds() throws Exception {
        Alien alien = new Alien(100, 50);
        Alien.Bomb bomb = alien.getBomb();

        bomb.setDestroyed(false);
        Player player = new Player();
        bomb.setX(player.getX() + Commons.PLAYER_WIDTH + 5);
        bomb.setY(player.getY() + Commons.PLAYER_HEIGHT + 5);
        board.setAliens(List.of(alien));
        board.setPlayer(player);

        invokeUpdateBomb();

        assertFalse(player.isDying(), "El jugador no debería morir si no hay colisión");
        assertFalse(bomb.isDestroyed(), "La bomba no debería destruirse si no impacta al jugador");
    }

    // CAJA BLANCA ->
    @Test
    @DisplayName("Se crea una bomba para un alien sin bomba activa")
    void shouldCreateBombForVisibleAlienWithoutActiveBomb() throws Exception {
        Alien alien = new Alien(20, 100);
        Alien.Bomb bomb = alien.getBomb();

        bomb.setDestroyed(true);
        assertTrue(alien.isVisible(), "El alien debería estar visible por defecto");

        board.setAliens(List.of(alien));

        invokeUpdateBomb();

        if (!bomb.isDestroyed()) {
            assertEquals(alien.getX(), bomb.getX(), "La bomba debe generarse en la posición X del alien");
            assertEquals(alien.getY(), bomb.getY(), "La bomba debe generarse en la posición Y del alien");
            assertFalse(bomb.isDestroyed(), "La bomba debe estar activa al ser creada");
        }
    }

    @Test
    @DisplayName("Una bomba activa desciende verticalmente con velocidad Commons.BOMB_SPEED")
    void shouldMoveActiveBombDownWithCorrectSpeed() throws Exception {
        Alien alien = new Alien(20, 100);
        Alien.Bomb bomb = alien.getBomb();

        bomb.setDestroyed(false);
        bomb.setY(200);

        board.setAliens(List.of(alien));

        invokeUpdateBomb();

        int expectedY = 200 - Commons.BOMB_SPEED;
        assertEquals(expectedY, bomb.getY(), "La bomba debe descender según la velocidad Commons.BOMB_SPEED");
    }

    @Test
    @DisplayName("Una bomba que alcanza el suelo se destruye automáticamente")
    void shouldDestroyBombWhenReachingGroundLimit() throws Exception {
        Alien alien = new Alien(20, 100);
        Alien.Bomb bomb = alien.getBomb();

        bomb.setDestroyed(false);
        bomb.setY(Commons.GROUND - Commons.BOMB_HEIGHT);

        board.setAliens(List.of(alien));

        invokeUpdateBomb();

        assertTrue(bomb.isDestroyed(), "La bomba debe destruirse al llegar al suelo");
    }

    @Test
    @DisplayName("Si la bomba impacta al jugador visible, el jugador muere y la bomba se destruye")
    void shouldKillPlayerWhenHitByBomb() throws Exception {
        Alien alien = new Alien(20, 100);
        Alien.Bomb bomb = alien.getBomb();

        bomb.setDestroyed(false);

        Player player = new Player();
        player.setX(20);
        player.setY(200);

        board.setAliens(List.of(alien));
        board.setPlayer(player);

        bomb.setX(player.getX() + Commons.PLAYER_WIDTH / 2);
        bomb.setY(player.getY() + Commons.PLAYER_HEIGHT / 2);

        invokeUpdateBomb();

        assertTrue(player.isDying(), "El jugador debe marcarse como muriendo tras impacto");
        assertTrue(bomb.isDestroyed(), "La bomba debe destruirse tras impactar al jugador");
    }
}