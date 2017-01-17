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
public class MegaCapteur extends ICapteur{
    
   
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
        this.IntGeneration=0;
    }

    
    public void ajouterC(ICapteur c){
        mc.add(c);
        this.setTemperature();
    }
    
    public void enleverC(ICapteur c){
        if(!mc.remove(c)) System.err.println("pb remove");
        this.setTemperature();
    }
    
    @Override
    public void arreter(){
        System.out.println("Metier.MegaCapteur.arreter()");
        for(ICapteur c: this.mc){
            c.arreter();
        }
        this.t.arreter();
    }

    
    @Override
     public String toString(){
         return "MegaCapteur generation toute les "+this.IntGeneration+" secondes";
     }
     
     
    @Override
     public void activer(){
         for(ICapteur c : this.mc){
             c.activer();
         }
         this.t=new ThreadCapteur(this);
         this.t.go();
     }
     
     @Override
     public void traitement(){
         this.setTemperature();
     }
     
}
