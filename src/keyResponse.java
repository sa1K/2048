import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * The Key response class.
 */
public class keyResponse implements KeyListener {

    private Matrix m;
    private JPanel p;
    private Grid g;
    private Random rand= new Random();
    private loseScreen l;
    private winScreen w;
    private JFrame window;
    private instructions i;

    /**
     * Instantiates a new Key response and initializes the initial state of the program.
     *
     * @param mat    the board matrix
     * @param panel  the panel used for drawing
     * @param grid   the grid class used to draw the grid
     * @param lose   lose is used to draw out the loseScreen if the game is lost
     * @param win    win is used to draw the winScreen if the game is won
     * @param window the window is used to make sure that the win and lose screens show up
     * @param instr  used to draw the instructions
     */
    public keyResponse(Matrix mat, JPanel panel, Grid grid, loseScreen lose, winScreen win, JFrame window,instructions instr){
        m=mat;
        p=panel;
        g=grid;
        l=lose;
        w=win;
        i=instr;
        this.window=window;
        m.addTile(rand);
        m.addTile(rand);
        i.setSize(new Dimension(1000,1000));
        i.drawInstructions();
        panel.add(i);
        g.setSize(new Dimension(490,490));
        g.drawLines();
        m.drawWindow(panel,g);
    }

    /**
     * moves the tiles according to which key is pressed and checks if the win screen can be drawn
     *
     * @param e the key that is pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        boolean canMove=m.canMove();
        if(canMove==true){
            int c=e.getKeyCode();
            if(c==KeyEvent.VK_UP){
                m.moveUp(rand,p,g);
                if(m.getHighest()==2048){
                    i.setSize(new Dimension(1000,1000));
                    i.flip();
                    i.drawInstructions();
                    w.setSize(new Dimension(1000,1000));
                    w.drawWin();
                    window.add(w);
                    window.validate();
                    window.repaint();
                }
            }
            else if(c==KeyEvent.VK_LEFT){
                m.moveLeft(rand, p,g);
                if(m.getHighest()==2048){
                    i.setSize(new Dimension(1000,1000));
                    i.flip();
                    i.drawInstructions();
                    w.setSize(new Dimension(1000,1000));
                    w.drawWin();
                    window.add(w);
                    window.validate();
                    window.repaint();
                }
            }

            else if(c==KeyEvent.VK_DOWN){
                m.moveDown(rand, p,g);
                if(m.getHighest()==2048){
                    i.setSize(new Dimension(1000,1000));
                    i.flip();
                    i.drawInstructions();
                    w.setSize(new Dimension(1000,1000));
                    w.drawWin();
                    window.add(w);
                    window.validate();
                    window.repaint();
                }
            }

            else if(c==KeyEvent.VK_RIGHT){
                m.moveRight(rand, p,g);
                if(m.getHighest()==2048){
                    i.setSize(new Dimension(1000,1000));
                    i.flip();
                    i.drawInstructions();
                    w.setSize(new Dimension(1000,1000));
                    w.drawWin();
                    window.add(w);
                    window.validate();
                    window.repaint();
                }
            }
            //canMove=m.canMove();
        }
        /*if(m.getHighest()!=2048 && canMove==false){
            l.setSize(new Dimension(1000,1000));
            l.drawLose();
            window.add(l);
            window.validate();
            window.repaint();
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
