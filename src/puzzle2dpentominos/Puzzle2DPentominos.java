/*
 * 
 * 
 * 
 */
package puzzle2dpentominos;

import java.awt.*;
import javax.swing.*;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class Puzzle2DPentominos {

    private static Tablero tablero;
    private static final int SIZE = 50;
    
    public Puzzle2DPentominos(Tablero tablero){
        this.tablero = tablero;
    }
    
    public JPanel GeneraTablero(){
        JPanel panel = new JPanel();
        panel.setSize(SIZE*tablero.getY(), SIZE*tablero.getX());
        panel.setLayout(new GridLayout(tablero.getX(), tablero.getY()));
        for (int i=0; i<tablero.getX()*tablero.getY(); i++){
            JButton button = new JButton(String.valueOf(i));
            button.setSize(SIZE, SIZE);
            panel.add(button);
        }
        return panel;
    }
    
}
