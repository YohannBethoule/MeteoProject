/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.Borne;
import Metier.Capteur;
import Metier.GenerationTemperature;
import Metier.ICapteur;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author BASTIEN
 */
public class UCCaptController extends HBox implements Initializable {

    @FXML
    ComboBox poids;
    @FXML
    ComboBox algo;
    @FXML
    ComboBox gen;
    @FXML
    TextField capt;

    Capteur c;
    MegaCapteurController2 mcc;
    
    public UCCaptController(Capteur c, MegaCapteurController2 m){
        try{
           FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/UCCapt.fxml"));
           f.setController(this);
           f.setRoot(this);
           f.load();
        }catch(Exception e){
            System.out.println("Controller.UCCaptController.<init>()");
        }
        this.c=c;
        poids.getSelectionModel().select(getPoidAffich(c));
        algo.getSelectionModel().select(getAlgoAffich((Capteur)c));

        gen.getSelectionModel().select(getTpsAffich(c));
        capt.appendText(c.toString());
        mcc=m;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.poids.getItems().setAll("1","2","3");
        this.algo.getItems().setAll("Defaut","Borne","Fenetre");
        this.gen.getItems().setAll(getListTps());
    }    
    //Méthode qui retourne une liste de nombre entier.
    private List getListTps(){
        List<Integer> l= new ArrayList<>();
        for(int i=1; i<31;i++){
            l.add(i);
        }
        return l;
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
    
    //Méthode qui retourne une position pour afficher l'algo du Capteur ou MegaCapteur à l'instant t.
    int getAlgoAffich(Capteur c){
        if(c.algo.equals("Defaut")){
            return 0;
        }else if(c.algo.equals("Borne")){
            return 1;
        }
        return 2;
    }
    
    //Méthode qui retourne une position pour afficher la durée de l'intervalle de regéneration du Capteur ou MegaCapteur à l'instant t.
    int getTpsAffich(ICapteur c){
        return (int)c.IntGeneration/1000-1;
    }
    
    @FXML
    void Appliquer(){
        int algSel=algo.getSelectionModel().getSelectedIndex();
        int PoiSel= poids.getSelectionModel().getSelectedIndex();
        int tpsSel= gen.getSelectionModel().getSelectedIndex();
        this.c.poids=PoiSel+1;
        this.c.IntGeneration=(tpsSel+1)*1000;

        if(algSel==0){
            this.c.setAlgo(new Borne());
            this.c.algo="Defaut";
            mcc.UCparent.getChildren().clear();
            showMessage();
        }else if (algSel==1){
            this.c.algo="Borne";
            UCAlgoController UC = new UCAlgoController(this.c,"Borne",mcc);
            UC.lab1.setText("Min :");
            UC.lab2.setText("Max :");
            mcc.UCparent.getChildren().clear();
            mcc.UCparent.getChildren().add(UC);
        }else{
            this.c.algo="Fenetre";
            UCAlgoController UC = new UCAlgoController(this.c,"Fenetre",mcc);
            UC.lab1.setText("t° initiale :");
            UC.lab2.setText("Fenetre :");
            mcc.UCparent.getChildren().clear();
            mcc.UCparent.getChildren().add(UC);
        } 
    }
    
     private void showMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("Capteur modifié! ");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
