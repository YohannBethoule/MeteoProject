/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Metier.ICapteur.cm;
import Metier.MegaCapteur;
import java.net.URL;
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
    
    @FXML
    private void ajoutMC(){
        captSP.setValue(listCapt.getSelectionModel().getSelectedItem().toString());
        capt.textProperty().bind(getCaptSP());
    }
    
    
}
