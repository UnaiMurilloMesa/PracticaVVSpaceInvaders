package space_invaders.sprites;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.Commons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlienTest {

    private final int CERO = 0;
    private final int ANCHO = Commons.BOARD_WIDTH;
    private final int ALTO = Commons.BOARD_HEIGHT;

    private final int X_NOMINAL = ANCHO / 2;
    private final int Y_NOMINAL = ALTO / 2;

    // [Función Init de Alien]

    // --- Casos de Prueba para la Variable X (Y se mantiene nominal) ---

    /**
     * 1. Prueba Robusta (Fallida): X por debajo del límite inferior.
     * X = min- (-1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Init] X < 0 (Robusto) -> X debe ajustarse a " + CERO)
    void testInitAlien_XDebajoLimiteInferior_DebeAproximarseACero() {
        Alien alien = new Alien(-1, Y_NOMINAL);
        assertEquals(CERO, alien.getX(), "X debe ajustarse al límite inferior " + CERO);
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
    }

    /**
     * 2. Prueba Límite: X en el límite inferior.
     * X = min (0)
     * Y = nominal
     */
    @Test
    @DisplayName("[Init] X = 0 (Límite Inf) -> X debe ser " + CERO)
    void testInitAlien_XEnLimiteInferior_DebeSerCero() {
        Alien alien = new Alien(CERO, Y_NOMINAL);
        assertEquals(CERO, alien.getX(), "X debe ser el límite inferior " + CERO);
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
    }

    /**
     * 3. Prueba Límite: X justo por encima del límite inferior.
     * X = min+ (1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Init] X = 1 (Límite Inf+) -> X debe ser " + (CERO + 1))
    void testInitAlien_XEncimaLimiteInferior_DebeSerUno() {
        Alien alien = new Alien(CERO + 1, Y_NOMINAL);
        assertEquals(CERO + 1, alien.getX(), "X debe ser el límite inferior más uno " + (CERO + 1));
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
    }

    /**
     * 4. Prueba Límite: X justo por debajo del límite superior.
     * X = max- (ANCHO - 1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Init] X = MAX-1 (Límite Sup-) -> X debe ser " + (ANCHO - 1))
    void testInitAlien_XDebajoLimiteSuperior_DebeSerMaxMenosUno() {
        Alien alien = new Alien(ANCHO - 1, Y_NOMINAL);
        assertEquals(ANCHO - 1, alien.getX(), "X debe ser el límite superior menos uno " + (ANCHO - 1));
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
    }

    /**
     * 5. Prueba Límite: X en el límite superior.
     * X = max (ANCHO)
     * Y = nominal
     */
    @Test
    @DisplayName("[Init] X = MAX (Límite Sup) -> X debe ser " + ANCHO)
    void testInitAlien_XEnLimiteSuperior_DebeSerMax() {
        Alien alien = new Alien(ANCHO, Y_NOMINAL);
        assertEquals(ANCHO, alien.getX(), "X debe ser el límite superior " + ANCHO);
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
    }

    /**
     * 6. Prueba Robusta (Fallida): X por encima del límite superior.
     * X = max+ (ANCHO + 1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Init] X > MAX (Robusto) -> X debe ajustarse a " + ANCHO)
    void testInitAlien_XEncimaLimiteSuperior_DebeAproximarseAMax() {
        Alien alien = new Alien(ANCHO + 1, Y_NOMINAL);
        assertEquals(ANCHO, alien.getX(), "X debe ajustarse al límite superior " + ANCHO);
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
    }

    // --- Casos de Prueba para la Variable Y (X se mantiene nominal) ---

    /**
     * 7. Prueba Robusta (Fallida): Y por debajo del límite inferior.
     * X = nominal
     * Y = min- (-1)
     */
    @Test
    @DisplayName("[Init] Y < 0 (Robusto) -> Y debe ajustarse a " + CERO)
    void testInitAlien_YDebajoLimiteInferior_DebeAproximarseACero() {
        Alien alien = new Alien(X_NOMINAL, -1);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(CERO, alien.getY(), "Y debe ajustarse al límite inferior " + CERO);
    }

    /**
     * 8. Prueba Límite: Y en el límite inferior.
     * X = nominal
     * Y = min (0)
     */
    @Test
    @DisplayName("[Init] Y = 0 (Límite Inf) -> Y debe ser " + CERO)
    void testInitAlien_YEnLimiteInferior_DebeSerCero() {
        Alien alien = new Alien(X_NOMINAL, CERO);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(CERO, alien.getY(), "Y debe ser el límite inferior " + CERO);
    }

    /**
     * 9. Prueba Límite: Y justo por encima del límite inferior.
     * X = nominal
     * Y = min+ (1)
     */
    @Test
    @DisplayName("[Init] Y = 1 (Límite Inf+) -> Y debe ser " + (CERO + 1))
    void testInitAlien_YEncimaLimiteInferior_DebeSerUno() {
        Alien alien = new Alien(X_NOMINAL, CERO + 1);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(CERO + 1, alien.getY(), "Y debe ser el límite inferior más uno " + (CERO + 1));
    }

    /**
     * 10. Prueba Límite: Y justo por debajo del límite superior.
     * X = nominal
     * Y = max- (ALTO - 1)
     */
    @Test
    @DisplayName("[Init] Y = MAX-1 (Límite Sup-) -> Y debe ser " + (ALTO - 1))
    void testInitAlien_YDebajoLimiteSuperior_DebeSerMaxMenosUno() {
        Alien alien = new Alien(X_NOMINAL, ALTO - 1);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(ALTO - 1, alien.getY(), "Y debe ser el límite superior menos uno " + (ALTO - 1));
    }

    /**
     * 11. Prueba Límite: Y en el límite superior.
     * X = nominal
     * Y = max (ALTO)
     */
    @Test
    @DisplayName("[Init] Y = MAX (Límite Sup) -> Y debe ser " + ALTO)
    void testInitAlien_YEnLimiteSuperior_DebeSerMax() {
        Alien alien = new Alien(X_NOMINAL, ALTO);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(ALTO, alien.getY(), "Y debe ser el límite superior " + ALTO);
    }

    /**
     * 12. Prueba Robusta (Fallida): Y por encima del límite superior.
     * X = nominal
     * Y = max+ (ALTO + 1)
     */
    @Test
    @DisplayName("[Init] Y > MAX (Robusto) -> Y debe ajustarse a " + ALTO)
    void testInitAlien_YEncimaLimiteSuperior_DebeAproximarseAMax() {
        Alien alien = new Alien(X_NOMINAL, ALTO + 1);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(ALTO, alien.getY(), "Y debe ajustarse al límite superior " + ALTO);
    }

    // --- Caso de Prueba Central (El "+1" en 6n+1) ---

    /**
     * 13. Prueba Nominal: Ambas variables en su valor nominal (central).
     * X = nominal
     * Y = nominal
     */
    @Test
    @DisplayName("[Init] X,Y Nominales (Central) -> Deben ser X,Y")
    void testInitAlien_XYNominal_DebeSerXY() {
        Alien alien = new Alien(X_NOMINAL, Y_NOMINAL);
        assertEquals(X_NOMINAL, alien.getX());
        assertEquals(Y_NOMINAL, alien.getY());
    }

    // [Función Act de Alien]

    /**
     * 1. [Límite Inferior] - Si el movimiento sobrepasa el límite inferior, se ajusta.
     */
    @Test
    @DisplayName("[Act] Límite inferior robusto -> Izquierda (0 - 2 -> 0)")
    void testAlienAct_LimiteInferiorRobusto_MovimientoIzquierda() {
        Alien alien = new Alien(CERO, 0);
        alien.act(-2); // Intentar mover a -2
        assertEquals(CERO, alien.getX(), "X debe ser " + CERO);
    }

    /**
     * 2. [Límite Inferior] - Movimiento dentro del límite desde el límite inferior.
     */
    @Test
    @DisplayName("[Act] Límite inferior -> Derecha (0 + 2 -> +2)")
    void testAlienAct_LimiteInferior_MovimientoDerecha() {
        Alien alien = new Alien(CERO, 0);
        alien.act(+2); // Intentar mover a +2
        assertEquals(CERO, alien.getX(), "X debe ser " + (CERO + 2));
    }

    /**
     * 3. [Nóminal] - Sin movimiento desde posición nóminal.
     */
    @Test
    @DisplayName("[Act] Nóminal -> Sin movimiento (X_NOMINAL + 0 -> X_NOMINAL)")
    void testAlienAct_Nominal_SinMovimiento() {
        Alien alien = new Alien(X_NOMINAL, 0);
        alien.act(0); // Sin movimiento
        assertEquals(X_NOMINAL, alien.getX(), "X debe ser " + X_NOMINAL);
    }

    /**
     * 4. [Límite Superior] - Movimiento dentro del límite desde el límite superior.
     */
    @Test
    @DisplayName("[Act] Límite superior -> Izquierda (" + ANCHO + " - 2 -> " + (ANCHO - 2) + ")")
    void testAlienAct_LimiteSuperior_MovimientoIzquierda() {
        Alien alien = new Alien(ANCHO, 0);
        alien.act(-2); // Intentar mover a ANCHO - 2
        assertEquals(ANCHO - 2, alien.getX(), "X debe ser " + (ANCHO - 2));
    }

    /**
     * 5. [Límite Superior] - Si el movimiento sobrepasa el límite superior, se ajusta.
     */
    @Test
    @DisplayName("[Act] Límite superior robusto (" + ANCHO + " + 2 -> " + ANCHO + ")")
    void testAlienAct_LimiteSuperiorRobusto_MovimientoDerecha() {
        Alien alien = new Alien(ANCHO, 0);
        alien.act(+2); // Intentar mover a ANCHO + 2
        assertEquals(ANCHO, alien.getX(), "X debe ser " + ANCHO);
    }
}