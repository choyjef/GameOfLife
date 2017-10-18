/**
 * 
 */
package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * @author Jeffrey
 *
 */
public interface Content {
    
    Color init();
    
    void setCell(Cell location);
    
    void takeAction();
}
