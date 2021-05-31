import javax.swing.*;
import java.awt.*;

/**
 * The Instructions class.
 */
public class instructions extends JPanel {
    private boolean show=true;

    /**
     * Instantiates a new Instructions class.
     */
    public instructions(){}

    /**
     * Draw instructions.
     */
    public void drawInstructions(){
        this.repaint();
    }

    /**
     * Changes whether instructions is true or false
     */
    public void flip(){
        show=false;
    }

    /**
     * Paint component to draw the instructions
     * @param g the Graphics object used to draw the string
     */
    @Override
    public void paintComponent(Graphics g) {
        if(show==true){
            g.setColor(Color.BLACK);
            Font tr = new Font("TimesRoman", Font.PLAIN, 50);
            g.setFont(tr);
            g.drawString("use the arrow keys to move ",100,550);
        }
        else{
            g.setColor(Color.WHITE);
            Font tr = new Font("TimesRoman", Font.PLAIN, 50);
            g.setFont(tr);
        }
    }
}
