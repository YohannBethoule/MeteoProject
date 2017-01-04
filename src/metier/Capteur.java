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
public class Capteur extends ICapteur implements Runnable{
    
    private DoubleProperty temperature = new SimpleDoubleProperty();
    private GenerationTemperature gt;
    private boolean actif =true;
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
    public void run(){
        while(actif){
            try{
            Thread.sleep(IntGeneration);
            }catch(InterruptedException e){
                System.err.println("pb sleep");
            }
            this.setTemperature();
            System.out.println(" \n coucou je change de temp CAP");
            System.out.println(this.getTemperature());
            if(observer != null){
                this.observer.update();
            }
        }
    }
        
     public void arreter(){   
         actif=false;
     }
     
     @Override 
     public void regenere(){
         this.setTemperature();
     }
}
