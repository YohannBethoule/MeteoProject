/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Controller.AbstractController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bagandboeu
 */
public class MegaCapteur extends ICapteur implements Runnable{
    
    Thread t = new Thread();
    private boolean actif=true;
    private List<ICapteur> mc;
    private ObservableList<String> ObslistMC= FXCollections.observableArrayList();
    private final ListProperty<String> listMC= new SimpleListProperty<>(ObslistMC);
    public ObservableList<String> getLesMCapteurString() {return listMC.get();}
        public void setLesMCapteurString(ObservableList<String> value) {listMC.set(value);}
        public ListProperty<String> lesMCapteurStringProperty() {return listMC;}
        
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
    
    public MegaCapteur(){
        mc=new ArrayList<>();
    }
    
    public void ajouterC(ICapteur c){
        mc.add(c);
        this.setLesMCapteurString(List2String());
        this.setTemperature();
    }
    
    public ObservableList<String> List2String(){
        ObservableList<String> l = FXCollections.observableArrayList();
        for(ICapteur c : this.mc){
            l.add(c.toString());
        }
        return l;
    } 
    
    public ICapteur FindCapteur(String s){
        for(ICapteur c : this.mc){
            if(c.toString().equals(s)){
                return c;
            }
        }
        return null;
    }
    
    public void enleverC(ICapteur c){
        if(!mc.remove(c)) System.err.println("pb remove");
        this.setTemperature();
    }
    
    public void setIntervalleRegeneration(long time){
        this.IntGeneration=time*1000;
    }
    
    @Override
    public void run(){   
        while(actif){
            for(ICapteur c : this.mc){
                c.regenere();
            
            }
            try{
            t.sleep(IntGeneration);
            }catch(InterruptedException e){
                System.err.println("pb sleep");
            }
            this.setTemperature();
            this.observer.update();
        }
    }
    
    @Override
    public void arreter(){
        try{
            this.actif=false; 
        }catch(Exception e){
            System.out.println("Metier.MegaCapteur.arreter()");
        }
    }
    
    @Override
    public void regenere(){
        for (ICapteur c : mc) {
            c.regenere();
        }
        this.setTemperature();
    }
    
    @Override
     public String toString(){
         return "MegaCapteur generation toute les "+this.IntGeneration+" secondes";
     }
}
