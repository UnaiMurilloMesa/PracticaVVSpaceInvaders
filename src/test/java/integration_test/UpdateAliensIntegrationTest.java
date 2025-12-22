package integration_test;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class UpdateAliensIntegrationTest {
    private Board board;
    private Alien alienMock;

    @BeforeEach
    public void setUp() {
        board = new Board();
        alienMock = mock(Alien.class);

        List<Alien> aliensList = new ArrayList<>();
        aliensList.add(alienMock);
        board.setAliens(aliensList);
    }

    private void invokeUpdateAliens() throws Exception {
        Method method = Board.class.getDeclaredMethod("update_aliens");
        method.setAccessible(true);
        method.invoke(board);
    }

    @Test
    public void testMMPath_Aliens_HitRightBorder() throws Exception {
        when(alienMock.getX()).thenReturn(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT + 1);

        invokeUpdateAliens();

        verify(alienMock, atLeastOnce()).setY(anyInt());
    }

    @Test
    public void testMMPath_Aliens_Invasion() throws Exception {
        when(alienMock.isVisible()).thenReturn(true);
        when(alienMock.getY()).thenReturn(Commons.GROUND + Commons.ALIEN_HEIGHT + 1);

        invokeUpdateAliens();

        verify(alienMock, atLeastOnce()).getY();

        assertEquals("Invasion!", board.getMessage(), "El mensaje debería ser de invasión");
    }

    @Test
    public void testMMPath_Aliens_NormalMove() throws Exception {
        when(alienMock.isVisible()).thenReturn(true);

        when(alienMock.getX()).thenReturn(150);
        when(alienMock.getY()).thenReturn(50);

        invokeUpdateAliens();

        verify(alienMock, times(1)).act(anyInt());


        assertNotEquals("Invasion!", board.getMessage(), "El juego no debería estar en invasión");
    }
}
