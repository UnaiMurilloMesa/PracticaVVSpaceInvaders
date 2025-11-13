package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AlienBombTest {

    private final int CERO = 0;
    private final int ANCHO = Commons.BOARD_WIDTH;
    private final int ALTO = Commons.BOARD_HEIGHT;

    private final int X_NOMINAL = ANCHO / 2;
    private final int Y_NOMINAL = ALTO / 2;

    // -------------------------------------------- << TESTS CAJA NEGRA >> -------------------------------------------

    // [Función Init de Alien.Bomb]

    // --- Casos de Prueba para la Variable X (Y se mantiene nominal) ---

    /**
     * 1. Prueba Robusta (Fallida): X por debajo del límite inferior.
     * X = min- (-1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = " + (CERO - 1) + " (Robusto) -> X debe ajustarse a " + CERO)
    void testCajaNegra_InitAlienBomb_XDebajoLimiteInferior_DebeAproximarseACero() {
        Alien alien = new Alien(-1, Y_NOMINAL);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(CERO, alienBomb.getX(), "X debe ajustarse al límite inferior " + CERO);
        assertEquals(Y_NOMINAL, alienBomb.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 2. Prueba Límite: X en el límite inferior.
     * X = min (0)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = " + CERO + " (Límite Inf) -> X debe ser " + CERO)
    void testCajaNegra_InitAlienBomb_XEnLimiteInferior_DebeSerCero() {
        Alien alien = new Alien(CERO, Y_NOMINAL);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(CERO, alienBomb.getX(), "X debe ser el límite inferior " + CERO);
        assertEquals(Y_NOMINAL, alienBomb.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 3. Prueba Límite: X justo por encima del límite inferior.
     * X = min+ (1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = " + (CERO + 1) + " (Límite Inf+) -> X debe ser " + (CERO + 1))
    void testCajaNegra_InitAlienBomb_XEncimaLimiteInferior_DebeSerUno() {
        Alien alien = new Alien(CERO + 1, Y_NOMINAL);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(CERO + 1, alienBomb.getX(), "X debe ser el límite inferior más uno " + (CERO + 1));
        assertEquals(Y_NOMINAL, alienBomb.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 4. Prueba Límite: X justo por debajo del límite superior.
     * X = max- (ANCHO - 1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = " + (ANCHO - 1) + " (Límite Sup-) -> X debe ser " + (ANCHO - 1))
    void testCajaNegra_InitAlienBomb_XDebajoLimiteSuperior_DebeSerMaxMenosUno() {
        Alien alien = new Alien(ANCHO - 1, Y_NOMINAL);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(ANCHO - 1, alienBomb.getX(), "X debe ser el límite superior menos uno " + (ANCHO - 1));
        assertEquals(Y_NOMINAL, alienBomb.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 5. Prueba Límite: X en el límite superior.
     * X = max (ANCHO)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = " + ANCHO + " (Límite Sup) -> X debe ser " + ANCHO)
    void testCajaNegra_InitAlienBomb_XEnLimiteSuperior_DebeSerMax() {
        Alien alien = new Alien(ANCHO, Y_NOMINAL);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(ANCHO, alienBomb.getX(), "X debe ser el límite superior " + ANCHO);
        assertEquals(Y_NOMINAL, alienBomb.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 6. Prueba Robusta (Fallida): X por encima del límite superior.
     * X = max+ (ANCHO + 1)
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X = " + (ANCHO + 1) + " (Robusto) -> X debe ajustarse a " + ANCHO)
    void testCajaNegra_InitAlienBomb_XEncimaLimiteSuperior_DebeAproximarseAMax() {
        Alien alien = new Alien(ANCHO + 1, Y_NOMINAL);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(ANCHO, alienBomb.getX(), "X debe ajustarse al límite superior " + ANCHO);
        assertEquals(Y_NOMINAL, alienBomb.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    // --- Casos de Prueba para la Variable Y (X se mantiene nominal) ---

    /**
     * 7. Prueba Robusta (Fallida): Y por debajo del límite inferior.
     * X = nominal
     * Y = min- (-1)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = " + (CERO - 1) + " (Robusto) -> Y debe ajustarse a " + CERO)
    void testCajaNegra_InitAlienBomb_YDebajoLimiteInferior_DebeAproximarseACero() {
        Alien alien = new Alien(X_NOMINAL, -1);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(X_NOMINAL, alienBomb.getX(), "X debe mantener su valor nominal");
        assertEquals(CERO, alienBomb.getY(), "Y debe ajustarse al límite inferior " + CERO);
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 8. Prueba Límite: Y en el límite inferior.
     * X = nominal
     * Y = min (0)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = " + CERO + " (Límite Inf) -> Y debe ser " + CERO)
    void testCajaNegra_InitAlienBomb_YEnLimiteInferior_DebeSerCero() {
        Alien alien = new Alien(X_NOMINAL, CERO);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(X_NOMINAL, alienBomb.getX(), "X debe mantener su valor nominal");
        assertEquals(CERO, alienBomb.getY(), "Y debe ser el límite inferior " + CERO);
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 9. Prueba Límite: Y justo por encima del límite inferior.
     * X = nominal
     * Y = min+ (1)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = " + (CERO + 1) + " (Límite Inf+) -> Y debe ser " + (CERO + 1))
    void testCajaNegra_InitAlienBomb_YEncimaLimiteInferior_DebeSerUno() {
        Alien alien = new Alien(X_NOMINAL, CERO + 1);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(X_NOMINAL, alienBomb.getX(), "X debe mantener su valor nominal");
        assertEquals(CERO + 1, alienBomb.getY(), "Y debe ser el límite inferior más uno " + (CERO + 1));
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 10. Prueba Límite: Y justo por debajo del límite superior.
     * X = nominal
     * Y = max- (ALTO - 1)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = " + (ALTO - 1) + " (Límite Sup-) -> Y debe ser " + (ALTO - 1))
    void testCajaNegra_InitAlienBomb_YDebajoLimiteSuperior_DebeSerMaxMenosUno() {
        Alien alien = new Alien(X_NOMINAL, ALTO - 1);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(X_NOMINAL, alienBomb.getX(), "X debe mantener su valor nominal");
        assertEquals(ALTO - 1, alienBomb.getY(), "Y debe ser el límite superior menos uno " + (ALTO - 1));
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 11. Prueba Límite: Y en el límite superior.
     * X = nominal
     * Y = max (ALTO)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = " + ALTO + " (Límite Sup) -> Y debe ser " + ALTO)
    void testCajaNegra_InitAlienBomb_YEnLimiteSuperior_DebeSerMax() {
        Alien alien = new Alien(X_NOMINAL, ALTO);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(X_NOMINAL, alienBomb.getX(), "X debe mantener su valor nominal");
        assertEquals(ALTO, alienBomb.getY(), "Y debe ser el límite superior " + ALTO);
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    /**
     * 12. Prueba Robusta (Fallida): Y por encima del límite superior.
     * X = nominal
     * Y = max+ (ALTO + 1)
     */
    @Test
    @DisplayName("[Caja Negra - Init] Y = " + (ALTO + 1) + " (Robusto) -> Y debe ajustarse a " + ALTO)
    void testCajaNegra_InitAlienBomb_YEncimaLimiteSuperior_DebeAproximarseAMax() {
        Alien alien = new Alien(X_NOMINAL, ALTO + 1);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(X_NOMINAL, alienBomb.getX(), "X debe mantener su valor nominal");
        assertEquals(ALTO, alienBomb.getY(), "Y debe ajustarse al límite superior " + ALTO);
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    // --- Caso de Prueba Central (El "+1" en 6n+1) ---

    /**
     * 13. Prueba Nominal: Ambas variables en su valor nominal (central).
     * X = nominal
     * Y = nominal
     */
    @Test
    @DisplayName("[Caja Negra - Init] X,Y Nominales (Central) -> Deben ser X,Y")
    void testCajaNegra_InitAlienBomb_XYNominal_DebeSerXY() {
        Alien alien = new Alien(X_NOMINAL, Y_NOMINAL);
        Alien.Bomb alienBomb = alien.new Bomb(alien.getX(), alien.getY());
        assertEquals(X_NOMINAL, alienBomb.getX(), "X debe mantener su valor nominal");
        assertEquals(Y_NOMINAL, alienBomb.getY(), "Y debe mantener su valor nominal");
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    // -------------------------------------------- << TESTS CAJA BLANCA >> -------------------------------------------

    // [Función Init de Alien.Bomb]

    @Test
    @DisplayName("[Caja Blanca - Init] Camino 1 -> X=" + X_NOMINAL + ", Y=" + (ALTO + 1))
    void testCajaBlanca_InitAlienBomb_Camino1() {
        Alien alien = new Alien(0, 0);
        Alien.Bomb alienBomb = alien.new Bomb(X_NOMINAL, (ALTO + 1));
        assertEquals(ANCHO, alienBomb.getX(), "X se ajusta al limite superior por el else: " + ANCHO);
        assertEquals(ALTO, alienBomb.getY(), "Y se ajusta al limite superior por el else: " + ALTO);
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    @Test
    @DisplayName("[Caja Blanca - Init] Camino 2 -> X=" + X_NOMINAL + ", Y=" + Y_NOMINAL)
    void testCajaBlanca_InitAlienBomb_Camino2() {
        Alien alien = new Alien(0, 0);
        Alien.Bomb alienBomb = alien.new Bomb(X_NOMINAL, Y_NOMINAL);
        assertEquals(X_NOMINAL, alienBomb.getX(), "X se suman los valores de " + alien.getX() + X_NOMINAL);
        assertEquals(Y_NOMINAL, alienBomb.getY(), "Y se suman los valores de " + alien.getY() + Y_NOMINAL);
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

    @Test
    @DisplayName("[Caja Blanca - Init] Camino 3 -> X=" + (ANCHO + 1) + ", Y=" + (ALTO + 1))
    void testCajaBlanca_InitAlienBomb_Camino3() {
        Alien alien = new Alien(0, 0);
        Alien.Bomb alienBomb = alien.new Bomb((ANCHO + 1), (ALTO + 1));
        assertEquals(ANCHO, alienBomb.getX(), "X se ajusta al limite superior por el else: " + ANCHO);
        assertEquals(ALTO, alienBomb.getY(), "Y se ajusta al limite superior por el else: " + ALTO);
        assertNotNull(alienBomb.getImage(), "La imagen no debe ser nula");
    }

}
