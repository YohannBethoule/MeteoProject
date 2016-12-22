/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Controller.AbstractController;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author bagandboeu
 */
public class MegaCapteur extends ICapteur {
    private List<ICapteur> mc;
    private static final int PRECISION = 100;
    private DoubleProperty temperature = new SimpleDoubleProperty();
    
    public DoubleProperty temperatureProperty(){
        return temperature;
    }
    
    @Override
    public double getTemperature(){
        return temperature.get();
    }
    
    private void setTemperature(){
        double mt=0;
        for(int i=0;i<mc.size();i++){
            mt=mt+mc.get(i).getTemperature();
        }
        this.temperature.set(Math.round((mt/mc.size()*PRECISION)/PRECISION));
    }
    
    public MegaCapteur(List<ICapteur> mc){
        this.mc=mc;
    }
    
    public MegaCapteur(){
        mc=new ArrayList<>();
    }
    
    public MegaCapteur(AbstractController obs){
        this.observer=obs;
    }
    
    public MegaCapteur(List<ICapteur> mc, AbstractController obs){
        this.observer=obs;
    }
    
    public void ajouterC(ICapteur c){
        mc.add(c);
        this.setTemperature();
    }
    
    public void enleverC(ICapteur c){
        if(!mc.remove(c)) System.err.println("pb remove");
        this.setTemperature();
    }
    
    
}