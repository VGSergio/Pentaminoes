/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle2dpentominos;

import java.awt.*;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class Casilla {
    private final int LADO = 10;
    private final Color COLOR;
    
    public Casilla(Color col){
        this.COLOR = col;
    }
    
    public void Dibuja(Graphics g){
        g.setColor(COLOR);
        g.drawRect(0, 0, LADO, LADO);
    }
}
