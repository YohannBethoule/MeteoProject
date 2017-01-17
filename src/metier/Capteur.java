/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Controller.AbstractController;
import java.lang.Thread.State;
import java.util.Random;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author bagandboeu
 */
public class Capteur extends ICapteur{
    
    private DoubleProperty temperature = new SimpleDoubleProperty();
    private GenerationTemperature gt;
    public DoubleProperty temperatureProperty(){
        return temperature;
    }
    
    @Override
    public double getTemperature(){
        return temperature.get();
    }
    
    private void setTemperature(){
        this.temperature.set(gt.randomPick());
    }
    
    public Capteur(GenerationTemperature gt){
        this.gt=gt;
        setTemperature();
        observer=null;
    }
    
    public Capteur(GenerationTemperature gt, long interGen){
        this.IntGeneration=interGen*1000;
        this.gt=gt;
        setTemperature();
        observer=null;
    }

    
    public void Actualiser(){
        this.setTemperature();
    }
    
    
    @Override
    public void arreter(){
        this.t.arreter();
    }

     
     @Override
     public String toString(){
         return"Capteur Simple "+this.gt.toString()+" intervale de generation = "+this.IntGeneration;
     }
    
    @Override
    public void activer(){
        this.t=new ThreadCapteur(this);
        this.t.go();
    }
    
    
    @Override
    public void traitement(){
        this.setTemperature();
    }
     
}

