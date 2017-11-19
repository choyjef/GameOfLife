package ca.bcit.comp2526.a2b;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Drives the program.
 * 
 * @author Jeffrey
 * @version 2017-11-04
 */
public final class Main {
    
    private static final Toolkit TOOLKIT;
    
    private static final int WORLD_SIZE = 7;
    
    private static final float SCREEN_AREA_CALC = 0.80f;
    
    private static final float SCREEN_AREA_PERCENT_UPPER = 100.0f;

    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * Creates a main object.
     */
    private Main() {
    }

    /**
     * Entry point for the program.
     * @param argv
     *          command line arguments
     */
    public static void main(final String[] argv) {
        final GameFrame frame;
        final World world;

        RandomGenerator.reset();
        world = new World(WORLD_SIZE, WORLD_SIZE);
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * Positions the GameFrame object.
     * 
     * @param frame
     *          GameFrame object to position.
     */
    private static void position(final GameFrame frame) {
        final Dimension size;

        size = calculateScreenArea(SCREEN_AREA_CALC, SCREEN_AREA_CALC);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     * Centers the GameFrame object on screen.
     * 
     * @param size
     *          Size of the GameFrame
     * @return
     *          Point containing calculated size
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2, 
                (screenSize.height - size.height) / 2));
    }

    /**
     * Calculates the screen area.
     * 
     * @param widthPercent
     *          percentage of actual screen width desired
     * @param heightPercent
     *          percentage of actual screen height desired
     * @return
     *      new screen dimensions
     */
    public static Dimension calculateScreenArea(
            final float widthPercent, final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) 
                || (widthPercent > SCREEN_AREA_PERCENT_UPPER)) {
            throw new IllegalArgumentException(
                    "widthPercent cannot be " 
                            + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) 
                || (heightPercent > SCREEN_AREA_PERCENT_UPPER)) {
            throw new IllegalArgumentException(""
                    + "heightPercent cannot be " 
                            + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
