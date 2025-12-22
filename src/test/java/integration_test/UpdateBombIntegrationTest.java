package integration_test;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import javax.swing.Timer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateBombIntegrationTest {
    private Board board;
    private Player mockPlayer;
    private Alien mockAlien;
    private Alien.Bomb mockBomb;

    @BeforeEach
    void setUp() {
        board = new Board();
        mockPlayer = mock(Player.class);
        mockAlien = mock(Alien.class);
        mockBomb = mock(Alien.Bomb.class);

        List<Alien> aliens = new ArrayList<>();
        aliens.add(mockAlien);
        board.setAliens(aliens);
        board.setPlayer(mockPlayer);

        if (board.getTimer() != null) {
            board.getTimer().stop();
        }
        board.setTimer(mock(Timer.class));

        when(mockAlien.getBomb()).thenReturn(mockBomb);
    }

    // Método para no repetir código
    private void invokeUpdateBomb() {
        try {
            Method method = Board.class.getDeclaredMethod("update_bomb");
            method.setAccessible(true);
            method.invoke(board);
        } catch (Exception e) {
            fail("Error al invocar update_bomb: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Integración: La bomba colisiona con el jugador si están en la misma posición")
    void testUpdateBombCollisionWithPlayer() {
        List<Alien> listAliens = new ArrayList<>();
        listAliens.add(mockAlien);
        board.setAliens(listAliens);

        when(mockPlayer.isVisible()).thenReturn(true);
        when(mockAlien.isVisible()).thenReturn(true);
        when(mockBomb.isDestroyed()).thenReturn(false);

        int posicion = 50;
        when(mockPlayer.getX()).thenReturn(posicion);
        when(mockPlayer.getY()).thenReturn(posicion);
        when(mockBomb.getX()).thenReturn(posicion);
        when(mockBomb.getY()).thenReturn(posicion);

        invokeUpdateBomb();

        verify(mockPlayer, times(1)).setDying(true);
        verify(mockBomb, times(1)).setDestroyed(true);
    }

    @Test
    @DisplayName("Integración: La bomba debe avanzar hacia abajo")
    void testUpdateBombMoves() {
        when(mockBomb.isDestroyed()).thenReturn(false);
        when(mockPlayer.isVisible()).thenReturn(false);

        int posicion = 50;
        when(mockBomb.getY()).thenReturn(posicion);

        invokeUpdateBomb();

        verify(mockBomb, atLeastOnce()).getY();
    }

    @Test
    @DisplayName("Integración: La bomba debe destruirse al tocar el suelo")
    void testUpdateBombReachesGround() {
        when(mockBomb.isDestroyed()).thenReturn(false);
        when(mockPlayer.isVisible()).thenReturn(false);

        when(mockBomb.getY()).thenReturn(Commons.GROUND);

        invokeUpdateBomb();

        verify(mockBomb, atLeastOnce()).getY();
        verify(mockBomb, atLeastOnce()).setDestroyed(true);
    }

    @Test
    @DisplayName("Integración: Creación de la bomba")
    void testUpdateBombCreation() {
       when(mockAlien.isVisible()).thenReturn(true);
       when(mockBomb.isDestroyed()).thenReturn(true);

        try (MockedConstruction<Random> mockedRandom = mockConstruction(Random.class,
                (mock, context) -> when(mock.nextInt(15)).thenReturn(Commons.CHANCE))) {
            invokeUpdateBomb();
        }

        verify(mockBomb).setDestroyed(false);
        verify(mockBomb).getX();
        verify(mockBomb).getY();
        verify(mockBomb).setX(mockAlien.getX());
        verify(mockBomb).setY(mockAlien.getY());
    }
}
