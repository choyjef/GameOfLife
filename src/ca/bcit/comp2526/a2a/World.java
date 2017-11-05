package ca.bcit.comp2526.a2a;

/**
 * A world object in which the Game of Life takes place.
 * 
 * @author Jeffrey
 * @version 2017-11-04
 *
 */
public class World {

    /**
     * The number of rows in the world grid.
     */
    private int rows;
    
    /**
     * The number of columns in the world grid.
     */
    private int columns;
    
    /**
     * A grid of Cells which visually represents the game world.
     */
    private Cell[][] grid;

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
