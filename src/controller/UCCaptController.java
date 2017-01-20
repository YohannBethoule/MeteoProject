/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.Capteur;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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
    
    public UCCaptController(Capteur c){
        try{
           FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/UCCapt.fxml"));
           f.setController(this);
           f.setRoot(this);
           f.load();
           System.out.println("Controller.UCCaptController.<init>()");
        }catch(Exception e){
            System.out.println("Controller.UCCaptController.<init>()");
        }
        this.c=c;
        poids.getSelectionModel().select(getPoidAffich(c));
        algo.getSelectionModel().select(getAlgoAffich((Capteur)c));

        gen.getSelectionModel().select(getTpsAffich(c));
        capt.appendText(c.toString());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.poids.getItems().setAll("1","2","3");
        this.algo.getItems().setAll("Défaut","Borne","Fenetre");
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
            return 1;
        }else if(c.algo.equals("Borne")){
            return 2;
        }
        return 3;
    }
    
    //Méthode qui retourne une position pour afficher la durée de l'intervalle de regéneration du Capteur ou MegaCapteur à l'instant t.
    int getTpsAffich(ICapteur c){
        return (int)c.IntGeneration/1000-1;
    }
    
    
}
