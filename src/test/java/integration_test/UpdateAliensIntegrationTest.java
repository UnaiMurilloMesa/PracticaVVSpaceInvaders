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


    // TEST 1: INTEGRACIÓN DE COORDINACIÓN (CHOQUE CON BORDE)
    // MM-Path: d1 -> d2(getX) -> d3(Check Borde) -> d4(setY)
    // Objetivo: Verificar que Board coordina al grupo para bajar si uno toca el borde.
    @Test
    public void testMMPath_Aliens_HitRightBorder() throws Exception {
        when(alienMock.getX()).thenReturn(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT + 1);

        invokeUpdateAliens();

        verify(alienMock, atLeastOnce()).setY(anyInt());
    }


    // TEST 2: INTEGRACIÓN DE FIN DE JUEGO (INVASIÓN)
    // MM-Path: d8 -> d9(Visible) -> d10(getY > Ground) -> d11(Invasion!)
    // Objetivo: Verificar que Board detecta cuándo los aliens tocan el suelo.
    @Test
    public void testMMPath_Aliens_Invasion() throws Exception {
        when(alienMock.isVisible()).thenReturn(true);
        when(alienMock.getY()).thenReturn(Commons.GROUND + Commons.ALIEN_HEIGHT + 1);

        invokeUpdateAliens();

        verify(alienMock, atLeastOnce()).getY();

        assertEquals("El mensaje debería ser de invasión", "Invasion!", board.getMessage());
    }

    // TEST 3: INTEGRACIÓN DE MOVIMIENTO NORMAL
    // MM-Path: d8 -> d9(Visible) -> d10(Seguro) -> d12(act)
    // Objetivo: Verificar que Board delega el movimiento individual en cada ciclo.
    // =========================================================================
    @Test
    public void testMMPath_Aliens_NormalMove() throws Exception {
        when(alienMock.isVisible()).thenReturn(true);

        when(alienMock.getX()).thenReturn(150);
        when(alienMock.getY()).thenReturn(50);

        invokeUpdateAliens();

        verify(alienMock, times(1)).act(anyInt());


        assertNotEquals("Invasion!", board.getMessage());
    }
}
