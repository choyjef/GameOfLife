package ca.bcit.comp2526.a2c;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

/**
 * A world object in which the Game of Life takes place.
 * 
 * @author Jeffrey
 * @version 2017-12-02
 *
 */
public class World implements Serializable {
    
    /**
     * Generated SerialVersionUID.
     */
    private static final long serialVersionUID = 9111559383502959631L;

    /**
     * The upper limit passed to our random number generator.
     */
    private static final int RANDOM_GEN_LIMIT = 100;
    
    /**
     * Probability constant for generating a herbivore.
     */
    private static final int HERBIVORE_PROB = 80;
    
    /**
     * Probability constant for generating a plant.
     */
    private static final int PLANT_PROB = 50;
    
    /**
     * Probability constant for generating a carnivore.
     */
    private static final int CARNIVORE_PROB = 40;
    
    /**
     * Probability constant for generating a omnivore.
     */
    private static final int OMNIVORE_PROB = 32;
    
    /**
     * The speed of the simulation.
     */
    private static final int SIMULATION_SPEED = 100;
    
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
    
    private Timer timer;
    
    private int cellCount;
  
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
     * Initializes the World object by creating and populating creatures 
     *      into the cells.
     */
    public void init() {
        
        timer = new Timer(SIMULATION_SPEED, (a -> updateUsingLinkedList()));
        RandomGenerator.reset();

        // creates the cells which represent the game world
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j] = new Cell(this, i, j);
                cellCount++;
                int inhabRoll = RandomGenerator.nextNumber(RANDOM_GEN_LIMIT);

                // random generation of creature types 
                if (inhabRoll >= HERBIVORE_PROB) {
                    grid[i][j].setInhabitant(
                            new Herbivore(grid[i][j]));                    
                } else if (inhabRoll >= PLANT_PROB) {
                    grid[i][j].setInhabitant(new Plant(grid[i][j]));
                } else if (inhabRoll >= CARNIVORE_PROB) {
                    grid[i][j].setInhabitant(new Carnivore(grid[i][j]));
                } else if (inhabRoll >= OMNIVORE_PROB) {
                    grid[i][j].setInhabitant(new Omnivore(grid[i][j]));
                } 
                
                grid[i][j].init();
            }
        }
        meetTheNeighbourhood();
    }
    
    /**
     * Reinitializes the world following being loaded from a save state.
     */
    public void reinit() {
        timer = new Timer(SIMULATION_SPEED, (a -> updateUsingLinkedList()));

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j].setWorld(this);
                if (!grid[i][j].isEmpty()) {
                    grid[i][j].getInhabitant().setLocation(grid[i][j]);
                }
                grid[i][j].init(); 
            }
        } 
    }
    
    /**
     * Advances the world a single turn.
     */    
    public void update() {
        
        List<Lifeform> lifeforms = new ArrayList<Lifeform>();
        
        // collect all of the existing lifeforms into a list
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (grid[i][j].getInhabitant() != null) {
                    lifeforms.add(grid[i][j].getInhabitant());
                }
            }
        }
        
        // triggers lifeform actions
        for (Lifeform l : lifeforms) {
            if (!l.isActionTaken()) {
                l.takeAction();
            }
        }
        
        // resets action taken status
        for (Lifeform l : lifeforms) {
            l.setActionTaken(false);
        }
    }
    
    /**
     * Advances the World a single turn using a double linked list for
     * the collection of Lifeforms.
     */
    public void updateUsingLinkedList() {
        DoubleLinkedList<Lifeform> lifeforms = new DoubleLinkedList<Lifeform>();
        
        // collect all of the existing lifeforms into a list
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (!grid[i][j].isEmpty()) {
                    try {
                        lifeforms.addToEnd(grid[i][j].getInhabitant()); 
                    } catch (CouldNotAddException c) {
                        c.printStackTrace();
                    }
                }
            }
        }
        
        // Create iterator
        Iterator<Lifeform> it = lifeforms.iterator();
        
        // Tells lifeforms to take action
        while (it.hasNext()) {
            Lifeform l = it.next();  
            if (!l.isActionTaken()) {
                l.takeAction();
            }        
        }
        
        // Reset iterator
        it = lifeforms.iterator();
        
        // resets action taken status
        while (it.hasNext()) {
            Lifeform l = it.next();
            l.setActionTaken(false);
        }
        
    }
    
    /**
     * Initiates the timer to continually advance the world until stopped.
     */
    public void startSimulation() {
        
        if (!timer.isRunning()) {
            timer.start();
        } else {
            endSimulation();
        }
    }
    
    /**
     * Stops the timer from advancing turns in the world.
     */
    public void endSimulation() {
        timer.stop();
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
    
    /**
     * Returns the 2D grid of Cells in the world.
     * @return
     *          the array of cells.
     */
    public Cell[][] getGrid() {
        return grid;
    }

    /**
     * Sets the grid for this world.
     * @param grid
     *          the new grid of Cells to be set.
     */
    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }
    
    /**
     * Returns the number of cells in the world.
     * 
     * @return the cellCount
     *                  the number of cells currently in the world.
     */
    public int getCellCount() {
        return cellCount;
    }


}
