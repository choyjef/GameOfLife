package ca.bcit.comp2526.a2c;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameControlsPanel extends JPanel {

    

    private static final long serialVersionUID = -4040955820198173379L;
    
    private World world;
    private GameFrame game;
    
    private JButton saveButton;
    private JButton loadButton;
    private JButton startStopButton;
    private JButton resetButton;

    public GameControlsPanel(GameFrame game, World world) {
        super();
        setLayout(new FlowLayout());
        this.world = world;
        this.game = game;
        populateControlPanel();
    }
    
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

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
