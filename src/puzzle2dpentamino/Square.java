/* Square class. A square is distinguished by its color, side, 
 * position in the board, determined by the row and column the square
 * is placed, and whether it's blocked or not.
 */
package puzzle2dpentamino;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Sergio Vega
 *         Andreas Korn
 */
public class Square {
    
    private final int SIDE = 60;        //Change this value to change squares size
    private Color COLOR;
    private final int Row, Column;
    private boolean Blocked;
    
    /**
     * Square constructor
     * @param row
     * @param column 
     */
    public Square(int row, int column){
        COLOR = Color.WHITE;
        Row = row;
        Column = column;
        Blocked = false;
    }
    
    /**
     * Draws the square with its color and size(Determined by side)
     * @param g 
     */
    public void paint(Graphics g){
        g.setColor(Color.LIGHT_GRAY);                   //Border color
        g.drawRect(Column, Row, SIDE, SIDE);            //Border
        g.setColor(COLOR);                              //Square color
        g.fillRect(Column+1, Row+1, SIDE-1, SIDE-1);    //+-1 so the border can be seen
    }
    
    /**
     * Returns squares side value
     * @return int
     */
    public int getSide(){
        return SIDE;
    }
    
    /**
     * Squares color setter
     * @param color 
     */
    public void setColor(Color color){
        COLOR = color;
    }
    
    /**
     * Squares color getter
     * @return 
     */
    public Color getColor(){
        return COLOR;
    }
    
    /**
     * Squares blocked status getter
     * @return boolean
     */
    public boolean isBlocked(){
        return Blocked;
    }
    
    /**
     * Blocked status setter
     * @param status 
     */
    public void setBlocked(boolean status){
        Blocked = status;
    }
}
