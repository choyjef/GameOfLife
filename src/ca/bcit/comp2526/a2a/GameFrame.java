package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private final World world;

    public GameFrame(final World w) {
        world = w;
    }

    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
                world.getCellAt(row, col).setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
            }
        }

        addMouseListener(new TurnListener(this));
    }

    public void takeTurn() {
        world.takeTurn();
        repaint();
    }
}
