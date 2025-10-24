package space_invaders.sprites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.TextField;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyEventsTest {
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void playerRandomKeyPressed() {
        int dx = player.dx;
        KeyEvent event = getEvent(KeyEvent.VK_4);
        player.keyPressed(event);
        assertEquals(dx, player.dx);
    }

    @Test
    void playerKeyLeftPressed() {
        KeyEvent event = getEvent(KeyEvent.VK_LEFT);
        player.keyPressed(event);
        assertEquals(-2, player.dx);
    }

    @Test
    void playerKeyRightPressed() {
        KeyEvent event = getEvent(KeyEvent.VK_RIGHT);
        player.keyPressed(event);
        assertEquals(2, player.dx);
    }

    @Test
    void playerRandomKeyWhileMovingLeft() {
        KeyEvent event = getEvent(KeyEvent.VK_LEFT);
        player.keyPressed(event);
        int dx = player.dx;
        KeyEvent event2 = getEvent(KeyEvent.VK_4);
        player.keyPressed(event2);
        assertEquals(dx, player.dx);
    }

    @Test
    void playerRandomKeyWhileMovingRight() {
        KeyEvent event = getEvent(KeyEvent.VK_RIGHT);
        player.keyPressed(event);
        int dx = player.dx;
        KeyEvent event2 = getEvent(KeyEvent.VK_4);
        player.keyPressed(event2);
        assertEquals(dx, player.dx);
    }

    @Test
    void playerKeyLeftReleased() {
        KeyEvent event = getEvent(KeyEvent.VK_LEFT);
        player.keyReleased(event);
        assertEquals(0, player.dx);
    }

    @Test
    void playerKeyRightReleased() {
        KeyEvent event = getEvent(KeyEvent.VK_RIGHT);
        player.keyReleased(event);
        assertEquals(0, player.dx);
    }

    @Test
    void playerKeyLeftReleasedWhileMoving() {
        KeyEvent event = getEvent(KeyEvent.VK_LEFT);
        player.keyPressed(event);
        player.keyReleased(event);
        assertEquals(0, player.dx);
    }

    @Test
    void playerKeyRightReleasedWhileMoving() {
        KeyEvent event = getEvent(KeyEvent.VK_RIGHT);
        player.keyPressed(event);
        player.keyReleased(event);
        assertEquals(0, player.dx);
    }

    private KeyEvent getEvent(int keyCode) {
        return new KeyEvent( new TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
    }
}
