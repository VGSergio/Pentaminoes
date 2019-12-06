/* Square class. A square is distinguished by its color, side 
 * and position in the board, determined by the row and column the square
 * is placed
 */
package puzzle2dpentamino;

import java.awt.*;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class Square {
    
    private static final int SIDE = 60;
    private Color COLOR;
    private final int Row, Column;
    private boolean Blocked;
    
    /**
     * Sqaure constructor
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
    public void Draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(Column, Row, SIDE, SIDE);
        g.setColor(COLOR);
        g.fillRect(Column+1, Row+1, SIDE-1, SIDE-1);
    }
    
    /**
     * Returns the square's side value
     * @return int
     */
    public int getSide(){
        return SIDE;
    }
    
    /**
     * Sets square color
     * @param color 
     */
    public void setColor(Color color){
        COLOR = color;
    }
    
    /**
     * Square's blocked status getter
     * @return boolean
     */
    public boolean getBlocked(){
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
