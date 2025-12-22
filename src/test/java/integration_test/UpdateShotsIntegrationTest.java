package integration_test;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Shot;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UpdateShotsIntegrationTest {
    private Board board;
    private Shot shotMock;
    private Alien alienMock;

    @BeforeEach
    public void setUp() {
        board = new Board();
        shotMock = mock(Shot.class);
        alienMock = mock(Alien.class);

        board.setShot(shotMock);
        List<Alien> aliensList = new ArrayList<>();
        aliensList.add(alienMock);
        board.setAliens(aliensList);
    }

    private void invokeUpdateShots() throws Exception {
        Method method = Board.class.getDeclaredMethod("update_shots");
        method.setAccessible(true);
        method.invoke(board);
    }

    // =========================================================================
    // CAMINO 1: COLISIÓN (Nodes: C1 -> C2 -> C3 -> C4 -> C5 -> C7)
    // =========================================================================
    @Test
    public void testMMPath_Collision() throws Exception {
        when(shotMock.isVisible()).thenReturn(true);
        when(shotMock.getX()).thenReturn(100);
        when(shotMock.getY()).thenReturn(100);

        when(alienMock.isVisible()).thenReturn(true);
        when(alienMock.getX()).thenReturn(100);
        when(alienMock.getY()).thenReturn(100);

        invokeUpdateShots();

        verify(alienMock, times(1)).setDying(true);

        verify(shotMock, atLeastOnce()).setY(anyInt());
    }

    // =========================================================================
    // CAMINO 2: MOVIMIENTO NORMAL (Nodes: ... -> C8 -> C9(No) -> C11)
    // =========================================================================
    @Test
    public void testMMPath_Movement() throws Exception {
        when(shotMock.isVisible()).thenReturn(true);
        when(alienMock.getX()).thenReturn(500);

        int initialY = 50;
        when(shotMock.getY()).thenReturn(initialY);

        invokeUpdateShots();

        verify(shotMock, times(1)).setY(initialY - Commons.SHOT_SPEED);

        verify(shotMock, never()).die();
    }

    // =========================================================================
    // CAMINO 3: SALIDA DE PANTALLA (Nodes: ... -> C8 -> C9(Yes) -> C10)
    // =========================================================================
    @Test
    public void testMMPath_OutOfBounds() throws Exception {
        when(shotMock.isVisible()).thenReturn(true);
        when(alienMock.isVisible()).thenReturn(false);

        when(shotMock.getY()).thenReturn(0);

        invokeUpdateShots();

        verify(shotMock, times(1)).die();

        verify(shotMock, never()).setY(anyInt());
    }
}
