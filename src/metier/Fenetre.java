/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.util.Random;


public class Fenetre implements GenerationTemperature{
    private final double min;
    private final double max;
    private final double inter;
    private static final Random rand = new Random();
    private static final int PRECISION = 100;

    public Fenetre(double initial, int fenetre){
        this.min= initial-(double)fenetre;
        this.max= initial+(double)fenetre;
        inter=max-min+1;
    }

    @Override
    public double randomPick(){
        return Math.round((min+inter*rand.nextDouble())*PRECISION)/PRECISION;
    }
}