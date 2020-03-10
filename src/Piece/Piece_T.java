package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_T extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,0, 0,1, 0,2, 1,1, 2,1,                //T 0º == T inverse 0º
        0,2, 1,0, 1,1, 1,2, 2,2,                //T 90º == T inverse 90º
        0,1, 1,1, 2,0, 2,1, 2,2,                //T 180º == T inverse 180º
        0,0, 1,0, 1,1, 1,2, 2,0,                //T 270º == T inverse 270º
    };
    
    private static final Color PIECE_COLOR = new Color(150,255,255);
    
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
