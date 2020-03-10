package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_Y extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,1, 1,0, 1,1, 2,1, 3,1,                //Y 0º
        0,2, 1,0, 1,1, 1,2, 1,3,                //Y 90º
        0,0, 1,0, 2,0, 2,1, 3,0,                //Y 180º
        0,0, 0,1, 0,2, 0,3, 1,1,                //Y 270º
        0,0, 1,0, 1,1, 2,0, 3,0,                //Y inverse 0º
        0,0, 0,1, 0,2, 0,3, 1,2,                //Y inverse 90º
        0,1, 1,1, 2,0, 2,1, 3,1,                //Y inverse 180º
        0,1, 1,0, 1,1, 1,2, 1,3,                //Y inverse 270º
    };
    
    private static final Color PIECE_COLOR = new Color(255,255,150);
    
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
