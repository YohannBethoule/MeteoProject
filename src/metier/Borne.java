/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Borne implements GenerationTemperature{
    private final double min;
    private final double max;
    private final double inter;
    private static final Random rand = new Random();
    private static final int PRECISION = 100;
    
    StringProperty algoP = new SimpleStringProperty();
    
    public Borne(){
        this.min=-10;
        this.max=40;
        inter=max-min+1;
        setAlgo();
    }
    
    public Borne(double min, double max){
        this.min=min;
        this.max=max;
        inter=max-min+1;
        setAlgo();
    }

    @Override
    public double randomPick(){
        return Math.round((min+inter*rand.nextDouble())*PRECISION)/PRECISION;
    }
    
    @Override
    public String toString(){
        return "algo Borne min="+this.min+" max="+this.max;
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
    public final void setAlgo(){
        algoP.set(this.toString());
    }
}