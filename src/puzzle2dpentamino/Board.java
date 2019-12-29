/* Board class. A board has a certain number of rows and columns 
 * filled with squares of a given size
 */
package puzzle2dpentamino;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JPanel;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class Board extends JPanel{
    
    private final int ROWS;
    private final int COLUMNS;
    private final Square[] SQUARES;
    private final int SIDE;
    private final int PIECESQUARES;
    
    private int Speed;
    private boolean Solving;
    private final ArrayList<Board> SOLUTIONS = new ArrayList();
    private int Iterations = 0;
    private int Solutions = 0;
    private int blockCheckCt;
    private final int[] blockCheck;
    private boolean StatusMessage;
    private int best = 0;
    
    /**
     * Board constructor
     * @param rows
     * @param columns 
     */
    public Board(int rows, int columns){
        PIECESQUARES = new Piece().getPieceSquares();
        SIDE = new Square(0,0).getSide();
        
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
        blockCheck = new int[SQUARES.length];
        StatusMessage = true;
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
     * Returns the amount of squares that form the board
     * @return 
     */
    public int getSquaresAmount(){
        return ROWS*COLUMNS;
    }
    
    /**
     * Returns true if a square is blocked.
     * @return 
     */
    public boolean hasBlockedSquares(){
        for(int i=0; i<getSquaresAmount(); i++){
            if(SQUARES[i].isBlocked()){
                return true;
            }
        }
        return false;
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
    
    /**
     * Solving speed setter
     * @param miliseconds 
     */
    public void setSpeed(int miliseconds){
        Speed = miliseconds;
    }
    
    public void setStatusMessage(boolean status){
        StatusMessage = status;
    }
    
    /**
     * Returns wheter the board is solved or not
     * @param position
     * @param usedpieces
     * @param maxpieces
     * @return 
     */
    public boolean isSolved(int position, int usedpieces, int maxpieces){
        if(position > getSquaresAmount()-PIECESQUARES){
            return true;
        } else if(usedpieces==maxpieces){
            return true;
        } else if(SQUARES[getSquaresAmount()-1].isBlocked()){
            return true;
        } else {
            return getSquaresOccupied() > getSquaresAmount()-PIECESQUARES;
        }
    }
    
    public Board[] Solve(GUI game, Board board, int pos, boolean[] pieces, int usedpieces, int maxpieces){
        if(isSolving()){
            if(usedpieces==maxpieces){
                SOLUTIONS.add(board.cloneBoard());
                Solutions++;
                if(Speed==0){
                    try {
                        Thread.sleep(10);
                        game.repaint();
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                for(int i=pos; i<getSquaresAmount(); i++){
                    if(!hasLeftEmptySquares(i)){
                        for(int piece=0; piece<pieces.length; piece++){
                            if(!pieces[piece]){
                                int[] perspectives = new Piece().getPerspectives(piece);
                                for(int pers=0; pers<perspectives.length/10; pers++){
                                    int[] perspective = getPerspective(perspectives, pers);
                                    if(pieceFits(i, perspective)){
                                        int[] positions = getSquaresPositions(i, perspective);
                                        Color color = new Piece().getColor(piece);
                                        AddPiece(positions, color);
                                        Iterations++;
                                        if(StatusMessage){
                                           String[] message = getMessage();
                                            game.setMessage(message[0], message[1], message[2]); 
                                        }

                                        if(Speed>0){
                                            try {
                                                Thread.sleep(Speed);
                                                game.repaint();
                                            } catch (InterruptedException ex) {
                                                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }

                                        if(!ObviousBlockExists()){
                                            pieces[piece] = true;
                                            usedpieces++;
                                            Solve(game, board, i+1, pieces, usedpieces, maxpieces);

                                            usedpieces--;
                                            pieces[piece] = false;
                                        }

                                        removePiece(positions);
                                        if(Speed>0){
                                            try {
                                                Thread.sleep(Speed);
                                                game.repaint();
                                            } catch (InterruptedException ex) {
                                                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(pos==0){
            Board[] solutions = new Board[SOLUTIONS.size()];
            for(int i=0; i<SOLUTIONS.size(); i++){
                solutions[i] = SOLUTIONS.get(i);
            }
            System.out.println(Iterations+" iterations done.");
            System.out.println(Solutions+" solutions found(s).");
            return solutions;
        }
        return null;
    }
    
    public Board[] Solve(GUI game, Board board, int pos, boolean[] pieces/*, int usedpieces, int maxpieces*/){
        if(isSolving()){
            if(board.getSquaresOccupied()>best){
                best=board.getSquaresOccupied();
                SOLUTIONS.clear();
                SOLUTIONS.add(board.cloneBoard());
                Solutions = 1;
            } else if(board.getSquaresOccupied()==best){
                SOLUTIONS.add(board.cloneBoard());
                Solutions++;
            }
            for(int i=pos; i<getSquaresAmount(); i++){
                if(!hasLeftEmptySquares(pos)){
                    for(int piece=0; piece<pieces.length; piece++){
                        if(!pieces[piece]){
                            int[] perspectives = new Piece().getPerspectives(piece);
                            for(int pers=0; pers<perspectives.length/10; pers++){
                                int[] perspective = getPerspective(perspectives, pers);
                                if(pieceFits(i, perspective)){
                                    int[] positions = getSquaresPositions(i, perspective);
                                    Color color = new Piece().getColor(piece);
                                    AddPiece(positions, color);
                                    Iterations++;
                                    if(StatusMessage){
                                       String[] message = getMessage();
                                        game.setMessage(message[0], message[1], message[2]); 
                                    }

                                    try {
                                        Thread.sleep(Speed);
                                        game.repaint();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }

//                                    if(!ObviousBlockExists()){
                                        pieces[piece] = true;
                                        Solve(game, board, i+1, pieces);

                                        pieces[piece] = false;
//                                    }

                                    removePiece(positions);
                                    try {
                                        Thread.sleep(Speed);
                                        game.repaint();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(pos==0){
            Board[] solutions = new Board[SOLUTIONS.size()];
            for(int i=0; i<SOLUTIONS.size(); i++){
                solutions[i] = SOLUTIONS.get(i);
            }
            System.out.println(Iterations+" iterations done.");
            System.out.println(Solutions+" solutions found(s).");
            System.out.println("Best solution found: "+best+" squares occupied");
            return solutions;
        }
        return null;
    }
    
    private boolean ObviousBlockExists(){
        blockCheckCt++;
        int forcedEmptyCt = 0;
        for(int r=0; r<ROWS; r++)
            for(int c=0; c<COLUMNS; c++){
                int blockSize = countEmptyBlock(r,c);
                if (blockSize % 5 == 0)
                  continue;
                forcedEmptyCt += blockSize % 5;
                if (forcedEmptyCt < 5)
                  return true;
            }
        return false;
    }
    
    private int countEmptyBlock(int r, int c) {  // Find the size of one empty region on the board; recursive routine called by obviousBlockExists.
        if(blockCheck[r*COLUMNS+c]==blockCheckCt || SQUARES[r*COLUMNS+c].isBlocked()){
            return 0;
        }
        int c1 = c, c2 = c;
        while (c1 > 0 && !SQUARES[(r*COLUMNS)+(c1-1)].isBlocked())
           c1--;
        while (c2 < COLUMNS-1 && !SQUARES[(r*COLUMNS)+(c2+1)].isBlocked())
           c2++;
        for (int i = c1; i <= c2; i++)
            blockCheck[r*COLUMNS+i] = blockCheckCt;
        int ct = c2 - c1 + 1;
        if (r > 1)
           for (int i = c1; i <= c2; i++)
              ct += countEmptyBlock(r-1,i);
        if (r < ROWS-1)
           for (int i = c1; i <= c2; i++)
              ct += countEmptyBlock(r+1,i);
        return ct;
      }
    
    /**
     * Return the loweste row and rightest column a piece occupies
     * @param positions
     * @return 
     */
    private int[] getLowestRightPieceSquare(int[] positions){
        int[] pos = new int[2];
        int brow=-1; int bcolumn=-1;
        for(int i=0; i<positions.length; i++){
            int position = positions[i];
            int row = position/COLUMNS;
            int column = position%COLUMNS;
            if(row>brow){
                brow = row;
                pos[0] = row;
            }
            if(column>bcolumn){
                bcolumn = column;
                pos[1] = column;
            }
        }
        return pos;
    }
    
    /**
     * Returns true if unnfillable areas have been left
     * @param position
     * @param positions
     * @return 
     */
    private boolean ObviousBlockExists(int position, int[] positions) {
        int upRow = position/COLUMNS;
        int leftColumn = position%COLUMNS;
        int lowRightPos[] = getLowestRightPieceSquare(positions);
        int downRow = lowRightPos[0];
        int rightColumn = lowRightPos[1];
        boolean obvious = false;
        for(int i=0; i <= downRow && !obvious; i++){
            for(int j=0; j <= rightColumn && !obvious; j++){
                if(!SQUARES[i*COLUMNS+j].isBlocked())
                    obvious = isObvious(i*COLUMNS+j);
            }
        }
        return obvious;
    }
           
    /**
     * ????????????????
     * @param position
     * @return 
     */
    private boolean isObvious(int position){
        return isObvious(position, 1, "None");
    }
    
    /**
     * ???????????????
     * @param position
     * @param emptySpaces
     * @param comesFrom
     * @return 
     */
    private boolean isObvious(int position, Integer emptySpaces, String comesFrom){
        if(emptySpaces >=PIECESQUARES){
            return false;
        }
        // Look right
        if((position+1)%COLUMNS != 0 && position+1 < getSquaresAmount() && !SQUARES[position+1].isBlocked()
                /*&& !comesFrom.equals("Right")*/){
            emptySpaces++;
            if(!isObvious(position+1, emptySpaces, "Left")) return false;
        }
        // Look down
        if(position+COLUMNS < getSquaresAmount() && !SQUARES[position+COLUMNS].isBlocked()
                /*&& !comesFrom.equals("Down")*/){
            emptySpaces++;
            if(!isObvious(position+COLUMNS, emptySpaces, "Up")) return false;
        }
        // Look up
        if(position-COLUMNS > 0 && !SQUARES[position-COLUMNS].isBlocked()
                && !comesFrom.equals("Up")){
            emptySpaces++;
            if(!isObvious(position-COLUMNS, emptySpaces, "Down")) return false;
        }
        // Look left
        if((position-1)%COLUMNS != COLUMNS-1 && position-1 > 0 && !SQUARES[position-1].isBlocked()
                && !comesFrom.equals("Left")){
            emptySpaces++;
            if(!isObvious(position-1, emptySpaces, "Right")) return false;
        }
        return true;
    }
            
    /**
     * Computes the squares position of a piece from the actual position 
     * depending on the selected perspective 
     * @param position
     * @param perspective
     * @return 
     */
    private int[] getSquaresPositions(int position, int[] perspective){
        int[] positions = new int[perspective.length/2];
        for(int i=0; i<PIECESQUARES; i++){
            //int nrow = perspective[2*i];  int ncolumn = perspective[2*i+1];
            //int position2 = position + nrow*COLUMNS+ncolumn;
            //int position2 = position + perspective[2*i]*COLUMNS+perspective[2*i+1];
            positions[i] = position + perspective[2*i]*COLUMNS+perspective[2*i+1];
        }
        return positions;
    }
    
    /**
     * Computes whether the new piece fits in the board or not
     * @param position
     * @param perspective
     * @return boolean
     */
    private boolean pieceFits(int position, int[] perspective){
        int i=0;
        while(i<PIECESQUARES){   //Checks if the piece fits and gets the necessary data to paint it
//            int nrow = perspective[2*i];  int ncolumn = perspective[2*i+1];   
//            int position2 = position + nrow*COLUMNS+ncolumn;
            int position2 = position + perspective[2*i]*COLUMNS+perspective[2*i+1];
            if(position2 >= getSquaresAmount()){            //The piece doesn't fit 
                return false;                               //in the board array
            } else if(SQUARES[position2].isBlocked()){      //The square you want to use 
                return false;                               //is already used by another piece
            } else if((position%COLUMNS) > (position2%COLUMNS)){    //The piece doesn't exceed
                return false ;                                      //the right end 
            }                       
            i++; 
        }    
        return true;
    }
    
    /**
     * Adds a new piece onto the board
     * @param nposition
     * @param color 
     */
    private void AddPiece(int[] nposition, Color color){
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
    
    /**
     * Counts the amount of occupied squares
     * @return 
     */
    private int getSquaresOccupied(){
        int cont = 0;
        for (int i = 0; i < getSquaresAmount(); i++) {
            if(SQUARES[i].isBlocked()){
                cont++;
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
    private int[] getPerspective(int[] perspectives,int persepective){
        int[] p = new int[2*PIECESQUARES];     //2 Coordinates, 5 squares, perspective array
        boolean found = false;
        
        for (int i=0; i<perspectives.length && !found; i+=(2*PIECESQUARES)){
            if(i/10==persepective){
                for(int j=0; j<p.length; j++){
                    p[j] = perspectives[i+j];
                }
                found=true;
            }
        }
        
        return  p;
    }
    
    /**
     * Clones a board
     * @return
     */
    private Board cloneBoard(){
        Board clone = new Board(ROWS,COLUMNS);
        for(int i=0; i<clone.getSquaresAmount(); i++){
            clone.SQUARES[i].setBlocked(SQUARES[i].isBlocked());
            clone.SQUARES[i].setColor(SQUARES[i].getColor());
        }
        return clone;
    }
    
    /**
     * Returns an array with the updated status message
     * @return message
     */
    private String[] getMessage(){
        String s1 = "";
        String s2 = Iterations+" iterations realised.";
        String s3 = Solutions +" Solution(s) found.";
        
        switch(Iterations/50000%4){
            case 0:
                s1 = "Solving   ";
                break;
            case 1:
                s1 = "Solving.  ";
                break;
            case 2:
                s1 = "Solving.. ";
                break;
            case 3: 
                s1 = "Solving...";
                break;
        }
        String[] message = {s1,s2,s3};
        return message;
    }
    
    /**
     * Returns wheter a square has left empty or not
     * @param position
     * @return 
     */
    private boolean hasLeftEmptySquares(int position){
        for(int i=0; i<position; i++){
            if(!SQUARES[i].isBlocked()){
                return true;
            }
        }
        return false;
    }

}