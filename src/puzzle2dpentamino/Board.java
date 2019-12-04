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
    
    /**
     * Board constructor
     * @param rows
     * @param columns 
     */
    public Board(int rows, int columns){
        ROWS = rows;
        COLUMNS = columns;
        SQUARES = new Square[rows*columns];
        SIDE = new Square(Color.BLACK,0,0).getSide();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int position = (i*columns)+j;
                float red  = (float) Math.random(); float green = (float) Math.random(); float blue  = (float) Math.random();
                SQUARES[position] = new Square(new Color(red,green,blue), i*SIDE, j*SIDE);
            }
        }
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
    
    @Override
    public void paint(Graphics g) {
        for (Square SQUARE1 : SQUARES) {
            SQUARE1.Draw(g);
        }
    }
    
    @Override
    public int getWidth(){
        return COLUMNS*SIDE;
    }
    
    @Override
    public int getHeight(){
        return ROWS*SIDE;
    }
    
    /**
     * Blocks the square placed in the given coordinates
     * @param x
     * @param y 
     */
    public void blockSquare(int x, int y){
        int row = y/SIDE -1;
        int column = x/SIDE;
        int position = row*COLUMNS+column;
        if(SQUARES[position].getColor() != Color.BLACK){
            SQUARES[position].setColor(Color.BLACK);
        }
    }

    
}
