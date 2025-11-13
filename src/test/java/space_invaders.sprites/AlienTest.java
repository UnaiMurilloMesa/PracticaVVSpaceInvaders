package space_invaders.sprites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import main.Commons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlienTest {

    private final int CERO = 0;
    private final int ANCHO = Commons.BOARD_WIDTH;
    private final int ALTO = Commons.BOARD_HEIGHT;

    private final int X_NOMINAL = ANCHO / 2;
    private final int Y_NOMINAL = ALTO / 2;

    // -------------------------------------------- << TESTS CAJA NEGRA >> -------------------------------------------

    // [Función Init de Alien]

    // --- Casos de Prueba para la Variable X (Y se mantiene nominal) ---

    /**
     * 1. Prueba Robusta (Fallida): X por debajo del límite inferior.
     * X = min- (-1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X < 0 (Robusto) -> X debe ajustarse a " + CERO)
    void testCajaNegra_InitAlien_XDebajoLimiteInferior_DebeAproximarseACero() {
        Alien alien = new Alien(-1, Y_NOMINAL);
        assertEquals(CERO, alien.getX(), "X debe ajustarse al límite inferior " + CERO);
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 2. Prueba Límite: X en el límite inferior.
     * X = min (0)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = 0 (Límite Inf) -> X debe ser " + CERO)
    void testCajaNegra_InitAlien_XEnLimiteInferior_DebeSerCero() {
        Alien alien = new Alien(CERO, Y_NOMINAL);
        assertEquals(CERO, alien.getX(), "X debe ser el límite inferior " + CERO);
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 3. Prueba Límite: X justo por encima del límite inferior.
     * X = min+ (1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = 1 (Límite Inf+) -> X debe ser " + (CERO + 1))
    void testCajaNegra_InitAlien_XEncimaLimiteInferior_DebeSerUno() {
        Alien alien = new Alien(CERO + 1, Y_NOMINAL);
        assertEquals(CERO + 1, alien.getX(), "X debe ser el límite inferior más uno " + (CERO + 1));
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 4. Prueba Límite: X justo por debajo del límite superior.
     * X = max- (ANCHO - 1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = MAX-1 (Límite Sup-) -> X debe ser " + (ANCHO - 1))
    void testCajaNegra_InitAlien_XDebajoLimiteSuperior_DebeSerMaxMenosUno() {
        Alien alien = new Alien(ANCHO - 1, Y_NOMINAL);
        assertEquals(ANCHO - 1, alien.getX(), "X debe ser el límite superior menos uno " + (ANCHO - 1));
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 5. Prueba Límite: X en el límite superior.
     * X = max (ANCHO)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = MAX (Límite Sup) -> X debe ser " + ANCHO)
    void testCajaNegra_InitAlien_XEnLimiteSuperior_DebeSerMax() {
        Alien alien = new Alien(ANCHO, Y_NOMINAL);
        assertEquals(ANCHO, alien.getX(), "X debe ser el límite superior " + ANCHO);
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 6. Prueba Robusta (Fallida): X por encima del límite superior.
     * X = max+ (ANCHO + 1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X > MAX (Robusto) -> X debe ajustarse a " + ANCHO)
    void testCajaNegra_InitAlien_XEncimaLimiteSuperior_DebeAproximarseAMax() {
        Alien alien = new Alien(ANCHO + 1, Y_NOMINAL);
        assertEquals(ANCHO, alien.getX(), "X debe ajustarse al límite superior " + ANCHO);
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    // --- Casos de Prueba para la Variable Y (X se mantiene nominal) ---

    /**
     * 7. Prueba Robusta (Fallida): Y por debajo del límite inferior.
     * X = nominal
     * Y = min- (-1)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y < 0 (Robusto) -> Y debe ajustarse a " + CERO)
    void testCajaNegra_InitAlien_YDebajoLimiteInferior_DebeAproximarseACero() {
        Alien alien = new Alien(X_NOMINAL, -1);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(CERO, alien.getY(), "Y debe ajustarse al límite inferior " + CERO);
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 8. Prueba Límite: Y en el límite inferior.
     * X = nominal
     * Y = min (0)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = 0 (Límite Inf) -> Y debe ser " + CERO)
    void testCajaNegra_InitAlien_YEnLimiteInferior_DebeSerCero() {
        Alien alien = new Alien(X_NOMINAL, CERO);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(CERO, alien.getY(), "Y debe ser el límite inferior " + CERO);
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 9. Prueba Límite: Y justo por encima del límite inferior.
     * X = nominal
     * Y = min+ (1)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = 1 (Límite Inf+) -> Y debe ser " + (CERO + 1))
    void testCajaNegra_InitAlien_YEncimaLimiteInferior_DebeSerUno() {
        Alien alien = new Alien(X_NOMINAL, CERO + 1);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(CERO + 1, alien.getY(), "Y debe ser el límite inferior más uno " + (CERO + 1));
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 10. Prueba Límite: Y justo por debajo del límite superior.
     * X = nominal
     * Y = max- (ALTO - 1)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = MAX-1 (Límite Sup-) -> Y debe ser " + (ALTO - 1))
    void testCajaNegra_InitAlien_YDebajoLimiteSuperior_DebeSerMaxMenosUno() {
        Alien alien = new Alien(X_NOMINAL, ALTO - 1);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(ALTO - 1, alien.getY(), "Y debe ser el límite superior menos uno " + (ALTO - 1));
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 11. Prueba Límite: Y en el límite superior.
     * X = nominal
     * Y = max (ALTO)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = MAX (Límite Sup) -> Y debe ser " + ALTO)
    void testCajaNegra_InitAlien_YEnLimiteSuperior_DebeSerMax() {
        Alien alien = new Alien(X_NOMINAL, ALTO);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(ALTO, alien.getY(), "Y debe ser el límite superior " + ALTO);
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    /**
     * 12. Prueba Robusta (Fallida): Y por encima del límite superior.
     * X = nominal
     * Y = max+ (ALTO + 1)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y > MAX (Robusto) -> Y debe ajustarse a " + ALTO)
    void testCajaNegra_InitAlien_YEncimaLimiteSuperior_DebeAproximarseAMax() {
        Alien alien = new Alien(X_NOMINAL, ALTO + 1);
        assertEquals(X_NOMINAL, alien.getX(), "X debe mantener su valor nominal");
        assertEquals(ALTO, alien.getY(), "Y debe ajustarse al límite superior " + ALTO);
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    // --- Caso de Prueba Central (El "+1" en 6n+1) ---

    /**
     * 13. Prueba Nominal: Ambas variables en su valor nominal (central).
     * X = nominal
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X,Y Nominales (Central) -> Deben ser X,Y")
    void testCajaNegra_InitAlien_XYNominal_DebeSerXY() {
        Alien alien = new Alien(X_NOMINAL, Y_NOMINAL);
        assertEquals(X_NOMINAL, alien.getX());
        assertEquals(Y_NOMINAL, alien.getY());
    }

    // [Función Act de Alien]

    /**
     * 1. [Límite Inferior] - Si el movimiento sobrepasa el límite inferior, se ajusta.
     */
    @Test
    @DisplayName("[Caja Negra - Act] Límite inferior robusto -> Izquierda (0 - 2 -> 0)")
    void testCajaNegra_AlienAct_LimiteInferiorRobusto_MovimientoIzquierda() {
        Alien alien = new Alien(CERO, 0);
        alien.act(-2); // Intentar mover a -2
        assertEquals(CERO, alien.getX(), "X debe ser " + CERO);
    }

    /**
     * 2. [Límite Inferior] - Movimiento dentro del límite desde el límite inferior.
     */
    @Test
    @DisplayName("[Caja Negra - Act] Límite inferior -> Derecha (0 + 2 -> +2)")
    void testCajaNegra_AlienAct_LimiteInferior_MovimientoDerecha() {
        Alien alien = new Alien(CERO, 0);
        alien.act(+2); // Intentar mover a +2
        assertEquals(CERO, alien.getX(), "X debe ser " + (CERO + 2));
    }

    /**
     * 3. [Nóminal] - Sin movimiento desde posición nóminal.
     */
    @Test
    @DisplayName("[Caja Negra - Act] Nóminal -> Sin movimiento (X_NOMINAL + 0 -> X_NOMINAL)")
    void testCajaNegra_AlienAct_Nominal_SinMovimiento() {
        Alien alien = new Alien(X_NOMINAL, 0);
        alien.act(0); // Sin movimiento
        assertEquals(X_NOMINAL, alien.getX(), "X debe ser " + X_NOMINAL);
    }

    /**
     * 4. [Límite Superior] - Movimiento dentro del límite desde el límite superior.
     */
    @Test
    @DisplayName("[Caja Negra - Act] Límite superior -> Izquierda (" + ANCHO + " - 2 -> " + (ANCHO - 2) + ")")
    void testCajaNegra_AlienAct_LimiteSuperior_MovimientoIzquierda() {
        Alien alien = new Alien(ANCHO, 0);
        alien.act(-2); // Intentar mover a ANCHO - 2
        assertEquals(ANCHO - 2, alien.getX(), "X debe ser " + (ANCHO - 2));
    }

    /**
     * 5. [Límite Superior] - Si el movimiento sobrepasa el límite superior, se ajusta.
     */
    @Test
    @DisplayName("[Caja Negra - Act] Límite superior robusto (" + ANCHO + " + 2 -> " + ANCHO + ")")
    void testCajaNegra_AlienAct_LimiteSuperiorRobusto_MovimientoDerecha() {
        Alien alien = new Alien(ANCHO, 0);
        alien.act(+2); // Intentar mover a ANCHO + 2
        assertEquals(ANCHO, alien.getX(), "X debe ser " + ANCHO);
    }

    // -------------------------------------------- << TESTS CAJA BLANCA >> -------------------------------------------

    // [Función Init de Alien]

    @Test
    @DisplayName("[Caja Blanca - Init] Camino 1 -> " + CERO + "<X<" + ANCHO + ", Y=" + (CERO - 1))
    void testCajaBlanca_InitAlien_Camino1() {
        Alien alien = new Alien(X_NOMINAL, CERO - 1);

        assertEquals(CERO, alien.getX(), "X al no instanciarse con this.x debe ser " + CERO);
        assertEquals(CERO, alien.getY(), "Y debe ajustarse al límite inferior " + CERO);
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    @Test
    @DisplayName("[Caja Blanca - Init] Camino 2 -> " + CERO + "<X<" + ANCHO + ", " + CERO + "<Y<" + ALTO)
    void testCajaBlanca_InitAlien_Camino2() {
        Alien alien = new Alien(X_NOMINAL, Y_NOMINAL);

        assertEquals(X_NOMINAL, alien.getX(), "X debe ser " + X_NOMINAL + " que son los parámetros pasados");
        assertEquals(Y_NOMINAL, alien.getY(), "Y debe ser " + Y_NOMINAL + " que son los parámetros pasados");
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    @Test
    @DisplayName("[Caja Blanca - Init] Camino 3 -> X=" + (ANCHO + 1) + ", Y=-1")
    void testCajaBlanca_InitAlien_Camino3() {
        Alien alien = new Alien(ANCHO + 1, -1);

        assertEquals(ANCHO, alien.getX(), "X al pasarse del límite superior debe ser " + ANCHO);
        assertEquals(CERO, alien.getY(), "Y al pasarse del límite inferior debe ser " + CERO);
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    @Test
    @DisplayName("[Caja Blanca - Init] Camino 4 -> X=" + (CERO - 1) + ", Y=-1")
    void testCajaBlanca_InitAlien_Camino4() {
        Alien alien = new Alien(CERO - 1, CERO - 1);

        assertEquals(CERO, alien.getX(), "X al pasarse del límite inferior debe ser " + CERO);
        assertEquals(CERO, alien.getY(), "Y al pasarse del límite inferior debe ser " + CERO);
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    @Test
    @DisplayName("[Caja Blanca - Init] Camino 5 -> X=" + X_NOMINAL + ", Y=" + ALTO + 1)
    void testCajaBlanca_InitAlien_Camino5() {
        Alien alien = new Alien(X_NOMINAL, (ALTO + 1));

        assertEquals(X_NOMINAL, alien.getX(), "X debe ser " + X_NOMINAL);
        assertEquals((ALTO + 1), alien.getY(), "Y deberá ser " + (ALTO + 1) + " ya que al ser > " + CERO + " se establece el pasado por parámetro");
        assertNotNull(alien.getBomb(), "El Alien.Bomb debe existir");
        assertNotNull(alien.getImage(), "La imagen del Alien debe existir");
    }

    // [Función Act de Alien]

    @Test
    @DisplayName("[Caja Blanca - Act] Camino 1 -> X inicial=" + (X_NOMINAL + 2) + ", act(-2)")
    void testCajaBlanca_AlienAct_Camino1() {
        Alien alien = new Alien((X_NOMINAL + 2), Y_NOMINAL);
        alien.act(+2); // Porque resta -2 el act()

        assertEquals(X_NOMINAL, alien.getX(), "X debe ser " + X_NOMINAL);
    }

}