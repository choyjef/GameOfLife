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
        int inhabitantRoll;
        
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j] = new Cell(this, i, j);
                inhabitantRoll = RandomGenerator.nextNumber(99);
                
                if (inhabitantRoll >= 80) {
                    grid[i][j].setInhabitant(new Herbivore(grid[i][j]));
                    grid[i][j].init();
                } else if (inhabitantRoll >= 50) {
                    grid[i][j].setInhabitant(new Plant(grid[i][j]));
                    grid[i][j].init();
                } else {
                    grid[i][j].setInhabitant(new Empty(grid[i][j]));
                    grid[i][j].init();
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
