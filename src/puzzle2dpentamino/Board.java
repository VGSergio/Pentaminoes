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
     * @param position
     * @param usedpieces
     * @param maxpieces
     * @return 
     */
    public boolean isSolved(int position, int usedpieces, int maxpieces){
//        if(position > getSquaresAmount()-PIECESQUARES){
//            return true;
//        } else if(usedpieces==maxpieces){
//            return true;
//        } else {
//            return getSquaresOccupied() > getSquaresAmount()-PIECESQUARES;
//        }
        boolean solved = true;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int position2 = (i*COLUMNS)+j;
                if(!SQUARES[position2].isBlocked()){
                    solved = false;
                }
            }
        }
        return solved;
    }
    
    public void Solve(JFrame game, int position, boolean[] pieces, int usedpieces, int maxpieces){
        if(isSolved(position, usedpieces, maxpieces)){
            System.out.println("Solved");
            if(Speed==0){
                game.repaint();
            }
            if(getSquaresOccupied() > best){
                best = getSquaresOccupied();
                if(!Solutions.isEmpty())
                    Solutions.clear();
                Solutions.add(this);
            } else if(getSquaresOccupied() == best) {
                Solutions.add(this);  
            }
        } else {
            if(position < getSquaresAmount()){
                if(usedpieces < maxpieces){
                    for(int piece=0; piece<pieces.length; piece++){
                        if(!pieces[piece] && isSolving()){
                            int[][] perspectives = new Piece().getPerspectives(piece);
                            for(int i=0; i<perspectives.length; i++){
                                int[] perspective = getPerspective(perspectives, i);
                                if(pieceFits(position, perspective)){
                                    int[] positions = getSquaresPositions(position, perspective);
                                    Color color = new Piece().getColor(piece);

                                    paintNewPiece(positions, color);
                                    if(Speed>0){
                                        try {
                                            Thread.sleep(Speed);
                                            game.repaint();
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                    if(!ObviousBlockExists2(position,positions)){
                                        pieces[piece] = true;
                                        usedpieces++;

                                        Solve(game, position+1,pieces, usedpieces, maxpieces);

                                        removePiece(positions);
                                        if(Speed>0){
                                            try {
                                                Thread.sleep(Speed);
                                                game.repaint(); 
                                            } catch (InterruptedException ex) {
                                                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        usedpieces--;
                                        pieces[piece] = false;
                                    } else {
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
                    }
                    Solve(game, position+1, pieces, usedpieces, maxpieces);
                }
            }
        }
    }
    
    private int[] getLowestRightPieceSquare(int[] positions){
        int[] pos = new int[2];
        int brow=-1; int bcolumn=-1;
        for(int i=0; i<positions.length; i++){
            int position = positions[i];
            int row = position/COLUMNS;
            int column = position%COLUMNS;
            if(row>brow){
                pos[0] = positions[i];
            }
            if(column>bcolumn){
                pos[1] = positions[i];
            }
        }
        return pos;
    }
    
    private boolean ObviousBlockExists2(int position, int[] positions){
        int row = position/COLUMNS;
        int column = position%COLUMNS;
        int position2[] = getLowestRightPieceSquare(positions);
        int brow = position2[0]/COLUMNS;
        int rcolumn = position2[1]%COLUMNS;
        int i = 0;
        int j = 0;
        boolean blocking = false;
        int nposition;
        if(column+1 == COLUMNS-1 && !SQUARES[position+1].isBlocked() && SQUARES[position].isBlocked() && SQUARES[position+COLUMNS].isBlocked()){
            blocking = true;
        }
        if(column == 0  && SQUARES[position+1].isBlocked() && !SQUARES[position].isBlocked()){
            blocking = true;
        }
        if(row+1 == ROWS-1 && !SQUARES[position+COLUMNS].isBlocked() && SQUARES[position].isBlocked()){
            blocking = true;
        }
        if(row == 0 && SQUARES[position+COLUMNS].isBlocked() && !SQUARES[position].isBlocked()){
            blocking = true;
        }
        System.out.print("First square: "+position+ "\tRow: "+row+"\tColumn: "+column+"\n");
        while (row<=brow && !SQUARES[position+COLUMNS*i].isBlocked() && !blocking){
            while(column<=rcolumn && !SQUARES[position+COLUMNS*i+j].isBlocked() && !blocking) {
                nposition = position+COLUMNS*i+j;
                System.out.print(nposition+"\t");
                boolean next = ObviousBlockExists2(nposition+1, positions);
                blocking = !next;
                
                j++;
                column++;
            }
                
            column = position%COLUMNS;;
            j=0;
            i++;
            row++;
        }
        
        while (row<=brow && SQUARES[COLUMNS*row].isBlocked() && !blocking){
            while(SQUARES[COLUMNS*row].isBlocked()){
                row++;
            }
            while(column<=rcolumn && !SQUARES[COLUMNS*brow+j].isBlocked() && !blocking) {
                nposition = COLUMNS*row+j;
                boolean next = ObviousBlockExists2(nposition, positions);
                blocking = !next;
                
                j++;
                column++;
            }  
            column = position%COLUMNS;;
            j=0;
        }
        
//        System.out.print("\n");
        return blocking;
    }
    private boolean ObviousBlockExists(int position, int[] positions){
        int row = position/COLUMNS;
        int column = position%COLUMNS;
        int position2[] = getLowestRightPieceSquare(positions);
        int brow = position2[0]/COLUMNS;
        int rcolumn = position2[1]%COLUMNS;
        int i = 0;
        int j = 0;
        boolean blocking = false;
        int nposition;
        int cont=0;
        
        while (row<=brow && !blocking){
            while(column<=rcolumn && !blocking) {
                nposition = position+COLUMNS*i+j;
                if (!SQUARES[nposition].isBlocked()){
                    if(!SQUARES[position+1].isBlocked() && !SQUARES[position+COLUMNS].isBlocked()){
                        blocking = true;
                    }
                }
                j++;
                column++;
            }
            column = position%COLUMNS;
            j=0;
            i++;
            row++;
        }
        return blocking;
    }
    
    private int getNextEMptySquare(){
        int position = 0, i=0, j=0;
        boolean found = false;
        while(i<ROWS && !found) {
            while(j<COLUMNS && !found) {
                position = (i*COLUMNS)+j;
                if (!SQUARES[position].isBlocked()){
                   found=true; 
                }
                j++;
            }
            i++;
        }
        return position;
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
        int end = getSquaresAmount();
        if(position<=end-PIECESQUARES){
            position++;
            while(position<end-PIECESQUARES){
                if(!SQUARES[position].isBlocked()){
                    break;
                }
            position++;
            }
            return position;
        } else {
            return 0;
        }
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
            SQUARES[nposition[i]].setBlocked(false);
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