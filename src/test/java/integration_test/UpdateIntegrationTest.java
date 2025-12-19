package integration_test;

import main.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import javax.swing.*;
import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateIntegrationTest {
    Board board;

    @Mock
    Player player;

    @Mock
    Alien alien;

    @Mock
    Alien.Bomb bomb;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        board = new Board();

        board.setPlayer(player);
        board.setAliens(List.of(alien));

        Shot shot = new Shot();
        board.setShot(shot);

        board.setDeaths(0);
        board.setInGame(true);
        board.setDirection(1);
        if (board.getTimer() != null) {
            board.getTimer().stop();
        }
        board.setTimer(mock(Timer.class));
    }

    @Test
    void update_llega_a_Player_act() {

        board.setAliens(java.util.Collections.emptyList());

        when(player.isVisible()).thenReturn(true);

        board.update();

        verify(player, times(1)).act();
    }

    @Test
    void update_llega_a_update_shots() {

        board.setAliens(java.util.Collections.emptyList());

        Shot shot = spy(new Shot());
        shot.setX(10);
        shot.setY(10);
        shot.setVisible(true);
        board.setShot(shot);

        board.update();

        verify(shot, atLeastOnce()).getY();
    }

    @Test
    void update_llega_a_update_aliens() {

        when(alien.getX()).thenReturn(100);
        when(alien.isVisible()).thenReturn(false);
        when(alien.getBomb()).thenReturn(bomb);
        when(bomb.isDestroyed()).thenReturn(true);

        board.update();

        verify(alien, atLeastOnce()).getX();
    }

    @Test
    void update_llega_update_bomb() {

        when(alien.getBomb()).thenReturn(bomb);
        when(bomb.isDestroyed()).thenReturn(true);
        when(alien.isVisible()).thenReturn(false);

        board.update();

        verify(alien, times(1)).getBomb();
    }
}
