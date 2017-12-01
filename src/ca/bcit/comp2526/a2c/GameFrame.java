package ca.bcit.comp2526.a2c;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    private World world;

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
        
        JPanel gamePanel = new JPanel();
        JPanel controls = new JPanel();
        add(gamePanel, BorderLayout.CENTER);
        add(controls, BorderLayout.PAGE_END);
        
        controls.setLayout(new FlowLayout());
        JButton startStopButton = new JButton("Start/Stop");
        
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                // TODO Auto-generated method stub
                world.startSimulation();        
            }
        });
        
        JButton saveButton = new JButton("Save");
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    FileOutputStream fileOut = new FileOutputStream("saveGame.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(world);
                    out.close();
                    fileOut.close();
                    System.out.printf("Game saved in saveGame.ser");
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        });
        
        JButton loadButton = new JButton("Load");
        
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                World brave;
                try {
                    FileInputStream fileIn = new FileInputStream("saveGame.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    brave = (World) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    i.printStackTrace();
                    return;
                } catch (ClassNotFoundException c) {
                    c.printStackTrace();
                    return;
                }
                
                world = brave;
                gamePanel.removeAll();
                for (int row = 0; row < world.getRowCount(); row++) {
                    for (int col = 0; col < world.getColumnCount(); col++) {
                        gamePanel.add(world.getCellAt(row, col));
                        world.getCellAt(row, col).setBorder(
                                BorderFactory.createMatteBorder(
                                        1, 1, 0, 0, Color.BLACK));
                    }
                }
                world.reinit();
                GameFrame.this.repaint();
                System.out.println("Game loaded.");
                System.out.println("Cell:" + brave.getCellCount());
                
            } 
        });
        
        controls.add(startStopButton);
        controls.add(saveButton);
        controls.add(loadButton);
        
        gamePanel.setLayout(new GridLayout(world.getRowCount(), 
                world.getColumnCount()));
        
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                gamePanel.add(world.getCellAt(row, col));
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
