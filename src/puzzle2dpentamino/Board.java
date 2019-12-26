/* Board class. A board has a certain number of rows and columns 
 * filled with squares of a given size
 */
package puzzle2dpentamino;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final int PIECESQUARES;
    
    private int Speed;
    private int best = 0;
    private boolean Solving;
    private ArrayList<Board> Solutions = new ArrayList();
    private ArrayList<String> Visited = new ArrayList();
    private int iterations =0;
    private int sol=0;
    
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
    
    /**
     * Solving speed setter
     * @param miliseconds 
     */
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
    
    public Board[] Solve(GUI game, Board board, boolean[] pieces, int usedpieces, int maxpieces){
        if(isSolving()){
            if(usedpieces==maxpieces){
                Solutions.add(board.clone());
                sol++;
            }

            int aux=getSquaresAmount();

            for(int i=0; i<getSquaresAmount(); i++){
                if(i<aux){
                    for(int piece=0; piece<pieces.length; piece++){
                        if(!pieces[piece]){
                            int[][] perspectives = new Piece().getPerspectives(piece);
                            for(int pers=0; pers<perspectives.length; pers++){
                                int[] perspective = getPerspective(perspectives, pers);
                                if(pieceFits(i, perspective)){
                                    int[] positions = getSquaresPositions(i, perspective);
                                    Color color = new Piece().getColor(piece);
                                    AddPiece(positions, color);
                                    iterations++;
                                    String[] message = getMessage();
                                    game.setMessage(message[0], message[1], message[2]);

                                    try {
                                        Thread.sleep(Speed);
                                        game.repaint();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if(!ObviousBlockExists(i, positions)){
                                        pieces[piece] = true;
                                        usedpieces++;
                                        aux = i;
                                        Solve(game, board, pieces, usedpieces, maxpieces);

                                        usedpieces--;
                                        pieces[piece] = false;
                                    }
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
        Board[] solutions = new Board[Solutions.size()];
        for(int i=0; i<Solutions.size(); i++){
            solutions[i] = Solutions.get(i);
        }
        return solutions;
    }
    
    /**
     * ????????????
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
     * ??????????????
     * @param positions
     * @return 
     */
    private boolean hasLeftUnfillableAreas(int[] positions){
        int min = 60;
        int lowRightPos[] = getLowestRightPieceSquare(positions);
        int downRow = lowRightPos[0];
        int rightColumn = lowRightPos[1];
        
        for (int i=0 ; i < downRow; i++) {
            for (int j=0 ; j < rightColumn; j++) {
                int position = (i*COLUMNS)+j;
                if(!SQUARES[position].isBlocked()){
                    int area = getAreaSize(i, j);
                    if(min > area){
                        min = area;
                    }
                }
            }
        }
        return min<PIECESQUARES;
    }
    
    /**
     * ??????????????????????
     * @param row
     * @param column
     * @return 
     */
    private int getAreaSize(int row, int column){
        int size=1;
        if(column>0 && !SQUARES[row*COLUMNS+(column-1)].isBlocked()){
            size += getAreaSize(row, column-1);
        }
        if(row>0 && !SQUARES[(row-1)*COLUMNS+column].isBlocked()){
            size += getAreaSize(row-1, column);
        }
        if(row<ROWS-1 && !SQUARES[(row+1)*COLUMNS+column].isBlocked()){
            size += getAreaSize(row+1, column);
        }
        if(column<COLUMNS-1 && !SQUARES[row*COLUMNS+(column+1)].isBlocked()){
            size += getAreaSize(row, column+1);
        }
        return size;
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
     * Returns the first epmty position
     * @return 
     */
    private int getNextEMptySquare(){
        int i=0;
        while(i<SQUARES.length){
            if(!SQUARES[i].isBlocked())
                break;
            i++;
        }
        return i;
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
            int position2 = position + perspective[2*i]*COLUMNS+perspective[2*i+1];
            positions[i] = position2;
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
    
    /**
     * Clones a board
     * @return
     */
    @Override
    protected Board clone(){
        Board clone = new Board(ROWS,COLUMNS);
        for(int i=0; i<clone.getSquaresAmount(); i++){
            clone.SQUARES[i].setBlocked(SQUARES[i].isBlocked());
            clone.SQUARES[i].setColor(SQUARES[i].getColor());
        }
        return clone;
    }
   
    /**
     * String representation of a board, if a board has already been tried
     * dismiss it
     * @param board
     * @return 
     */
    private boolean checkAndUpdateVisitedState(Board board){
        String aux = board.toString();
        boolean visited = false;
        if(Visited.size()>0){
            for(int i=0; i<Visited.size(); i++){
                if(Visited.get(i).equals(aux)){
                    visited=true;
                }
            }
        }
        if(!visited){
            Visited.add(aux);
        }
        return visited;
    }
    
    private String[] getMessage(){
        String s1 = "";
        String s2 = iterations+" iterations realised.";
        String s3 = sol+" Solution(s) found.";
        
        switch(iterations/50000%4){
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
}