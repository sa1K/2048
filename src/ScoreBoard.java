import javax.swing.*;
import java.awt.*;

/**
 * The Score board class.
 */
public class ScoreBoard extends JPanel {
    private int score;

    /**
     * Instantiates a new Score board.
     */
    public ScoreBoard(){}

    /**
     * Changes the score on the board and redraws the board.
     *
     * @param s the score that the board will be changed to
     */
    public void drawScore(int s){
        score=s;
        this.repaint();
    }

    /**
     * Paint component to draw the score board
     * @param g the Graphics object used to draw the score and the strings
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        Font tr = new Font("TimesRoman", Font.BOLD, 70);
        g.setFont(tr);
        g.drawString("SCORE",500,100);
        Font tr1 = new Font("TimesRoman", Font.PLAIN, 30);
        g.setFont(tr1);
        g.drawString(Integer.toString(score),500,150);
    }

}
