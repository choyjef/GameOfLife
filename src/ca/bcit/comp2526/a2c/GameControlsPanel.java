package ca.bcit.comp2526.a2c;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A JPanel containing the game controls for the GameOfLife game.
 * 
 * @author Jeffrey
 * @version 2017-12-02
 */
public class GameControlsPanel extends JPanel {

    private static final long serialVersionUID = -4040955820198173379L;
    
    private World world;
    private GameFrame game;
    
    private JButton saveButton;
    private JButton loadButton;
    private JButton startStopButton;
    private JButton resetButton;

    /**
     * Instantiates a GameControlsPanel object.
     * 
     * @param game
     *          the game to be associated with the controls.
     * @param world
     *          the world the controls will be associated with.
     */
    public GameControlsPanel(GameFrame game, World world) {
        super();
        setLayout(new FlowLayout());
        this.world = world;
        this.game = game;
        populateControlPanel();
    }
    
    /**
     * Initializes the control panel with the appropriate buttons.
     */
    private void populateControlPanel() {
        
        ControlListener control = new ControlListener();
        startStopButton = new JButton("Start/Stop");
        startStopButton.addActionListener(control);
        
        saveButton = new JButton("Save");
        saveButton.addActionListener(control);
        
        loadButton = new JButton("Load");
        loadButton.addActionListener(control);
        
        resetButton = new JButton("Reset");
        resetButton.addActionListener(control);
        
        add(startStopButton);
        add(saveButton);
        add(loadButton);
        add(resetButton);
    }
    
    /**
     * Action Listener for control panel.
     * 
     * @author Jeffrey
     * @version 2017-12-02
     */
    private class ControlListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            
            if (source == startStopButton) {
                world.startSimulation();  
            } 
            
            if (source == loadButton) {
                world.endSimulation();
                game.load();
            } 
            
            if (source == saveButton) {
                world.endSimulation();
                game.save();
            }
            
            if (source == resetButton) {
                world.endSimulation();
                int row = world.getRowCount();
                int col = world.getColumnCount();
                world.setGrid(new Cell[row][col]);
                world.init();
                game.getGamePanel().removeAll();
                game.populateGamePanel();
                game.repaint();
                
            }
        }
    }

    /**
     * Gets the World currently associated with the controls.
     * @return
     *      the World to be returned.
     */
    public World getWorld() {
        return world;
    }
    
    /**
     * Sets the world to associated with the controls.
     * @param world
     *          the World object to be set.
     */
    public void setWorld(World world) {
        this.world = world;
    }
}
