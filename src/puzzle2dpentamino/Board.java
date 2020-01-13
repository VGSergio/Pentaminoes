/* Board class. A board has a certain number of rows and columns 
 * filled with squares of a given size. 
 */
package puzzle2dpentamino;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int best = 0;
    private int blockCheckCt;
    private int[] blockCheck;                                            
    private boolean StatusMessage;
    
    /**
     * Board constructor
     * @param rows
     * @param columns 
     */
    public Board(int rows, int columns){
        PIECESQUARES = new Piece().getPieceSquares();       //Sets how many squares form the pieces
        SIDE = new Square(0,0).getSide();                   //Sets Squares SIDE value
        
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
     * Rows amount getter
     * @return int
     */
    public int getRows(){
        return ROWS;
    }
        
    /**
     * Columns amount getter
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
     * Returns true if the board has blocked squares.
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
     * Returns Boards width
     * @return 
     */
    @Override
    public int getWidth(){
        return COLUMNS*SIDE;
    }
    
    /**
     * Returns board height
     * @return 
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
     * Solving status setter
     * @param Solving 
     */
    public void setSolving(boolean Solving) {
        this.Solving = Solving;
    }
    
    /**
     * Blocks/unblocks the square placed in the given coordinates
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
     * Boards paint method
     * @param g 
     */
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
     * StatusMessage setter
     * @param status 
     */
    public void setStatusMessage(boolean status){
        StatusMessage = status;
    }
    
    /**
     * BackTracking recursive method to find the pentominoes solutions of an
     * empty board
     * @param game
     * @param pos
     * @param pieces
     * @param usedpieces
     * @param maxpieces
     * @return 
     */
    public Board[] Solve(GUI game, int pos, boolean[] pieces, int usedpieces, int maxpieces){
        if(isSolving()){                                //If the board is still solving
            if(usedpieces==maxpieces){                  //If all the pieces has been used
                SOLUTIONS.add(cloneBoard());            //Adds the solution to the solutions array
                Solutions++;                            //Updates solutions counter
                if(Speed==0){                           //If OnlySolutions speed is selected, repaints the board
                    try {
                        Thread.sleep(10);               //Mandatory sleep to have an accurate representation of the solution, some visual bugs may still happen sometimes
                        game.repaint();                 //Repaints the board
                        Thread.sleep(10);               //Mandatory sleep to have an accurate representation of the solution, some visual bugs may still happen sometimes
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {            //Not a solution
                for(int i=pos; i<getSquaresAmount(); i++){                      //Tries each position
                    if(!hasLeftEmptySquares(i)){                                //No squares have been left empty
                        for(int piece=0; piece<pieces.length; piece++){         //Tries each piece
                            if(!pieces[piece]){                                 //The piece has not been used
                                int[] perspectives = new Piece().getPerspectives(piece);            //Gets the piece pespectives
                                for(int pers=0; pers<perspectives.length/10; pers++){               //Tries each piece perspective
                                    int[] perspective = getPerspective(perspectives, pers);         //Gets the desired perspective
                                    if(pieceFits(i, perspective)){                                  //The piece fits
                                        int[] positions = getSquaresPositions(i, perspective);
                                        Color color = new Piece().getColor(piece);
                                        AddPiece(positions, color);                                 //Adds the piece onto the board
                                        Iterations++;
                                        if(StatusMessage){                                          //Updates the status message if selected
                                           String[] message = getMessage();
                                            game.setMessage(message[0], message[1], message[2]); 
                                        }
                                        
                                        if(Speed>0){                            //If the selected speed != OnlySolutions -> repaints the board
                                            try {
                                                Thread.sleep(Speed);
                                                game.repaint();                 //Repaints the board
                                            } catch (InterruptedException ex) {
                                                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }

                                        if(!ObviousBlockExists()){              //If no unfillable areas has been created
                                            pieces[piece] = true;               //Updates used pieces array
                                            usedpieces++;                       //Updates used pieces counter
                                            Solve(game, i+1, pieces, usedpieces, maxpieces);     //Recursive call

                                            usedpieces--;                       //Updates used pieces counter
                                            pieces[piece] = false;              //Updates used pieces array
                                        }

                                        removePiece(positions);                 //Removes the piece from the board
                                        if(Speed>0){                            //If the selected speed != OnlySolutions -> repaints the board
                                            try {
                                                Thread.sleep(Speed);
                                                game.repaint();                 //Repaints the board
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
        if(pos==0){             //Once BackTracking is done
            Board[] solutions = new Board[SOLUTIONS.size()];            //Solutions array
            SOLUTIONS.toArray(solutions);
            System.out.println(Iterations+" iterations done.");         //Debugging data / useful info
            System.out.println(Solutions+" solutions found(s).");       //Debugging data / useful info
            return solutions;
        }
        return null;
    }
    
    /**
     * BackTracking recursive method to find the pentominoes solutions of a
     * board with blocked squares
     * @param game
     * @param pos
     * @param pieces
     * @return 
     */
    public Board[] Solve(GUI game, int pos, boolean[] pieces){
        if(isSolving()){                                    //If the board is still solving
            if(getSquaresOccupied()>best){                  //If a better solution has been found
                best=getSquaresOccupied();                  //Sets best solution found
                SOLUTIONS.clear();                          //Erases previous solutions found
                SOLUTIONS.add(cloneBoard());                //Adds the new solution to the solutions array
                Solutions = 1;                              //Resets solution counter
            } else if(getSquaresOccupied()==best){          //If a equally good solution found
                SOLUTIONS.add(cloneBoard());                //Adds it to the solution array
                Solutions++;                                //Updates solutions counter
            }
            for(int i=pos; i<getSquaresAmount(); i++){      //Tries each position
                if(!hasLeftEmptySquares(pos)){              //If none squares have been left empty
                    for(int piece=0; piece<pieces.length; piece++){             //Tries each piece
                            if(!pieces[piece]){                                 //If it hasn't been used already
                            int[] perspectives = new Piece().getPerspectives(piece);        //Gets the piece perspectives
                            for(int pers=0; pers<perspectives.length/10; pers++){           //Tries each perspective
                                int[] perspective = getPerspective(perspectives, pers);     //Gets desired perspective
                                if(pieceFits(i, perspective)){                              //If the piece fits
                                    int[] positions = getSquaresPositions(i, perspective);  
                                    Color color = new Piece().getColor(piece);
                                    AddPiece(positions, color);                 //Adds the piece to the board
                                    Iterations++;
                                    if(StatusMessage){                          //Uptades message if selected
                                       String[] message = getMessage();
                                        game.setMessage(message[0], message[1], message[2]); 
                                    }

                                    try {
                                        Thread.sleep(Speed);
                                        game.repaint();                         //Repaints the board
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }

//                                    if(!ObviousBlockExists()){                //Invalid pruning method, some solutions wouldn't be found
                                        pieces[piece] = true;                   //Updates the used pieces array
                                        Solve(game, i+1, pieces);               //Recursive call

                                        pieces[piece] = false;                  //Updates the used pieces array
//                                    }

                                    removePiece(positions);                     //Deletes the piece from the board
                                    try {
                                        Thread.sleep(Speed);
                                        game.repaint();                         //Repaints the board
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
        if(pos==0){             //Once backtracking is done
            Board[] solutions = new Board[SOLUTIONS.size()];            //Solutions array
            SOLUTIONS.toArray(solutions);
            System.out.println(Iterations+" iterations done.");                     //Debugging data / useful info
            System.out.println(Solutions+" solutions found(s).");                   //Debugging data / useful info
            System.out.println("Best solution found: "+best+" squares occupied");   //Debugging data / useful info
            return solutions;
        }
        return null;
    }
    
    /**
     * Computes if an unfillable area has been created
     * @return 
     */
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
    
    /**
     * Find the size of one empty region on the board 
     * Recursive routine called by obviousBlockExists.
     * @param r
     * @param c
     * @return 
     */
    private int countEmptyBlock(int r, int c) {
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
     * Computes the squares position of a piece from the actual position, 
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
        while(i<PIECESQUARES){   
//            int nrow = perspective[2*i];  int ncolumn = perspective[2*i+1];   
//            int position2 = position + nrow*COLUMNS+ncolumn;
            int position2 = position + perspective[2*i]*COLUMNS+perspective[2*i+1];     //Computes the squares position
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
     * Returns the desired perspective of the specified piece
     * @param perspectives
     * @param persepective
     * @return int[]
     */
    private int[] getPerspective(int[] perspectives,int persepective){
        int[] p = new int[2*PIECESQUARES];     //2 Coordinates, 5 squares, perspective array
        boolean found = false;
        
        for (int i=0; i<perspectives.length && !found; i+=(2*PIECESQUARES)){    //1 new perspective every 10 values
            if(i/10==persepective){             //Desired perspective found
                for(int j=0; j<p.length; j++){  
                    p[j] = perspectives[i+j];   //Copies perspective datra
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
     * Returns a String array with the updated status message
     * @return message
     */
    private String[] getMessage(){
        String s1 = "";
        String s2 = Iterations+" iterations realised.";
        String s3 = Solutions +" Solution(s) found.";
        
        switch(Iterations/100000%4){
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
     * Returns whether a square has been left empty or not
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