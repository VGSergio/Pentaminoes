/* Board class. A board has a certain number of rows and columns 
 * filled with squares of a given size
 */
package puzzle2dpentamino;

import java.awt.*;
import java.util.ArrayList;
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
    private ArrayList<Board> Solutions;
    
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
        int squares = 5;    //5 -> a piece is formed by 5 squares
        boolean fits = true;
        int[] perspective = null;
        Color color = null;
        int nposition[] = new int[squares];
        
        Piece piece = new Piece();
        color = piece.getColor(2);
        perspective = piece.getPerspective(2, 2); //First: piece id, second: perspective
        
        int i=0;
        while(i<squares && fits){   //Checks if the piece fits and gets the necessary data to paint it
            int nrow = perspective[2*i];
            int ncolumn = perspective[2*i+1];
            nposition[i] = position + nrow*COLUMNS+ncolumn;
            fits = pieceFits(position, nposition[i]);
            i++; 
        }
        
        if (fits){  //If the new piece fits paints it.
            paintNewPiece(nposition, color);
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
    
    /**
     * Paint a new piece onto the board
     * @param nposition
     * @param color 
     */
    public void paintNewPiece(int[] nposition, Color color){
        for (int i=0; i<nposition.length; i++){
            SQUARES[nposition[i]].setColor(color);
            SQUARES[nposition[i]].setBlocked(true);
        }
    }
    
    /**
     * Removes a piece from the board
     * @param nposition 
     */
    public void removePiece(int[] nposition){
        for (int i=0; i<nposition.length; i++){
            SQUARES[nposition[i]].setColor(Color.WHITE);
            SQUARES[nposition[i]].setBlocked(true);
        }
    }
    
    @Override
    public void paint(Graphics g) {
        for (Square SQUARE1 : SQUARES) {
            SQUARE1.paint(g);
        }
    }
    
    /**
     * Returns wheter the board is full(Last square reached) or not
     * @param nposition
     * @return 
     */
    public boolean isFinalPosition(int[] nposition){
        boolean end = false;
        for(int i=0; i<nposition.length; i++){
            if (nposition[i]==(ROWS*COLUMNS-1)){
                end = true;
            }
        }
        return end;
    }
    
    public int squaresOccupied(){
        int cont = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int position = (i*COLUMNS)+j;
                if(SQUARES[position].isBlocked()){
                    cont++;
                }
            }
        }
        return cont;
    }
    
}