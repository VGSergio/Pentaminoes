package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_X extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,1, 1,0, 1,1, 1,2, 2,1,                //X
    };
    
    private static final Color PIECE_COLOR = new Color(200,0,200);
    
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
