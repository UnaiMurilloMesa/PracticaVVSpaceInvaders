package integration_test;

import main.Board;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockConstruction;

public class GameInitTest {

    @Test
    public void testGameInitMockingAlien() {
        try (MockedConstruction<Alien> mocked = mockConstruction(Alien.class)) {
            Board board = new Board();

            assertNotNull(board.getAliens(), "Aliens list should not be null");
            assertEquals(24, board.getAliens().size(), "Should have 24 aliens in the list");
            assertEquals(24, mocked.constructed().size(),
                    "Should have constructed 24 aliens");

            // Verify content is indeed our mocks
            List<Alien> aliens = board.getAliens();
            for (int i = 0; i < 24; i++) {
                assertEquals(mocked.constructed().get(i), aliens.get(i));
            }
        }
    }

    @Test
    public void testGameInitMockingPlayer() {
        try (MockedConstruction<Player> mocked = mockConstruction(Player.class)) {
            Board board = new Board();

            assertNotNull(board.getPlayer(), "Player should not be null");
            assertEquals(1, mocked.constructed().size(), "Should have constructed 1 player");

            assertEquals(mocked.constructed().get(0), board.getPlayer());
        }
    }

    @Test
    public void testGameInitMockingShot() {
        try (MockedConstruction<Shot> mocked = mockConstruction(Shot.class)) {
            Board board = new Board();

            assertNotNull(board.getShot(), "Shot should not be null");
            assertEquals(1, mocked.constructed().size(), "Should have constructed 1 shot");
            assertEquals(mocked.constructed().get(0), board.getShot());
        }
    }
}
