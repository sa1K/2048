import javax.swing.*;
import java.awt.*;

/**
 * The class to draw Win screen.
 */
public class winScreen extends JPanel {
    /**
     * Instantiates a new Win screen class and draws a new win screen.
     */
    public winScreen(){
    }

    public void drawWin(){
        this.repaint();
    }

    /**
     * Paint component to draw the win screen
     * @param g the Graphics object used to draw the string
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        Font tr = new Font("TimesRoman", Font.BOLD, 50);
        g.setFont(tr);
        g.drawString("You Won!!",300,600);
        Font tr1 = new Font("TimesRoman", Font.PLAIN, 30);
        g.setFont(tr1);
        g.drawString("Close this window and restart the program to replay",250,700);
    }
}
