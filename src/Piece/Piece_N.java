package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_N extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,1, 1,0, 1,1, 2,0, 3,0,                //N 0º
        0,0, 0,1, 0,2, 1,2, 1,3,                //N 90º
        0,1, 1,1, 2,0, 2,1, 3,0,                //N 180º
        0,0, 0,1, 1,1, 1,2, 1,3,                //N 270º
        0,0, 1,0, 1,1, 2,1, 3,1,                //N inverse 0º
        0,2, 0,3, 1,0, 1,1, 1,2,                //N inverse 90º
        0,0, 1,0, 2,0, 2,1, 3,1,                //N inverse 180º
        0,1, 0,2, 0,3, 1,0, 1,1,                //N inverse 270º
    };
    
    private static final Color PIECE_COLOR = new Color(255,150,255);
    
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
