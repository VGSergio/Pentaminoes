/* Board class. A board has a certain number of rows and columns 
 * filled with squares of a given size
 */
package puzzle2dpentamino;

import java.awt.*;
import javax.swing.*;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class Board extends JPanel{
    
    private final int ROWS;
    private final int COLUMNS;
    private final Square[] SQUARES;
    private final int SIDE;
    private boolean Solving;
    
    /**
     * Board constructor
     * @param rows
     * @param columns 
     */
    public Board(int rows, int columns){
        ROWS = rows;
        COLUMNS = columns;
        SQUARES = new Square[rows*columns];
        SIDE = new Square(0,0).getSide();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int position = (i*columns)+j;
                SQUARES[position] = new Square(i*SIDE, j*SIDE);
            }
        }
        Solving = false;
    }
        
    /**
     * Rows getter
     * @return int
     */
    public int getRows(){
        return ROWS;
    }
        
    /**
     * Columns getter
     * @return int
     */
    public int getColumns(){
        return COLUMNS;
    }
    
    /**
     * Returns the width of the board
     * @return int
     */
    @Override
    public int getWidth(){
        return COLUMNS*SIDE;
    }
    
    /**
     * Returns the height of the board
     * @return int
     */
    @Override
    public int getHeight(){
        return ROWS*SIDE;
    }

    /**
     * Returns solving status
     * @return boolean
     */
    public boolean isSolving() {
        return Solving;
    }

    /**
     * Sets solving status
     * @param Solving 
     */
    public void setSolving(boolean Solving) {
        this.Solving = Solving;
    }
    
    /**
     * Blocks the square placed in the given coordinates
     * @param x
     * @param y 
     */
    public void ChangeSquareStatus(int x, int y){
        y /= SIDE;
        x /= SIDE;
        int position = y*COLUMNS+x;
        if(SQUARES[position].isBlocked()){
            SQUARES[position].setColor(Color.WHITE);
            SQUARES[position].setBlocked(false);
        }
        else {
            SQUARES[position].setColor(Color.BLACK);
            SQUARES[position].setBlocked(true);
        }
    }
    
    /**
     * patata method
     * @param column
     * @param row 
     */
    public void patata(int column, int row){
        column /= SIDE;
        row /= SIDE;
        int position = row*COLUMNS+column;
        boolean fits = true;
        int[] perspective = null;
        Color color = null;
        
        Piece piece = new Piece();
        color = piece.getColor(2);
        perspective = piece.getPerspective(2, 2); //First: piece id, second: perspective
        int i=0;
        while(i<5 && fits){
            int row2 = perspective[2*i];
            int column2 = perspective[2*i+1];
            int position2 = position + row2*COLUMNS+column2;
            fits = pieceFits(position, position2);
            i++;
        }
        
        if (fits){
                for (i=0; i<5; i++){
                    int row2 = perspective[2*i];
                    int column2 = perspective[2*i+1];
                    int position2 = position + row2*COLUMNS+column2;
                    SQUARES[position2].setColor(color);
                    SQUARES[position2].setBlocked(true);
                }
            }
    }
    
    /**
     * Computes whether the new piece fits in the board or not
     * @param position
     * @param position2
     * @return boolean
     */
    public boolean pieceFits(int position, int position2){
        if((position2) > (getColumns()*getRows())-1){   //The piece doesn't fit 
            return false;                               //in the board array
            
        } else if(SQUARES[position2].isBlocked()){      //The square you want to use 
            return false;                               //is already used by another piece
            
        } else if(SQUARES[position].isBlocked() && position==position2){
            return false;
            
        } else {
            return (position%COLUMNS) <= (position2%COLUMNS);       //The piece doesn't exceed
        }                                                           //the right end   
    }
    
    @Override
    public void paint(Graphics g) {
        for (Square SQUARE1 : SQUARES) {
            SQUARE1.paint(g);
        }
    }
    
}