package ca.bcit.comp2526.a2c;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * An object to listen for mouse clicks in order to advancec the game.
 * 
 * @author Jeffrey
 * @version 2017-11-19
 */
public class TurnListener extends MouseAdapter {
    
    /**
     * The GameFrame object in which to listen for clicks.
     */
    private GameFrame game;

    /**
     * Creates a TurnListener object.
     * 
     * @param game
     *          the GameFrame object in which the TurnListener
     *          will listen for clicks.
     */
    public TurnListener(GameFrame game) {
        this.game = game;
    }

    /** 
     * Advances the game a single turn on a mouse click.
     * @param e
     *      mouse click event.
     */
    public void mouseClicked(MouseEvent e) {
        game.takeTurn();
    }
    
    /**
     * Returns the current GameFrame object the TurnListener
     *      is associated with.
     *      
     * @return the game
     */
    public GameFrame getGame() {
        return game;
    }

    /**
     * Sets the GameFrame object to listen for clicks in.
     * 
     * @param game the game to set
     */
    public void setGame(GameFrame game) {
        this.game = game;
    }

}
