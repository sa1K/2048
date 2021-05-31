import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Matrix class.
 */
public class Matrix extends JPanel {
    private Tile[][] board;
    private ScoreBoard scoreBoard;
    private int score=0;
    private ArrayList<Integer> zeroRows=new ArrayList<>();
    private ArrayList<Integer> zeroCols=new ArrayList<>();
    private loseScreen l;
    private JFrame window;
    private instructions i;

    /**
     * Instantiates a new board
     *
     * @param lose   the losescreen
     * @param window the window
     * @param s      the scoreboard to track the score
     * @param i      the instructions class used to draw the instructions
     */
    public Matrix(loseScreen lose, JFrame window, ScoreBoard s, instructions i){
        l=lose;
        this.window=window;
        this.i=i;
        scoreBoard=s;
        board=new Tile[4][4];
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++){
                board[r][c]=new Tile(r*120,c*120);
            }
        }
    }

    /**
     * finds and stores the row index and column index of all places that are open
     */
    private void findZeros(){
        zeroCols.clear();
        zeroRows.clear();
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++){
                if(board[r][c].getVal()==0){
                    if(zeroCols.contains(r)==false){
                        zeroCols.add(r);
                    }
                    zeroRows.add(c);
                }
            }
        }
    }

    /**
     * Adds a new tile to a random spot.
     *
     * @param rand the random object used to pick a row and a column
     */
    public void addTile(Random rand){
        findZeros();
        int r=zeroRows.get(rand.nextInt(zeroRows.size()));
        int c=zeroCols.get(rand.nextInt(zeroCols.size()));
        while(board[c][r].getVal()!=0){
            r=zeroRows.get(rand.nextInt(zeroRows.size()));
            c=zeroCols.get(rand.nextInt(zeroCols.size()));
        }
        //System.out.println("r:"+r+" c:"+c);
        board[c][r].addVal();
    }

    /**
     * Draws grid and the lose screen if needed.
     *
     * @param panel the panel used for adding elements to the screen
     * @param g     the grid object used to draw the grid lines
     */
    public void drawWindow(JPanel panel, Grid g){
        if(getHighest()!=2048 && canMove()==false){
            i.setSize(new Dimension(1000,1000));
            i.flip();
            i.drawInstructions();
            l.setSize(new Dimension(1000,1000));
            l.drawLose();
            window.add(l);
            window.validate();
            window.repaint();
        }
        scoreBoard.setSize(new Dimension(1000,1000));
        scoreBoard.drawScore(score);
        panel.add(scoreBoard);
        g.setSize(new Dimension(490,490));
        g.drawLines();
        window.add(g);
        window.validate();
        window.repaint();
        for(int i=0; i<board.length; i++){
            for(int s=0; s<board[i].length; s++){
                board[s][i].setSize(new Dimension(1000,1000));
                board[s][i].drawTile();
                panel.add(board[s][i]);
            }
        }
    }

    /**
     * Gets highest int.
     *
     * @return the highest int
     */
    public int getHighest(){
        int highest=0;
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++){
                if(board[c][r].getVal()>highest){
                    highest=board[c][r].getVal();
                }
            }
        }
        return highest;
    }

    /**
     * Checks if a move can be made
     *
     * @return whether or not an the tiles can be moved
     */
    public boolean canMove(){
        if(getHighest()==2048){
            return false;
        }
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++){
                if(board[c][r].getVal()==0){
                    return true;
                }
                if(c!=3 && board[c][r].getVal()==board[c+1][r].getVal()){
                    return true;
                }
                if(c!=0 && board[c][r].getVal()==board[c-1][r].getVal()){
                    return true;
                }
                if(r!=0 && board[c][r].getVal()==board[c][r-1].getVal()){
                    return true;
                }
                if(r!=3 && board[c][r].getVal()==board[c][r+1].getVal()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Moves up all the tiles.
     *
     * @param rand  the random object used to generate random numbers
     * @param panel the panel used to draw tiles
     * @param g     the grid object that needs to be drawn
     */
    public void moveUp(Random rand, JPanel panel, Grid g){
        Tile[] temp;
        for(int i=0; i<board[0].length; i++){
            temp=getCol(i);
            shiftUpLeft(temp);
        }
        addTile(rand);
        drawWindow(panel,g);
    }

    /**
     * Moves down all the tiles.
     *
     * @param rand  the random object used to generate random numbers
     * @param panel the panel used to draw tiles
     * @param g     the grid object that needs to be drawn
     */
    public void moveDown(Random rand, JPanel panel, Grid g){
        Tile[] temp;
        for(int i=0; i<board[0].length; i++){
            temp=getCol(i);
            shiftDownRight(temp);
        }
        addTile(rand);
        drawWindow(panel,g);
    }

    /**
     * Moves left all the tiles.
     *
     * @param rand  the random object used to generate random numbers
     * @param panel the panel used to draw tiles
     * @param g     the grid object that needs to be drawn
     */
    public void moveLeft(Random rand, JPanel panel,Grid g){
        Tile[] temp;
        for(int i=0; i<board.length; i++){
            temp=getRow(i);
            shiftUpLeft(temp);
        }
        addTile(rand);
        drawWindow(panel,g);
    }

    /**
     * Moves right all the tiles.
     *
     * @param rand  the random object used to generate random numbers
     * @param panel the panel used to draw tiles
     * @param g     the grid object that needs to be drawn
     */
    public void moveRight(Random rand, JPanel panel, Grid g){
        Tile[] temp;
        for(int i=0; i<board.length; i++){
            temp=getRow(i);
            shiftDownRight(temp);
        }
        addTile(rand);
        drawWindow(panel,g);
    }

    /**
     * Shifts the the row of tiles such that all the tiles w/ value zero are at the end, works for if you need to move right or down
     *
     * @param row the row that needs to manipulated.
     */
    private void shiftDownRight(Tile[] row){
        int ptr2=0;
        int ptr1=0;
        int posPtr=nonZeroFinder(row, row.length-1);//the first tile from the end with a nonzero val
        while(posPtr!=-1){
            if(row[row.length-1].getVal()==0){//if the end tile of the row is empty switch with the first nonzero value
                row=swap(row,3, posPtr);
                posPtr=3;
            }
            ptr2=nonZeroFinder(row, posPtr-1);// checks if there are any other nonzero tiles that are after the one identified earlier

            if(ptr2!=-1){//if there are any other nonzero tiles
                if(row[ptr2].getVal()==row[posPtr].getVal()){// if the two tiles are equal merge
                    row[posPtr].setVal(row[posPtr].getVal()+row[ptr2].getVal());
                    row[ptr2].setVal(0);
                    score=score+row[posPtr].getVal()+row[ptr2].getVal();
                    ptr1=nonZeroFinder(row, ptr2);//checks if there is a third number that can be moved next to the newly combined tile
                    if(ptr1!=-1){
                        row=swap(row,posPtr-1, ptr1);
                    }
                    posPtr--;
                }
                else{//otherwise bring the other nonzero tile close to the one moved earlier
                    if (ptr2!=posPtr-1){
                        row= swap(row, ptr2, posPtr-1);
                    }
                    posPtr--;
                }
            }
            else{
                posPtr--;
            }
            // System.out.println(posPtr);
        }
    }

    /**
     * Shifts the the row of tiles such that all the tiles w/ value zero are at the end, works for if you need to move up or left
     *
     * @param row the row that needs to manipulated.
     */
    private void shiftUpLeft(Tile[] row){
        int ptr2=0;
        int ptr1=0;
        int posPtr=nonZeroFinder2(row, 0);//finds the first nonzero from the beginning of the array
        while(posPtr<row.length && posPtr>=0){
            if(row[0].getVal()==0){
                row=swap(row,0, posPtr);
                posPtr=0;
            }
            ptr2=nonZeroFinder2(row, 1+posPtr);
            if(ptr2!=-1){
                if(row[ptr2].getVal()==row[posPtr].getVal()){
                    row[posPtr].setVal(row[posPtr].getVal()+row[ptr2].getVal());
                    row[ptr2].setVal(0);
                    score=score+row[posPtr].getVal()+row[ptr2].getVal();
                    ptr1=nonZeroFinder2(row, ptr2);
                    if(ptr1!=-1){
                        row=swap(row,posPtr+1, ptr1);
                    }
                    posPtr++;
                }
                else{
                    if (ptr2!=posPtr+1){
                        row= swap(row, ptr2, posPtr+1);
                    }
                    posPtr++;
                }
            }
            else{
                posPtr++;
            }
        }
    }

    /**
     * Finds and return the index of the first nonzero tile at or after the specified limiting index in the row, used in method shiftDownRight
     *
     * @param arr the array that that is being searched
     * @param limit where you want to start searching from
     * @return the index of the first nonzero element in arr or -1 if nothing can be found
     */
    private int nonZeroFinder2(Tile[] arr, int limit){
        for(int i=limit; i<arr.length; i++){
            if(arr[i].getVal()!=0){
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds and return the index of the first nonzero tile before the specified limiting index in the row, used in method shiftUpLeft
     *
     * @param arr the array that that is being searched
     * @param limit where you want to start searching from
     * @return the index of the first nonzero element in arr or -1 if nothing can be found
     */
    private int nonZeroFinder(Tile[] arr, int limit){
        for(int i=limit; i>=0; i--){
            if(arr[i].getVal()!=0){
                return i;
            }
        }
        return -1;
    }

    /**
     * Swaps the values of two tiles
     *
     * @param arr the array where the swapping is occurring
     * @param zeroIndex the first of the two items that is being swapped
     * @param other the other of the two items that is being swapped
     * @return the modified tile array
     */
    private Tile[] swap(Tile[] arr, int zeroIndex, int other){
        int temp=arr[zeroIndex].getVal();
        arr[zeroIndex].setVal(arr[other].getVal());
        arr[other].setVal(temp);
        return arr;
    }

    /**
     * gets the specified column of the array
     *
     * @param col the column number that needs to be gotten
     * @return the 1d column of tiles
     */
    private Tile[] getCol(int col){
        return board[col];
    }

    /**
     * gets the specified row of the array
     *
     * @param row the row number that needs to be gotten
     * @return the 1d row of tiles
     */
    private Tile[] getRow(int row){
        Tile[] colArr= new Tile[board[0].length];
        for(int a = 0; a < board.length; a++){
            colArr[a] = board[a][row];
        }
        return colArr;
    }
}
