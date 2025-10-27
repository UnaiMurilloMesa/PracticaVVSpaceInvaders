package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    //tests para los límites izquierdo en act()
    @ParameterizedTest
    @CsvSource({
            "7, -2, " + Commons.BORDER_LEFT,
            "5, -2, " + Commons.BORDER_LEFT,
            "3, -2, " + Commons.BORDER_LEFT
    })
    void testAct_LeftBoundary(int initialX, int dx, int expectedX) {
        player.setX(initialX);
        player.dx = dx;

        player.act();

        assertEquals(expectedX, player.getX(), "El jugador no debe pasar el borde izquierdo");
    }


    //tests para los límites derecho en act()
    @ParameterizedTest
    @CsvSource({
            "312, 2",
            "313, 2",
            "314, 2"
    })
    void testAct_RightBoundary(int initialX, int dx) {
        int rightLimit = Commons.BOARD_WIDTH - Commons.PLAYER_WIDTH - Commons.BORDER_RIGHT;

        player.setX(initialX);
        player.dx = dx;

        player.act();

        assertEquals(rightLimit, player.getX(), "El jugador no debe pasar el borde derecho");
    }


}
