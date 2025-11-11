package space_invaders.sprites;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShotTest {

    /**
     * Test para comprobar que no se crea un objeto vacío
     */
    @Test
    void testShotIsNotNull() {
        Shot shot = new Shot();

        assertNotNull(shot, "Shot no debería ser null");
    }

    /**
     * Test para verificar que las coordenadas de un nuevo objeto Shot son las correctas.
     * Como no se especifican restricciones en la documentación, se establecen parámetros dentro de los límites,
     * como fue acordado con el profesor
     * También se detecta si la imagen es null o no
     */
    @Test
    void testShotConstructor() {
        int x, y; x = y = 250;
        Shot shot = new Shot(x, y);

        // Ajustamos los valores como indica la documentación del método initShot()
        int expectedX = x + 6;
        int expectedY = y - 1;

        assertNotNull(shot.getImage(), "La imagen de Shot no debería de ser null");
        assertEquals(expectedX, shot.getX(), "La posición de Shot debería ser " + expectedX);
        assertEquals(expectedY, shot.getY(), "La posición de Shot debería ser " + expectedY);
    }

    /**
     * Comprobar el método primado de la clase Shot
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
            Field valx = Sprite.class.getDeclaredField("x");
            valx.setAccessible(true);
            int valorx = (int) valx.get(shot);

            Field valy = Sprite.class.getDeclaredField("y");
            valy.setAccessible(true);
            int valory = (int) valy.get(shot);

            // Ajustamos los valores como indica la documentación del método initShot()
            int expectedX = x + 6;
            int expectedY = y - 1;

            // Comprobamos que el efecto es el deseado
            assertTrue((valorx == expectedX) && (valory == expectedY));

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
