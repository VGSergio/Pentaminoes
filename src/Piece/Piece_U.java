package Piece;

import java.awt.Color;

/**
 *
 * @author Sergio
 */
public class Piece_U extends Piece{

    private static final int[] PIECE_DATA = {   // All the pieces and their different perspectives
        0,0, 0,2, 1,0, 1,1, 1,2,                //U 0º == U inverse 0º
        0,0, 0,1, 1,0, 2,0, 2,1,                //U 90º == U inverse 90º
        0,0, 0,1, 0,2, 1,0, 1,2,                //U 180º == U inverse 180º
        0,0, 0,1, 1,1, 2,0, 2,1,                //U 270º == U inverse 270º
    };
    
    private static final Color PIECE_COLOR = new Color(200,200,0);
    
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
