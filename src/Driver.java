import javax.swing.*;
import java.awt.*;

/**
 * The type Driver.
 */
public class Driver{
    /**
     * Main driver code of the program.
     *
     * @param args the args for the program
     */
    public static void main(String[] args){
        JFrame window = new JFrame("2048");
        window.setBackground(Color.WHITE);
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(490, 490));
        Grid grid= new Grid();
        ScoreBoard scoreBoard=new ScoreBoard();
        loseScreen lose=new loseScreen();
        instructions i= new instructions();
        Matrix m= new Matrix(lose,window, scoreBoard, i);
        winScreen win=new winScreen();
        keyResponse k= new keyResponse(m,panel,grid,lose, win, window,i);
        panel.setLayout(null);
        panel.add(grid);
        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(1000, 1000));
        window.addKeyListener(k);
        window.setVisible(true);
    }

}
