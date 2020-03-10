/* A piece has an identificator, a color, and multiple representations depending
 * of the piece.
 */
package Piece;

import java.awt.Color;

/**
 * @author Sergio Vega
 *         Andreas Korn
 */
public abstract class Piece {
    
    /**
     * Returns the amount of squares that form a piece
     * @return
     */
    public abstract int getPieceSquares();
    
    /**
     * Returns the color of a given piece id
     * @return
     */
    public abstract Color getColor();
    
    /**
     * Returns an array with all the perspectives of a given piece
     * @return Perspectives
     */
    public abstract int[] getPerspectives();
    
}
