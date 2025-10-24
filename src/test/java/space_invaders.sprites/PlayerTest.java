package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    //tests para Player() y initPlayer()
    @Test
    public void testConstructor_InitialState_Visibility() {
        assertTrue(player.isVisible(), "El jugador debería estar visible al iniciar");
    }

    //player se inicializa desviado del centro hacia la derecha
    @Test
    public void testConstructor_InitialState_PositionX() {
        assertEquals(Commons.BOARD_WIDTH / 2, player.getX(), "La posición X debe inicializarse en el centro del tablero");
    }

    @Test
    public void testConstructor_InitialState_PositionY() {
        assertEquals(Commons.GROUND - 10, player.getY(), "La posición Y debe estar a 10 píxeles por encima del suelo");
    }

    //tests para act()
    @Test
    public void testAct_NoMovementWhenDxZero() {
        int posXBefore = player.getX();

        assertEquals(0, player.dx);
        player.act();

        assertEquals(posXBefore, player.getX(), "El jugador no debe moverse si dx = 0");
    }

    @Test
    public void testAct_MoveRight() {
        int posXBefore = player.getX();

        player.dx = 2;

        player.act();

        assertEquals(posXBefore + 2, player.getX(), "El jugador debe moverse 2 píxeles a la derecha");
    }

    @Test
    public void testAct_MoveLeft() {
        int posXBefore = player.getX();

        player.dx = -2;

        player.act();

        assertEquals(posXBefore - 2, player.getX(), "El jugador debe moverse 2 píxeles a la izquierda");
    }

    //el jugador se sale por el borde izquierdo
    @Test
    public void testAct_LeftBoundaryLimit() {
        player.setX(Commons.BORDER_LEFT);
        player.dx = -3;

        player.act();

        assertEquals(Commons.BORDER_LEFT, player.getX(), "El jugador no debe pasar el borde izquierdo");
    }

    @Test
    public void testAct_RightBoundaryLimit() {
        int rightLimit = Commons.BOARD_WIDTH - Commons.PLAYER_WIDTH - Commons.BORDER_RIGHT;

        player.setX(rightLimit);

        player.act();

        assertEquals(rightLimit, player.getX(),
                "El jugador no debe pasar el borde derecho");
    }
}
