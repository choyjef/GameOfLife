package ca.bcit.comp2526.a2c;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A GameFrame object that contains the Game World and visualizes it.
 * 
 * @author Jeffrey
 * @version 2017-11-19
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {
    
    /**
     * The World object in which the game occurs.
     */
    private final World world;

    /**
     * Creates a GameFrame object.
     * 
     * @param w
     *      the World object in which the game occurs.
     */
    public GameFrame(final World w) {
        world = w;
    }

    /**
     * Initializes the game World grid and creates a visual representation.
     */
    public void init() {
        setTitle("Assignment 2c");
        setLayout(new BorderLayout());
        
        JPanel game = new JPanel();
        JPanel controls = new JPanel();
        add(game, BorderLayout.CENTER);
        add(controls, BorderLayout.PAGE_END);
        
        controls.setLayout(new FlowLayout());
        JButton startStop = new JButton("Start/Stop");
        startStop.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                world.startSimulation();
                
            }
        
        });
        controls.add(startStop);
        
        
        
        game.setLayout(new GridLayout(world.getRowCount(), 
                world.getColumnCount()));
        
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                game.add(world.getCellAt(row, col));
                world.getCellAt(row, col).setBorder(
                        BorderFactory.createMatteBorder(
                                1, 1, 0, 0, Color.BLACK));
            }
        }

        addMouseListener(new TurnListener(this));
        
        repaint();
    }
    
    /**
     * Advances the World object a single turn.
     */
    public void takeTurn() {
        world.update();
        repaint();
    }
}
