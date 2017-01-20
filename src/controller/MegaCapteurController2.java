/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.Capteur;
import Metier.ICapteur;
import static Metier.ICapteur.cm;
import Metier.MegaCapteur;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Léandre
 */
public class MegaCapteurController2 implements Initializable{
    
    @FXML
    private ListView listCapt, listMCapt;
    
    
    @FXML
     VBox UCparent; 
    
    private MegaCapteur mc;
    

    private StringProperty captSP = new SimpleStringProperty();
    public ObservableStringValue getCaptSP(){
        return captSP;
    }
    
    private StringProperty algoSP = new SimpleStringProperty();
    public ObservableStringValue getAlgoSP(){
        return algoSP;
    }
    
    public MegaCapteurController2(MegaCapteur mc){
        this.mc=mc;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listCapt.itemsProperty().bind(cm.lesCapteurStringProperty());
        listCapt.getSelectionModel().select(0);
        
        listMCapt.itemsProperty().bind(mc.lesMCapteurStringProperty());
        listMCapt.getSelectionModel().select(0);
        
    }
    
    
    //Méthode appelé lorsqu'on veut ajouter un élement au mégaCapteur.
    @FXML
    private void ajoutMC(){
        ICapteur c = cm.FindCapteur(listCapt.getSelectionModel().getSelectedItems().toString());
        mc.ajouterC(c);
        listMCapt.itemsProperty().bind(mc.lesMCapteurStringProperty());
    }
    
    
    //Méthode appelé lorqu'on clique sur un des élements de la liste du MégaCpateur.
    @FXML
    private void ModifC(){
        ICapteur c = cm.FindCapteur(listMCapt.getSelectionModel().getSelectedItems().toString());
        UCparent.getChildren().clear();
        if(c instanceof Capteur){
            UCCaptController UC = new UCCaptController((Capteur)c, this);
            UCparent.getChildren().add(UC);
            listCapt.itemsProperty().bind(cm.lesCapteurStringProperty());
            listMCapt.itemsProperty().bind(mc.lesMCapteurStringProperty());
        }else {
            UCMegaCaptController UC = new UCMegaCaptController(c,this);
            UCparent.getChildren().add(UC);
            
        }
        majList();
    }
        
    
        void majList(){
            cm.setLesCapteurString(cm.List2String());
            mc.setLesMCapteurString(mc.List2String());
            listCapt.itemsProperty().bind(cm.lesCapteurStringProperty());
            listMCapt.itemsProperty().bind(mc.lesMCapteurStringProperty());
         }
    }
    
    

