import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

/**
 * The Tile class used to draw tiles.
 */
public class Tile extends JPanel {
    private int x;
    private int y;
    private int val;
    private Random rand= new Random();
    private Color[] colors={new Color(0xfff4d3),new Color(0xFFE4C3), //2,4
            new Color(0xffdac3),new Color(0xe7bf8e), //8,16
            new Color(0xe7b08e), new Color(0xffc4c3), //32,64
            new Color(0xE7948e), new Color(0xbe7e56), //128,256
            new Color(0xbe5e56), new Color(0x9c3931), //512, 1024
            new Color(0x701710)//2048
    };


    /**
     * Instantiates a new Tile.
     *
     * @param x1 the x position of the new tile
     * @param y1 the y position of the new tile
     */
    public Tile(int x1, int y1){
        x=x1;
        y=y1;
        val=0;
    }

    /**
     * generates a new val for each tile that is instantiated
     */
    public void addVal(){
        double chance= rand.nextDouble();
        if(chance<0.9){
            val=2;
        }
        else if(chance>=0.9){
            val=4;
        }
    }

    /**
     * Draws the tile.
     */
    public void drawTile(){
        if(val==0){
            draw0();
        }
        else{
            drawOther();
        }
    }

    /**
     * Set val of the tile.
     *
     * @param toSet the to value that needs to be set
     */
    public void setVal(int toSet){
        val=toSet;
    }

    /**
     * draws the tile for other values greater than 0
     */
    private void drawOther(){
        this.repaint();
        //System.out.println("drawn "+val);
    }

    /**
     * Draws the 0 tile.
     */
    private void draw0(){
        this.repaint();
    }

    /**
     * Get val of the tile.
     *
     * @return the value of the tile
     */
    public int getVal(){
        return val;
    }

    /**
     * Paint component to draw the tiles
     * @param g the Graphics object used to draw the numbers in the tiles
     */
    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        if(val!=0){
            int n= (int) ((Math.log(val)/Math.log(2))-1);//Gets the power that 2 is raised to that equals the value of the tile, then subtracts one to get that index in the color array
            g.setColor(colors[n]);
            g.fillRect(x+30,y+30,60,60);
            if(val>=1024){
                g.setColor(Color.WHITE);
            }
            else{
                g.setColor(Color.BLACK);
            }
            Font tr = new Font("TimesRoman", Font.BOLD, 14);
            g.setFont(tr);
            g.drawString(String.valueOf(val),x+55,y+65);
        }
        else{
            g.setColor(Color.WHITE);
            g.fillRect(x+30,y+30,60,60);
        }
    }

    /**
     * Converts the tile's position and value into a readable string
     * @return a string with the value and position of the tile
     */
    @Override
    public String toString(){
        String str= ("x:"+x +" y:"+ y+ " val:"+val);
        return str;
    }
}

