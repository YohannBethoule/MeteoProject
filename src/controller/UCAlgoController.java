/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.Borne;
import Metier.Capteur;
import Metier.Fenetre;
import Metier.GenerationTemperature;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author BASTIEN
 */
public class UCAlgoController extends HBox implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label lab1,lab2;
    @FXML
    ComboBox c1,c2;
    
    private Capteur c;
    private VBox p;
    private MegaCapteurController2 mcc;
    public UCAlgoController(Capteur c, String algo, MegaCapteurController2 m){
        this.c=c;
        mcc=m;
        try{
        FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/UCAlgo.fxml"));
        f.setController(this);
        f.setRoot(this);
        f.load();
        }catch(Exception e){
            System.out.println("Controller.UCAlgoController.<init>()");
        }
        if(algo.equals("Borne")){
            this.c2.getItems().setAll(getListInt(-10,40));
        }else{
            this.c2.getItems().setAll(getListInt(1,20));
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.c1.getItems().setAll(getListInt(-10,40));
        
    }    
    
    
    
    @FXML
    void confirmer(){
        GenerationTemperature gt;
        int sel1= c1.getSelectionModel().getSelectedIndex();
        int sel2= c2.getSelectionModel().getSelectedIndex();
        sel1=sel1-10;
        if(lab1.getText().equals("Min :")){   
            sel2=sel2-10;
            if(sel1>sel2){
                showErre();
            }else{
                gt= new Borne(sel1, sel2);
                showMessage();
                mcc.UCparent.getChildren().clear();
                mcc.majList();
            }
        }else{
            sel2=sel2+1;
            gt=new Fenetre(sel1, sel2);
            showMessage();
            mcc.UCparent.getChildren().clear();
            mcc.majList();
        }
    }
    
    
    private void showMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("Capteur modifié! ");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    //Méthode qui retourne une liste de nombre entier.
    private List getListInt(int deb, int max){
        List<Integer> l= new ArrayList<>();
        for(int i=deb; i<max ;i++){
            l.add(i);
        }
        return l;
    }
    
    private void showErre() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("Le min doit être inférieur au max !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
