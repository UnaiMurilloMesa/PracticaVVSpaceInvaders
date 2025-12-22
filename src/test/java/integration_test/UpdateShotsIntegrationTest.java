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

        // mocks
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
    // TEST 1: MM-PATH DE COLISION
    // B1 -> S1 -> B2 -> S2 -> B3 -> A1 -> B4 -> A2 -> B5 -> S3
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



        verify(shotMock, atLeastOnce()).isVisible();

        verify(shotMock, atLeastOnce()).getX();
        verify(shotMock, atLeastOnce()).getY();

        verify(alienMock, atLeastOnce()).getX();
        verify(alienMock, atLeastOnce()).getY();

        verify(alienMock, times(1)).setDying(true);
    }

    // =========================================================================
    // TEST 2: MM-PATH DE MOVIMIENTO (Sin colision)
    // B1 -> S1 -> B2 -> S2 -> B3 -> A1 -> B4(No) -> B5 -> S3(setY)
    // =========================================================================
    @Test
    public void testMMPath_Movement() throws Exception {
        when(shotMock.isVisible()).thenReturn(true);

        when(shotMock.getX()).thenReturn(100);
        when(shotMock.getY()).thenReturn(100);

        when(alienMock.isVisible()).thenReturn(true);
        when(alienMock.getX()).thenReturn(200);
        when(alienMock.getY()).thenReturn(200);

        invokeUpdateShots();



        verify(alienMock, never()).setDying(true);

        int expectedY = 100 - Commons.SHOT_SPEED;

        verify(shotMock, times(1)).setY(expectedY);

        verify(shotMock, never()).die();
    }

    // =========================================================================
    // TEST 3: MM-PATH DE FRONTERA (Sale del tablero)
    // ... -> B5 -> msg -> S3(die)
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

    // =========================================================================
    // TEST 4: MM-PATH TRIVIAL (No hay disparo)
    // B1 -> S1(false) -> Fin
    // =========================================================================
    @Test
    public void testMMPath_NoShot() throws Exception {
        when(shotMock.isVisible()).thenReturn(false);

        invokeUpdateShots();

        verify(shotMock, times(1)).isVisible();
        verify(shotMock, never()).getX();
        verify(alienMock, never()).getX();
    }
}
