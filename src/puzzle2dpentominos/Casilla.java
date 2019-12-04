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
    private final int fila, columna;
    
    public Casilla(Color col, int fila, int columna){
        this.COLOR = col;
        this.fila = fila;
        this.columna = columna;
    }
    
    public void Dibuja(Graphics g){
//        g.setColor(Color.BLACK);
//        g.drawRect(columna, fila, LADO, LADO);
        g.setColor(COLOR);
        g.fillRect(columna, fila, LADO, LADO);
    }
    
    public int getLado(){
        return LADO;
    }
}
