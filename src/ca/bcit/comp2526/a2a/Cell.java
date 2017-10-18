/**
 * 
 */
package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author Jeffrey
 *
 */
public class Cell extends JPanel {
    private World world;
    
    private int row;
    
    private int column;
    
    private Color color;
    
    private Content inhabitant;
    
    private Cell[] neighbours;

    /**
     * 
     */
    public Cell(World world, int row, int column) {
        this.row = row;
        this.column = column;
        this.world = world;
        meetNeighbours();
    }
    
    public void init() {
        this.setBackground(inhabitant.init());
        
    }
    
    public void takeTurn() {
        
    }
    
    public Point getLocation() {
        Point location = new Point(this.row, this.column);
        
        return location;
    }
    
    public Cell[] getNeighbours() {
        return neighbours;
        
    }
    
    public void setInhabitant(Content c) {
        this.inhabitant = c;
    }
    
    public Content getInhabitant() {
        return inhabitant;
        
    }
    
    private boolean isInWorld(int x, int y) {
        return x >= 0 
                && y >= 0 
                && x < world.getColumnCount() 
                && y < world.getRowCount();
    }
    
    private void meetNeighbours() {
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

}
