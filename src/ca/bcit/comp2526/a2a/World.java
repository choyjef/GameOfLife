/**
 * 
 */
package ca.bcit.comp2526.a2a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jeffrey
 *
 */
public class World {

    int rows;
    int columns;
    Cell[][] grid;

    /**
     * 
     */
    public World(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
    }

    public void takeTurn() {

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {

                grid[i][j].takeTurn();

            }
        }
        
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {

                grid[i][j].getInhabitant().setActionTaken(false);

            }
        }
        
    }

    public void init() {
        int inhabitantRoll;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j] = new Cell(this, i, j);
                inhabitantRoll = RandomGenerator.nextNumber(99);

                if (inhabitantRoll >= 80) {
                    grid[i][j].setInhabitant(new Herbivore(grid[i][j]));                    
                } else if (inhabitantRoll >= 50) {
                    grid[i][j].setInhabitant(new Plant(grid[i][j]));
                } else {
                    grid[i][j].setInhabitant(new Blank(grid[i][j]));
                }

                grid[i][j].init();
            }
        }

        meetTheNeighbourhood();

        
        List<Cell> neighbs = new
        ArrayList<Cell>(Arrays.asList(grid[0][1].getNeighbours()));
        
        for (int n = 0; n < neighbs.size(); n++) {
        System.out.print("Neighbour #" + n + ": "); neighbs.get(n).draw(); }
         

    }

    Cell getCellAt(int row, int column) {
        return grid[row][column];

    }

    public int getRowCount() {
        return rows;

    }

    public int getColumnCount() {
        return columns;

    }

    private void meetTheNeighbourhood() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j].meetNeighbours();
            }
        }

    }

}
