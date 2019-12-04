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
    
    /**
     * Sqaure constructor
     * @param col
     * @param row
     * @param column 
     */
    public Square(Color col, int row, int column){
        this.COLOR = col;
        this.Row = row;
        this.Column = column;
    }
    
    /**
     * Draws the square with its color and size(Determined by side)
     * @param g 
     */
    public void Draw(Graphics g){
        g.setColor(Color.WHITE);
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
     * Returns square's color
     * @return Color
     */
    public Color getColor(){
        return COLOR;
    }
    
    /**
     * Sets square color
     * @param color 
     */
    public void setColor(Color color){
        COLOR = color;
    }
}
