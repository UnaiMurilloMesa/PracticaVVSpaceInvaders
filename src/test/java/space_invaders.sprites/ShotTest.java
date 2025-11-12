package space_invaders.sprites;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShotTest {


    // ------------------------------------------------ TESTS CAJA NEGRA ------------------------------------------------


    /**
     * Test para comprobar que no se crea un objeto vacío con el constructor sin parámetros
     */
    @Test
    void testShotIsNotNull() {
        Shot shot = new Shot();

        assertNotNull(shot, "Shot no debería ser null");
    }

    /**
     * Test para verificar que se establece una imagen al crear un objeto Shot
     */
    @Test
    void testImagenShot() {
        int x, y; x = y = 250;
        Shot shot = new Shot(x, y);

        assertNotNull(shot.getImage(), "La imagen de Shot no debería de ser null");
    }

    /**
     * Test para verificar que las coordenadas de un nuevo objeto Shot son las correctas.
     * Como no se especifican restricciones en la documentación, se establecen parámetros dentro de los límites,
     * como fue acordado con el profesor
     */
    @Test
    void testCoordenadasShot() {
        int x, y; x = y = 250;
        Shot shot = new Shot(x, y);

        // Ajustamos los valores como indica la documentación del método initShot()
        int expectedX = x + 6;
        int expectedY = y - 1;

        assertEquals(expectedX, shot.getX(), "La posición de Shot debería ser " + expectedX);
        assertEquals(expectedY, shot.getY(), "La posición de Shot debería ser " + expectedY);
    }


    // ------------------------------------------------ TESTS CAJA BLANCA -----------------------------------------------


    /**
     * Test de cobertura de líneas del Constructor vacío
     */
    @Test
    void testConstructorSinParametrosNoLanzaErrores() {
        assertDoesNotThrow(() -> new Shot());
    }

    @Test
    void testConstructorConParametros() {
        int x, y; x = y = 250;
        Shot shot = new Shot(x, y);

        int expectedX = x + 6;
        int expectedY = y - 1;

        assertNotNull(shot.getImage(), "La imagen de Shot no debería de ser null");
        assertEquals(expectedX, shot.getX(), "La posición de Shot debería ser " + expectedX);
        assertEquals(expectedY, shot.getY(), "La posición de Shot debería ser " + expectedY);
    }

    /**
     * Comprobar el método privado de la clase Shot
     */
    @Test
    void testInitShot() {
        Shot shot = new Shot();
        try {
            // Accedemos al método de la clase Shot
            Method method = Shot.class.getDeclaredMethod("initShot", int.class, int.class);
            method.setAccessible(true);

            int x, y; x = y = 250;
            method.invoke(shot, x, y);

            // Accedemos a los atributos
            int valorx = shot.getX();
            int valory = shot.getY();

            // Ajustamos los valores como indica la documentación del método initShot()
            int expectedX = x + 6;
            int expectedY = y - 1;

            // Comprobamos que el efecto es el deseado
            assertEquals(expectedX, valorx, "La posición de Shot debería ser " + expectedX);
            assertEquals(expectedY, valory, "La posición de Shot debería ser " + expectedY);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
