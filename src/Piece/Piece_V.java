package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_V extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,0, 1,0, 2,0, 2,1, 2,2,                //V 0º == V inverse 90º
        0,0, 0,1, 0,2, 1,0, 2,0,                //V 90º == V inverse 180º
        0,0, 0,1, 0,2, 1,2, 2,2,                //V 180º == V inverse 270º
        0,2, 1,2, 2,0, 2,1, 2,2,                //V 270º == V inverse 0º
    };
    
    private static final Color PIECE_COLOR = new Color(0,0,200);
    
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
