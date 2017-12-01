package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * A Cell meant to represent a specific point in the world grid, 
 *      contains an inhabitant.
 * 
 * @author Jeffrey
 * @version 2017-11-19
 */
@SuppressWarnings("serial")
public class Cell extends JPanel implements Serializable {

    /**
     * The World object this Cell exists in. 
     */
    private transient World world;
    
    /**
     * The row index of this Cell.
     */
    private int row;
    
    /**
     * The column index of this Cell.
     */
    private int column;
    
    /**
     * The current background color this Cell.
     */
    private Color color;
    
    /**
     * The Content object currently existing in this Cell.
     */
    private Lifeform inhabitant;
    
    /**
     * Array of neighbouring Cells.
     */
    private Cell[] neighbours;


    /**
     * Creates a Cell object.
     * 
     * @param world
     *          The world object that this Cell exists in.
     * @param row
     *          The row index where this Cell exists.
     * @param column
     *          The column index where this Cell exists.
     */
    public Cell(World world, int row, int column) {
        this.row = row;
        this.column = column;
        this.world = world;
    }
    
    /**
     * Sets the background colour of this Cell.
     */
    public void init() {
        if (inhabitant == null) {
            this.setBackground(Color.white);
        } else {
            this.setBackground(color);
        }
    }
    
    /**
     * Returns the Content object inhabiting the Cell.
     * @return
     *      This Cell's Content inhabitant.
     */
    public Lifeform getInhabitant() {
        return inhabitant;
        
    }
    
    /**
     * Sets content object as inhabitant of this Cell.
     * @param l
     *          new inhabitant.
     */
    public void setInhabitant(Lifeform l) {
        this.inhabitant = l;
        if (inhabitant != null) {
            inhabitant.init();
        }
    }
    
    /**
     * Returns the array of this Cell's neighbours.
     * @return
     *      An array containing this Cell's neighbouring cells.
     */
    public Cell[] getNeighbours() {
      
        return neighbours;
        
    }
    
    /**
     * Collects all immediately adjacent Cells and stores collection as a 
     *      data member.
     * 
     */
    public void meetNeighbours() {
        List<Cell> neighbs = new ArrayList<Cell>();
        
        // iterates through the nine squares surrounding a position on a grid
        // and adds them if they are not the original position and if they are
        // within the bounds of the world grid
        for (int i = this.row - 1; i <= this.row + 1; i++) {
            for (int j = this.column - 1; j <= this.column + 1; j++) {
                if (i == this.row && j == this.column) {
                    continue;
                }
                if (isInWorld(i, j)) {
                    neighbs.add(world.getCellAt(i, j));
                }
            }
        }
        
        this.neighbours = neighbs.toArray(new Cell[neighbs.size()]);
    }
    
    /**
     * Checks whether the coordinates passed in are within the World
     *  object grid the the Cell exists in. Primarily used for finding
     *  neighbours.
     * 
     * @param x
     *          Row index of the Cell to check.
     * @param y
     *          Column index of the Cell to check.
     * @return
     *          True if Cell exists within the World grid, otherwise false.
     */
    protected boolean isInWorld(int x, int y) {
        return x >= 0 
                && y >= 0 
                && x < world.getColumnCount() 
                && y < world.getRowCount();
    }
    
    /**
     * Checks whether the Cell can be considered empty for the purposes
     *      of the game (i.e. contains an inhabitant of 'Blank' class.
     * 
     * @return
     *      True if cell is considered empty.
     */
    public boolean isEmpty() {
        return inhabitant == null;
    }
    
    /**
     * Prints out the row and column indices for this cell.
     */
    public void draw() {
        System.out.print(row + ", " + column);
        if (!isEmpty()) {
            System.out.print("Has lifefform");
        }
        System.out.println();
    }

    /**
     * Returns the Cell's current background colour.
     * 
     * @return
     *      current background colour.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the Cell's current background colour.
     * @param color
     *          New background colour to set.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
    
    
}
