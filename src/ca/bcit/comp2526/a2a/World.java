/**
 * 
 */
package ca.bcit.comp2526.a2a;

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
        
    }
    
    public void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell(this, i, j);
                if (RandomGenerator.nextNumber(100) >= 80) {
                    grid[i][j].setInhabitant(new Plant());
                } else if (RandomGenerator.nextNumber(100) >= 50) {
                    //generate herbivore
                }
            }
        }
        
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

}
