/** A piece has an identificator, a color, and multiple representations depending
 * of the piece
 */
package puzzle2dpentamino;

import java.awt.Color;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class Piece {
    
    /**
     * Matrix with the necessary data about the different pieces and their
     * perspectives.
     * First value is the piece id. The rest of values are row,column by pairs.
     */
    private static final int[][] PIECE_DATA = {  // All the pieces and their different perspectives
        { 0, 0,1, 0,2, 1,0, 1,1, 2,1},          //F 0º
        { 0, 0,1, 1,0, 1,1, 1,2, 2,2},          //F 90º
        { 0, 0,1, 1,1, 1,2, 2,0, 2,1},          //F 180º
        { 0, 0,0, 1,0, 1,1, 1,2, 2,1},          //F 270º
        { 0, 0,0, 0,1, 1,1, 1,2, 2,1},          //F inverse 0º
        { 0, 0,2, 1,0, 1,1, 1,2, 2,1},          //F inverse 90º
        { 0, 0,1, 1,0, 1,1, 2,1, 2,2},          //F inverse 180º
        { 0, 0,1, 1,0, 1,1, 1,2, 2,0},          //F inverse 270º
        
        { 1, 0,0, 1,0, 2,0, 3,0, 4,0},          //I 0º == I 180º == I inverse 0º == I inverse 180º
        { 1, 0,0, 0,1, 0,2, 0,3, 0,4},          //I 90º == I 270º == I inverse 0º == I inverse 270º
        
        { 2, 0,0, 1,0, 2,0, 3,0, 3,1},          //L 0º
        { 2, 0,0, 0,1, 0,2, 0,3, 1,0},          //L 90º
        { 2, 0,0, 0,1, 1,1, 2,1, 3,1},          //L 180º
        { 2, 0,3, 1,0, 1,1, 1,2, 1,3},          //L 270º
        { 2, 0,1, 1,1, 2,1, 3,0, 3,1},          //L inverse 0º
        { 2, 0,0, 1,0, 1,1, 1,2, 1,3},          //L inverse 90º
        { 2, 0,0, 0,1, 1,0, 2,0, 3,0},          //L inverse 180º
        { 2, 0,0, 0,1, 0,2, 0,3, 1,3},          //L inverse 270º
        
        { 3, 0,1, 1,0, 1,1, 2,0, 3,0},          //N 0º
        { 3, 0,0, 0,1, 0,2, 1,2, 1,3},          //N 90º
        { 3, 0,1, 1,1, 2,0, 2,1, 3,0},          //N 180º
        { 3, 0,0, 0,1, 1,1, 1,2, 1,3},          //N 270º
        { 3, 0,0, 1,0, 1,1, 2,1, 3,1},          //N inverse 0º
        { 3, 0,2, 0,3, 1,0, 1,1, 1,2},          //N inverse 90º
        { 3, 0,0, 1,0, 2,0, 2,1, 3,1},          //N inverse 180º
        { 3, 0,1, 0,2, 0,3, 1,0, 1,1},          //N inverse 270º
        
        { 4, 0,1, 1,0, 1,1, 2,0, 2,1},          //P 0º
        { 4, 0,0, 0,1, 1,0, 1,1, 1,2},          //P 90º
        { 4, 0,0, 0,1, 1,0, 1,1, 2,0},          //P 180º
        { 4, 0,0, 0,1, 0,2, 1,1, 1,2},          //P 270º
        { 4, 0,0, 1,0, 1,1, 2,0, 2,1},          //P inverse 0º
        { 4, 0,0, 0,1, 0,2, 1,0, 1,1},          //P inverse 90º
        { 4, 0,0, 0,1, 1,0, 1,1, 2,1},          //P inverse 180º
        { 4, 0,1, 0,2, 1,0, 1,1, 1,2},          //P imverse 270º
        
        { 5, 0,0, 0,1, 0,2, 1,1, 2,1},          //T 0º == T inverse 0º
        { 5, 0,2, 1,0, 1,1, 1,2, 2,2},          //T 90º == T inverse 90º
        { 5, 0,1, 1,1, 2,0, 2,1, 2,2},          //T 180º == T inverse 180º
        { 5, 0,0, 1,0, 1,1, 1,2, 2,0},          //T 270º == T inverse 270º 
        
        { 6, 0,0, 0,2, 1,0, 1,1, 1,2},          //U 0º == U inverse 0º
        { 6, 0,0, 0,1, 1,0, 2,0, 2,1},          //U 90º == U inverse 90º
        { 6, 0,0, 0,1, 0,2, 1,0, 1,2},          //U 180º == U inverse 180º
        { 6, 0,0, 0,1, 1,1, 2,0, 2,1},          //U 270º == U inverse 270º
        
        { 7, 0,0, 1,0, 2,0, 2,1, 2,2},          //V 0º == V inverse 90º
        { 7, 0,0, 0,1, 0,2, 1,0, 2,0},          //V 90º == V inverse 180º
        { 7, 0,0, 0,1, 0,2, 1,2, 2,2},          //V 180º == V inverse 270º
        { 7, 0,2, 1,2, 2,0, 2,1, 2,2},          //V 270º == V inverse 0º
        
        { 8, 0,0, 1,0, 1,1, 2,1, 2,2},          //W 0º == W inverse 90º
        { 8, 0,1, 0,2, 1,0, 1,1, 2,0},          //W 90º == W inverse 180º
        { 8, 0,0, 0,1, 1,1, 1,2, 2,2},          //W 180º == W inverse 270º
        { 8, 0,2, 1,1, 1,2, 2,0, 2,1},          //W 270º == W inverse 0º
        
        { 9, 0,1, 1,0, 1,1, 1,2, 2,1},          //X
        
        {10, 0,1, 1,0, 1,1, 2,1, 2,1},          //Y 0º
        {10, 0,2, 1,0, 1,1, 1,2, 1,3},          //Y 90º
        {10, 0,0, 1,0, 2,0, 2,1, 3,0},          //Y 180º
        {10, 0,0, 0,1, 0,2, 0,3, 1,1},          //Y 270º
        {10, 0,0, 1,0, 1,1, 2,0, 3,0},          //Y inverse 0º
        {10, 0,0, 0,1, 0,2, 0,3, 1,2},          //Y inverse 90º
        {10, 0,1, 1,1, 2,0, 2,1, 3,1},          //Y inverse 180º
        {10, 0,1, 1,0, 1,1, 1,2, 1,3},          //Y inverse 270º
        
        {11, 0,0, 0,1, 1,1, 2,1, 2,2},          //Z 0º == Z 180º
        {11, 0,2, 1,0, 1,1, 1,2, 2,0},          //Z 90º == z 270º
        {11, 0,1, 0,2, 1,1, 2,0, 2,1},          //Z inverse 0º == Z inverse 180º
        {11, 0,0, 1,0, 1,1, 1,2, 2,2}           //Z inverse 90º == Z inverse 270º
    };
    
    /**
     * Piece's colors, number 0 through 11.
     */
    private static final Color PIECE_COLOR[] = {  
         new Color(200,0,0),
         new Color(150,150,255),
         new Color(0,200,200),
         new Color(255,150,255),
         new Color(0,200,0),
         new Color(150,255,255),
         new Color(200,200,0),
         new Color(0,0,200),
         new Color(255,150,150),
         new Color(200,0,200),
         new Color(255,255,150),
         new Color(150,255,150)
   };
    
    public static final int PIECESQUARES = 5;
    
    /**
     * returns the color of a given piece id
     * @param piece
     * @return Color
     */
    public Color getColor(int piece){
        return PIECE_COLOR[piece];
    }
    
    public int[][] getPerspectives(int piece){
        int numcoords = 2*PIECESQUARES;
        int i=0;
        int cont = 0;
        
        while(i<PIECE_DATA.length && PIECE_DATA[i][0]<=piece){
            if(PIECE_DATA[i][0]==piece){
                cont++;
            }
            i++;
        }
        
        int g = i-cont;
        int[][] p = new int[cont][numcoords]; //2 Coordinates, 5 squares, perspective array
        for(i=0; i<cont; i++){
            for(int j=0; j<numcoords; j++){
                p[i][j] = PIECE_DATA[g+i][j+1]; 
            }
        }
        
        return  p;
    }
    
}
