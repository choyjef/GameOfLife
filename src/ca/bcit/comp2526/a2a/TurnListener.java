package ca.bcit.comp2526.a2a;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jeffrey
 *
 */
public class TurnListener extends MouseAdapter {
    GameFrame game;

    /**
     * 
     */
    public TurnListener(GameFrame game) {
        this.game = game;
    }
    
    public void mouseClicked(MouseEvent e) {
        game.takeTurn();
    }

}
