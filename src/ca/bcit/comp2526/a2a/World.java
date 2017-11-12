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
     * The upper limit passed to our random number generator.
     */
    private static final int RANDOM_GEN_LIMIT = 99;
    
    /**
     * The upper limit passed to our random number generator.
     */
    private static final int HERBIVORE_PROB = 80;
    
    /**
     * The upper limit passed to our random number generator.
     */
    private static final int PLANT_PROB = 50;
    
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
     * Creates a World object.
     * 
     * @param rows
     *          The number of rows the world grid will contain.
     * @param columns
     *          The number of columns the world grid will contain.
     */
    public World(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
    }
    
    
    /**
     * Advances the world a single turn.
     */
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

    /**
     * Initializes the World object by creating and populating creatures 
     *      into the cells.
     */
    public void init() {
        int inhabitantRoll;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j] = new Cell(this, i, j);
                inhabitantRoll = RandomGenerator.nextNumber(RANDOM_GEN_LIMIT);

                if (inhabitantRoll >= HERBIVORE_PROB) {
                    grid[i][j].setInhabitant(
                            new Herbivore(grid[i][j]));                    
                } else if (inhabitantRoll >= PLANT_PROB) {
                    grid[i][j].setInhabitant(new Plant(grid[i][j]));
                } else {
                    grid[i][j].setInhabitant(new Blank(grid[i][j]));
                }
                grid[i][j].init();
            }
        }
        meetTheNeighbourhood();
    }

    /**
     * Returns the cell at the specified row and column.
     * 
     * @param row
     *          The row index of desired cell.
     * @param column
     *          The column index of desired cell.
     * @return
     *          The cell at the specified indices.
     */
    Cell getCellAt(int row, int column) {
        return grid[row][column];
    }

    /**
     * Returns the number of rows in the world grid.
     * 
     * @return
     *      the number of rows
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Returns the number of columns in the world grid.
     * 
     * @return
     *      the number of columns
     */
    public int getColumnCount() {
        return columns;
    }

    /**
     * Has each cell collect an array of their neighbouring cells
     *      and store it as a data member.
     * 
     */
    private void meetTheNeighbourhood() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j].meetNeighbours();
            }
        }
    }

}
