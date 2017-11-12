package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * A Cell meant to represent a specific point in the world grid, 
 *      contains an inhabitant.
 * 
 * @author Jeffrey
 * @version 2017-11-04
 */
@SuppressWarnings("serial")
public class Cell extends JPanel {
    
    /**
     * The World object this Cell exists in. 
     */
    private World world;
    
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
    private Content inhabitant;
    
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
     * Prints out the row and column indices for this cell.
     */
    public void draw() {
        System.out.println(row + ", " + column);
    }
    
    /**
     * Returns the Content object inhabiting the Cell.
     * @return
     *      This Cell's Content inhabitant.
     */
    public Content getInhabitant() {
        return inhabitant;
        
    }
    
    
    /**
     * Returns a Point object of this Cell's location.
     * @see java.awt.Component#getLocation()
     * 
     */
    @Override
    public Point getLocation() {
        Point location = new Point(this.row, this.column);
        
        return location;
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
     * Sets the background colour of this Cell.
     */
    public void init() {
        this.setBackground(color);    
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
    private boolean isInWorld(int x, int y) {
        return x >= 0 
                && y >= 0 
                && x < world.getColumnCount() 
                && y < world.getRowCount();
    }
    
    /**
     * Collects all immediately adjacent Cells and stores collection as a 
     *      data member.
     * 
     */
    public void meetNeighbours() {
        List<Cell> neighbs = new ArrayList<Cell>();
        
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
     * Sets content object as inhabitant of this Cell.
     * @param c
     *          new inhabitant.
     */
    public void setInhabitant(Content c) {
        this.inhabitant = c;
        this.inhabitant.init();
    }
    
    /**
     * Triggers Cell inhabitant's takeAction method and updates the 
     *      background color in case the current inhabitant has moved 
     *      or been replaced.
     */
    public void takeTurn() {
        if (!inhabitant.isActionTaken()) {
            inhabitant.takeAction();
            this.setBackground(color);
        }
    }
    
    /**
     * Checks whether the Cell can be considered empty for the purposes
     *      of the game (i.e. contains an inhabitant of 'Blank' class.
     * 
     * @return
     *      True if cell is considered empty.
     */
    public boolean isEmpty() {
        return inhabitant.getType() == ContentType.BLANK;
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
    
}
