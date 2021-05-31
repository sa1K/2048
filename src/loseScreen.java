import javax.swing.*;
import java.awt.*;

/**
 * The class to draw the Lose screen.
 */
public class loseScreen extends JPanel {
    /**
     * Instantiates a new Lose screen class.
     */
    public loseScreen(){
    }

    public void drawLose(){
        this.repaint();
    }
    /**
     * Paint component to draw the loose screen
     * @param g the Graphics object used to draw the string
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        Font tr = new Font("TimesRoman", Font.PLAIN, 50);
        g.setFont(tr);
        g.drawString("You Lost :(",100,550);
        Font tr1 = new Font("TimesRoman", Font.PLAIN, 30);
        g.setFont(tr1);
        g.drawString("Close this window and restart the program to replay",250,700);
    }
}
