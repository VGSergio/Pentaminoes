package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_L extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,0, 1,0, 2,0, 3,0, 3,1,                //L 0º
        0,0, 0,1, 0,2, 0,3, 1,0,                //L 90º
        0,0, 0,1, 1,1, 2,1, 3,1,                //L 180º
        0,3, 1,0, 1,1, 1,2, 1,3,                //L 270º
        0,1, 1,1, 2,1, 3,0, 3,1,                //L inverse 0º
        0,0, 1,0, 1,1, 1,2, 1,3,                //L inverse 90º
        0,0, 0,1, 1,0, 2,0, 3,0,                //L inverse 180º
        0,0, 0,1, 0,2, 0,3, 1,3,                //L inverse 270º
    };
    
    private static final Color PIECE_COLOR = new Color(0,200,200);
    
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
