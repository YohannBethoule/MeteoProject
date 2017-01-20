/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.ICapteur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author BASTIEN
 */
public class UCMegaCaptController extends HBox implements Initializable {

    @FXML
    ComboBox poids;
    
    private ICapteur c;
     MegaCapteurController2 mcc;
    
    public UCMegaCaptController(ICapteur c, MegaCapteurController2 m){
        try{
           FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/UCMegaCapt.fxml"));
           f.setController(this);
           f.setRoot(this);
           f.load();
        }catch(Exception e){
            System.out.println("Controller.UCCaptController.<init>()");
        }
        this.c=c;
        poids.getSelectionModel().select(getPoidAffich(c));
        mcc=m;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.poids.getItems().setAll("1","2","3");
    }    
    
    @FXML
    void Appliquer(){
        this.c.poids=poids.getSelectionModel().getSelectedIndex()+1;
        showMessage();
        mcc.majList();
    }
    
    //Méthode qui retourne une position pour afficher le poid du Capteur ou MegaCapteur à l'instant t.
    int getPoidAffich(ICapteur c)
    {
        if(c.poids==1){
            return 0;
        }else if (c.poids==2){
            return 1;
        }
        return 2;
    }
    
     private void showMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("MegaCapteur modifié! ");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
