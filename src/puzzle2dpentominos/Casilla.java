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
    private final int LADO = 50;
    private final Color COLOR;
    
    public Casilla(Color col){
        this.COLOR = col;
    }
    
    public void Dibuja(Graphics g){
        g.setColor(COLOR);
        g.drawRect(0, 0, LADO, LADO);
    }
}
