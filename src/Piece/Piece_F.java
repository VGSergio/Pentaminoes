package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_F extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,1, 0,2, 1,0, 1,1, 2,1,                //F 0º
        0,1, 1,0, 1,1, 1,2, 2,2,                //F 90º
        0,1, 1,1, 1,2, 2,0, 2,1,                //F 180º
        0,0, 1,0, 1,1, 1,2, 2,1,                //F 270º
        0,0, 0,1, 1,1, 1,2, 2,1,                //F inverse 0º
        0,2, 1,0, 1,1, 1,2, 2,1,                //F inverse 90º
        0,1, 1,0, 1,1, 2,1, 2,2,                //F inverse 180º
        0,1, 1,0, 1,1, 1,2, 2,0,                //F inverse 270º
    };
    
    private static final Color PIECE_COLOR = new Color(200,0,0);
    
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
