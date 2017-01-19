/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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

/**
 *
 * @author Léandre
 */
public class MegaCapteurController2 implements Initializable{
    
    @FXML
    private ListView listCapt, listMCapt;
    @FXML
    private ComboBox poids, algo, gen;
    @FXML
    private TextField capt;
    
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
        
        //poids.itemsProperty().bindBidirectional(cm.FindCapteur(capt.getSelectedText()).poidProperty());
        //System.out.println(cm.FindCapteur(capt.getSelectedText()).getPoid());
        this.poids.getItems().setAll("1","2","3");
        this.algo.getItems().setAll("Défaut","Borne","Fenetre");
        this.gen.getItems().setAll(getListTps());
        
        //algoSP.setValue(cm.FindCapteur(capt.getSelectedText()).);
        
    }
    
    
    //Méthode appelé lorsqu'on veut ajouter un élement au mégaCapteur.
    @FXML
    private void ajoutMC(){
        mc.ajouterC(cm.FindCapteur(listCapt.getSelectionModel().getSelectedItems().toString())); 
    }
    
    
    //Méthode appelé lorqu'on clique sur un des élements de la liste du MégaCpateur.
    @FXML
    private void ModifC(){
        captSP.setValue(listMCapt.getSelectionModel().getSelectedItem().toString());
        capt.textProperty().bind(getCaptSP());
    }
    
    
    //Méthode qui retourne une liste de nombre entier.
    private List getListTps(){
        List<Integer> l= new ArrayList<>();
        for(int i=1; i<31;i++){
            l.add(i);
        }
        return l;
    }
    
    
}
