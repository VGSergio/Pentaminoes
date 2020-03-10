package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_W extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,0, 1,0, 1,1, 2,1, 2,2,                //W 0º == W inverse 90º
        0,1, 0,2, 1,0, 1,1, 2,0,                //W 90º == W inverse 180º
        0,0, 0,1, 1,1, 1,2, 2,2,                //W 180º == W inverse 270º
        0,2, 1,1, 1,2, 2,0, 2,1,                //W 270º == W inverse 0º
    };
    
    private static final Color PIECE_COLOR = new Color(255,150,150);
    
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
