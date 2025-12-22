package integration_test;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
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
        when(mockPlayer.isVisible()).thenReturn(true);
        when(mockAlien.isVisible()).thenReturn(true);
        when(mockBomb.isDestroyed()).thenReturn(false);

        when(mockPlayer.getX()).thenReturn(100);
        when(mockPlayer.getY()).thenReturn(100);
        when(mockBomb.getX()).thenReturn(100);
        when(mockBomb.getY()).thenReturn(100);

        invokeUpdateBomb();

        verify(mockPlayer).setDying(true);
        verify(mockBomb).setDestroyed(true);
    }

    @Test
    @DisplayName("Integración: La bomba debe destruirse al tocar el suelo")
    void testUpdateBombReachesGround() {
        when(mockBomb.isDestroyed()).thenReturn(false);
        when(mockPlayer.isVisible()).thenReturn(false);

        when(mockBomb.getY()).thenReturn(Commons.GROUND);

        invokeUpdateBomb();

        verify(mockBomb).setDestroyed(true);
    }
}
