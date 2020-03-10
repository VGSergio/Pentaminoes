package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_Z extends Piece{

    /**
     * Number of squares that form a piece
     */
    private static final int PIECESQUARES = 5;
    
    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,0, 0,1, 1,1, 2,1, 2,2,                //Z 0º == Z 180º
        0,2, 1,0, 1,1, 1,2, 2,0,                //Z 90º == z 270º
        0,1, 0,2, 1,1, 2,0, 2,1,                //Z inverse 0º == Z inverse 180º
        0,0, 1,0, 1,1, 1,2, 2,2                 //Z inverse 90º == Z inverse 270º
    };
    
    private static final Color PIECE_COLOR = new Color(150,255,150);
    
    @Override
    public Color getColor() {
        return PIECE_COLOR;
    }

    @Override
    public int[] getPerspectives() {
        return PIECE_DATA;
    }

    @Override
    public int getPieceSquares() {
      return PIECESQUARES;  
    }
    
}
