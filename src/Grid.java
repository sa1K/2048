import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The Grid class.
 */
public class Grid extends JPanel{
    /**
     * Instantiates a new Grid.
     */
    public Grid(){
    }

    /**
     * Draws grid.
     */
    public void drawLines(){
        this.repaint();
    }

    /**
     * Paint component to draw the grid
     * @param g the Graphics object used to draw the grid
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 480, 480);
        g.drawLine(120, 0, 120, 480);
        g.drawLine(240, 0, 240, 480);
        g.drawLine(360, 0, 360, 480);
        g.drawLine(0, 120, 480, 120);
        g.drawLine(0, 240, 480, 240);
        g.drawLine(0, 360, 480, 360);
    }

}
