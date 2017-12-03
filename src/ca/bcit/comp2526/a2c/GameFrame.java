package ca.bcit.comp2526.a2c;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
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
    
    private JPanel gamePanel;
    private GameControlsPanel controller;
    
    
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
        controller = new GameControlsPanel(this, world);
        
        add(controller, BorderLayout.PAGE_END);
        
        gamePanel = new JPanel();
        add(gamePanel, BorderLayout.CENTER);
        gamePanel.setLayout(new GridLayout(world.getRowCount(), 
                world.getColumnCount()));
        populateGamePanel();
          
        addMouseListener(new TurnListener(this));
        
        repaint();
    }
    
    

    /**
     * Advances the World object a single turn.
     */
    public void takeTurn() {
        world.updateLinkedList();
        repaint();
    }
    
    void populateGamePanel() {
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                gamePanel.add(world.getCellAt(row, col));
                world.getCellAt(row, col).setBorder(
                        BorderFactory.createMatteBorder(
                                1, 1, 0, 0, Color.BLACK));
            }
        }
    }
    
    public void save() {
        final JFileChooser fc = 
                new JFileChooser(System.getProperty("user.dir"));
        try {
            fc.setSelectedFile(new File("*.ser"));
            int fileSaved = fc.showSaveDialog(GameFrame.this);
            
            if (fileSaved == JFileChooser.APPROVE_OPTION) {
                File saveGame = fc.getSelectedFile();
                FileOutputStream fileOut = new FileOutputStream(saveGame);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(world);
                out.close();
                fileOut.close();
                System.out.println("Game saved.");
            } else {
                return;
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
        
    }
    
    public void load() {
        World brave;
        final JFileChooser fc = 
                new JFileChooser(System.getProperty("user.dir"));
        try {
            int fileOpened = fc.showOpenDialog(GameFrame.this);
            
            if (fileOpened == JFileChooser.APPROVE_OPTION) {
                FileInputStream fileIn = 
                        new FileInputStream(fc.getSelectedFile());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                brave = (World) in.readObject();
                in.close();
                fileIn.close();
            } else {
                return;
            }  
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
        
        world = brave;
        gamePanel.removeAll();
        populateGamePanel();
        world.reinit();
        controller.setWorld(world);
        GameFrame.this.repaint();
        System.out.println("Game loaded.");
    }
    
    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
