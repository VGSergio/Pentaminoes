/*
 * 
 * 
 * 
 */
package puzzle2dpentominos;

import java.awt.*;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class Casilla {
    
    private static final int LADO = 40;
    private final Color COLOR;
    private final int x, y;
    
    public Casilla(Color col, int x, int y){
        this.COLOR = col;
        this.x = x;
        this.y = y;
    }
    
    public void Dibuja(Graphics g){
//        g.setColor(Color.BLACK);
//        g.drawRect(x, y, LADO, LADO);
        g.setColor(COLOR);
        g.fillRect(x, y, LADO, LADO);
    }
    
    public int getLado(){
        return LADO;
    }
}
