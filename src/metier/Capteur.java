/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.Random;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author bagandboeu
 */
public class Capteur {
    private DoubleProperty temperature = new SimpleDoubleProperty();
    private GenerationTemperature gt;
    public DoubleProperty temperatureProperty(){
        return temperature;
    }
    
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
    
    public void Actualiser(){
        this.setTemperature();
    }
}