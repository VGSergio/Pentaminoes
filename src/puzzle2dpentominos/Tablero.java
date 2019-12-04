/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle2dpentominos;

import java.awt.*;
import javax.swing.*;

/**
 * @authors Sergio Vega     (43480752B)
 *          Andreas Korn    (X4890193W)
 */
public class Tablero extends JPanel{
    
    private int filas ,columnas;
    private final Casilla[] CASILLAS;
    private final int[] SIZE = new int[2];
    
    public Tablero(int filas, int columnas){
        CASILLAS = new Casilla[filas*columnas];
        int Lado = new Casilla(Color.BLACK,0,0).getLado();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int pos = (i*columnas)+j;
                float rojo  = (float) Math.random(); float verde = (float) Math.random(); float azul  = (float) Math.random();
                CASILLAS[pos] = new Casilla(new Color(rojo,verde,azul), i*Lado, j*Lado);
            }
        }
        SIZE[0] = filas*Lado;
        SIZE[1] = columnas*Lado;
    }
        
    public int getFilas(){
        return filas;
    }
        
    public int getColumnas(){
        return columnas;
    }
    
    @Override
    public void paint(Graphics g) {
        for (Casilla CASILLAS1 : CASILLAS) {
            CASILLAS1.Dibuja(g);
        }
    }
    
    @Override
    public int getWidth(){
        return SIZE[1];
    }
    
    @Override
    public int getHeight(){
        return SIZE[0];
    }
}
