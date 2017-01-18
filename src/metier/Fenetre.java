/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Fenetre implements GenerationTemperature{
    private final double min;
    private final double max;
    private final double inter;
    private static final Random rand = new Random();
    private static final int PRECISION = 100;

    StringProperty algoP = new SimpleStringProperty();
    
    public Fenetre(double initial, int fenetre){
        this.min= initial-(double)fenetre;
        this.max= initial+(double)fenetre;
        inter=max-min+1;
    }

    @Override
    public double randomPick(){
        return Math.round((min+inter*rand.nextDouble())*PRECISION)/PRECISION;
    }
    
    @Override
    public String toString(){
        return "algo Fenetre min="+this.min+" max="+this.max;
    }

    @Override
    public StringProperty algoProperty() {
        return algoP;
    }

    @Override
    public String getAlgo() {
        return algoP.get();
    }
    
    @Override
    public void setAlgo(){
        algoP.set(this.toString());
    }
}