/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Controller.AbstractController;
import java.util.Random;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author bagandboeu
 */
public class Capteur extends ICapteur implements Runnable{
    
    public long IntGeneration;
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
    }
    
    public Capteur(GenerationTemperature gt, long interGen){
        this.IntGeneration=interGen*1000;
        this.gt=gt;
        setTemperature();
    }

    
    public void Actualiser(){
        this.setTemperature();
    }
    
    @Override
    public void run(){
        
        for(;;){
            System.out.println("coucou je change de temp");
            System.out.println(this.getTemperature());
            this.observer.update();
            this.setTemperature();
            try{
            Thread.sleep(3000);
            }catch(InterruptedException e){
                System.err.println("pb sleep");
            }
        }
        
    }
}
