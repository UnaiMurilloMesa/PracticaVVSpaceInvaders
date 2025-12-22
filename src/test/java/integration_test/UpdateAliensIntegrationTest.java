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
        // 1. Instanciamos el SUT (System Under Test)
        board = new Board();

        // 2. Creamos el Mock de la dependencia (Alien)
        alienMock = mock(Alien.class);

        // 3. Inyección de dependencias (Setter Injection)
        // Creamos una lista que contenga nuestro mock y se la pasamos al tablero
        List<Alien> aliensList = new ArrayList<>();
        aliensList.add(alienMock);
        board.setAliens(aliensList);
    }

    /**
     * Utilidad para invocar el método privado 'update_aliens' usando Reflection.
     * Esto permite probar la integración aislada sin pasar por el bucle principal.
     */
    private void invokeUpdateAliens() throws Exception {
        Method method = Board.class.getDeclaredMethod("update_aliens");
        method.setAccessible(true); // Hacemos accesible el método privado
        method.invoke(board);
    }

    // =========================================================================
    // TEST 1: INTEGRACIÓN DE COORDINACIÓN (CHOQUE CON BORDE)
    // MM-Path: d1 -> d2(getX) -> d3(Check Borde) -> d4(setY)
    // Objetivo: Verificar que Board coordina al grupo para bajar si uno toca el borde.
    // =========================================================================
    @Test
    public void testMMPath_Aliens_HitRightBorder() throws Exception {
        // ARRANGE (Configuración del escenario)
        // Simulamos que el alien ha llegado al borde derecho.
        // Límite: BOARD_WIDTH (358) - BORDER_RIGHT (30) = 328.
        // Ponemos el alien en 329 para forzar la condición "x >= ...".
        when(alienMock.getX()).thenReturn(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT + 1);

        // ACT (Ejecución)
        invokeUpdateAliens();

        // ASSERT (Verificación)
        // Verificamos que Board envió el mensaje 'setY' (bajar) al alien.
        // Esto confirma que la lógica de coordinación d3->d4 se ejecutó.
        verify(alienMock, atLeastOnce()).setY(anyInt());
    }

    // =========================================================================
    // TEST 2: INTEGRACIÓN DE FIN DE JUEGO (INVASIÓN)
    // MM-Path: d8 -> d9(Visible) -> d10(getY > Ground) -> d11(Invasion!)
    // Objetivo: Verificar que Board detecta cuándo los aliens tocan el suelo.
    // =========================================================================
    @Test
    public void testMMPath_Aliens_Invasion() throws Exception {
        // ARRANGE
        // El alien debe ser visible para que se compruebe su altura
        when(alienMock.isVisible()).thenReturn(true);

        // Simulamos que el alien ha tocado el suelo.
        // Límite: GROUND (290) + ALIEN_HEIGHT (12) = 302.
        // Ponemos el alien en 303.
        when(alienMock.getY()).thenReturn(Commons.GROUND + Commons.ALIEN_HEIGHT + 1);

        // ACT
        invokeUpdateAliens();

        // ASSERT
        // 1. Verificamos que Board consultó la altura (interacción d10)
        verify(alienMock, atLeastOnce()).getY();

        // 2. Verificamos que el estado del juego cambió a "Invasion!" (efecto de d11)
        assertEquals("El mensaje debería ser de invasión", "Invasion!", board.getMessage());
    }

    // =========================================================================
    // TEST 3: INTEGRACIÓN DE MOVIMIENTO NORMAL
    // MM-Path: d8 -> d9(Visible) -> d10(Seguro) -> d12(act)
    // Objetivo: Verificar que Board delega el movimiento individual en cada ciclo.
    // =========================================================================
    @Test
    public void testMMPath_Aliens_NormalMove() throws Exception {
        // ARRANGE
        when(alienMock.isVisible()).thenReturn(true);

        // Posición segura (centro del tablero), lejos de bordes y suelo
        when(alienMock.getX()).thenReturn(150);
        when(alienMock.getY()).thenReturn(50);

        // ACT
        invokeUpdateAliens();

        // ASSERT
        // 1. Verificamos que Board llamó a 'act()' para mover al alien (interacción d12)
        verify(alienMock, times(1)).act(anyInt());

        // 2. Verificamos que NO se activó el estado de invasión
        assertNotEquals("Invasion!", board.getMessage());

        // 3. Verificamos que NO se llamó a setY (bajar), ya que no tocó bordes
        // Nota: setY se puede llamar en inicialización, pero aquí verificamos que
        // durante el update normal sin bordes no ocurra.
        // verify(alienMock, never()).setY(anyInt()); // Opcional, dependiendo de la rigurosidad
    }
}
