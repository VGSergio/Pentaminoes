/* Board class. A board has a certain number of rows and columns 
 * filled with squares of a given size
 */
package puzzle2dpentamino;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static puzzle2dpentamino.Square.SIDE;
import static puzzle2dpentamino.Piece.PIECESQUARES;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class Board extends JPanel{
    
    private final int ROWS;
    private final int COLUMNS;
    private final Square[] SQUARES;
    
    private int Speed;
    private int best = 0;
    private boolean Solving;
    private ArrayList<Board> Solutions = new ArrayList();
    private int UsedPieces = 0;
    
    /**
     * Board constructor
     * @param rows
     * @param columns 
     */
    public Board(int rows, int columns){
        ROWS = rows;
        COLUMNS = columns;
        SQUARES = new Square[rows*columns];
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
    
    public int getSquaresAmount(){
        return ROWS*COLUMNS;
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
    
    @Override
    public void paint(Graphics g) {
        for (Square SQUARE1 : SQUARES) {
            SQUARE1.paint(g);
        }
    }
    
    public void setSpeed(int miliseconds){
        Speed = miliseconds;
    }
    
    /**
     * Returns wheter the board is solved or not
     * @return 
     */
    public boolean isSolved(){
        int occ = getSquaresOccupied();
        return occ > getSquaresAmount()-PIECESQUARES; //No more pieces can be used
    }
    
    public void Solve(JFrame game, int position, boolean[] pieces){
        if(position > getSquaresAmount()-PIECESQUARES || isSolved()){
            if(getSquaresOccupied() > best){
                best = getSquaresOccupied();
                if(!Solutions.isEmpty())
                    Solutions.clear();
                Solutions.add(this);
            } else if(getSquaresOccupied() == best) {
                Solutions.add(this);  
            }
        } else {
            for(int i=0; i<pieces.length; i++){
                if(!pieces[i]){
                    int perspectives[][] = new Piece().getPerspectives(i);
                    for (int j=0; j<perspectives.length; j++) {
                        int[] perspective = getPerspective(perspectives, j);
                        if(pieceFits(position, perspective)){
                            int[] positions = getSquaresPositions(position, perspective);
                            Color color = new Piece().getColor(i);
                            try {
                                Thread.sleep(Speed);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            paintNewPiece(positions, color);
                            game.repaint();
                            boolean[] npieces = new boolean[pieces.length];
                            System.arraycopy(pieces, 0, npieces, 0, pieces.length);
                            npieces[i]=true;
                            int nposition = getNextPosition(position);
                            
                            Solve(game, nposition, npieces);
                            
                            try {
                                Thread.sleep(Speed);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            removePiece(positions);
                            game.repaint();
                        }
                    }
                }
            }
            Solve(game, position+1, pieces);
        }
        
    }
    
    private int[] getSquaresPositions(int position, int[] perspective){
        int[] positions = new int[perspective.length/2];
        for(int i=0; i<PIECESQUARES; i++){
            //int nrow = perspective[2*i];  int ncolumn = perspective[2*i+1];
            //int position2 = position + nrow*COLUMNS+ncolumn;
            int position2 = position + perspective[2*i]*COLUMNS+perspective[2*i+1];
            positions[i] = position2;
        }
        return positions;
    }
    
    private int getNextPosition(int position){
        int i = position + 1;
        int end = getSquaresAmount();
        while(i<end){
            if(!SQUARES[i].isBlocked()){
                break;
            }
            i++;
        }
        return i;
    }
    
    /**
     * Computes whether the new piece fits in the board or not
     * @param position
     * @param perspective
     * @return boolean
     */
    private boolean pieceFits(int position, int[] perspective){
        boolean fits = true;
        int i=0;
        while(i<PIECESQUARES && fits){   //Checks if the piece fits and gets the necessary data to paint it
//            int nrow = perspective[2*i];  int ncolumn = perspective[2*i+1];   
//            int position2 = position + nrow*COLUMNS+ncolumn;
            int position2 = position + perspective[2*i]*COLUMNS+perspective[2*i+1];
            if(position2 >= getSquaresAmount()){   //The piece doesn't fit 
                fits = false;                               //in the board array
            
            } else if(SQUARES[position2].isBlocked()){      //The square you want to use 
                fits = false;                               //is already used by another piece
                
            } else {
                fits = (position%COLUMNS) <= (position2%COLUMNS);       //The piece doesn't exceed
            }                                                           //the right end 
            
            i++; 
        }    
        return fits;
    }
    
    /**
     * Paint a new piece onto the board
     * @param nposition
     * @param color 
     */
    private void paintNewPiece(int[] nposition, Color color){
        for (int i=0; i<nposition.length; i++){
            SQUARES[nposition[i]].setColor(color);
            SQUARES[nposition[i]].setBlocked(true);
        }
    }
    
    /**
     * Removes a piece from the board
     * @param nposition 
     */
    private void removePiece(int[] nposition){
        for (int i=0; i<nposition.length; i++){
            SQUARES[nposition[i]].setColor(Color.WHITE);
            SQUARES[nposition[i]].setBlocked(true);
        }
    }
    
    private int getSquaresOccupied(){
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
    
    /**
     * Returns the selected perspective of the specified piece
     * @param perspectives
     * @param persepective
     * @return int[]
     */
    private int[] getPerspective(int[][] perspectives,int persepective){
        int[] p = new int[2*PIECESQUARES];     //2 Coordinates, 5 squares, perspective array

        for (int i=0; i<perspectives.length; i++){
            if(i==persepective){
                System.arraycopy(perspectives[i], 1, p, 1, perspectives[i].length - 1);
            }
        }
        
        return  p;
    }
}