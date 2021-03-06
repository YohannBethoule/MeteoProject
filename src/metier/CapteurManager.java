/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bagandboeu
 */
public class CapteurManager {
    
    private List<ICapteur> manager;
    private ObservableList<String> ObslistC= FXCollections.observableArrayList();
    private final ListProperty<String> listPC= new SimpleListProperty<>(ObslistC);
    
    public ObservableList<String> getLesCapteurString() {return listPC;}
        public void setLesCapteurString(ObservableList<String> value) {listPC.set(value);}
        public ListProperty<String> lesCapteurStringProperty() {return listPC;}
    
    int nbCapt=0;
    
    public CapteurManager(){
        manager = new ArrayList<>();
    }
    
    public void ajoutCapteur(ICapteur ic){
        manager.add(ic);
        this.setLesCapteurString(List2String());
        nbCapt=nbCapt=1;
    }
    
    
    public ObservableList<String> List2String(){
        ObservableList<String> l = FXCollections.observableArrayList();
        for(ICapteur c : this.manager){
            l.add(c.toString());
        }
        return l;
    } 
    
    public ICapteur FindCapteur(String s){
        for(ICapteur c : manager){
            String sc= "["+c.toString()+"]" ;
            if(sc.equals(s) || s.equals(c.toString())){
                return c;
            }
        }
        return null;
    }
    
}
