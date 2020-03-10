package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_I extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,0, 1,0, 2,0, 3,0, 4,0,                //I 0º == I 180º == I inverse 0º == I inverse 180º
        0,0, 0,1, 0,2, 0,3, 0,4,                //I 90º == I 270º == I inverse 0º == I inverse 270º
    };
    
    private static final Color PIECE_COLOR = new Color(150,150,255);
    
    @Override
    public Color getColor() {
        return PIECE_COLOR;
    }

    @Override
    public int[] getPerspectives() {
        return PIECE_DATA;
    }
    
    /**
     * Number of squares that form a piece
     */
    private static final int PIECESQUARES = 5;
    
    @Override
    public int getPieceSquares() {
      return PIECESQUARES;  
    }
    
}
