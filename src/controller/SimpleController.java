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
import Metier.ICapteur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Léandre
 */
public class SimpleController implements Initializable {
    
    @FXML
    private Spinner<Integer> min,max,init,fenetre,interDefault,interBorne,interFenetre;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        min.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        max.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        init.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        fenetre.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        interDefault.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 5));
        interBorne.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 5));
        interFenetre.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 5));
    }
    
    @FXML
    private void algo1() throws Exception{
        GenerationTemperature gt = new Borne();
        ICapteur c= new Capteur(gt,interDefault.getValue());
        ((Capteur) c).algo="Defaut";
        ICapteur.cm.ajoutCapteur(c);
        
        showMessage(c);
    }
    
    @FXML
    private void algo2() throws Exception{
        if(min.getValue()>max.getValue()){
            showErre();
        }else{
            GenerationTemperature gt = new Borne(min.getValue(),max.getValue());
            ICapteur c= new Capteur(gt, interBorne.getValue());
           ((Capteur) c).algo="Borne";
            ICapteur.cm.ajoutCapteur(c);
            showMessage(c);
        }
    }
    
    @FXML
    private void algo3() throws Exception{
        GenerationTemperature gt = new Fenetre(init.getValue(),fenetre.getValue());
        ICapteur c= new Capteur(gt, interFenetre.getValue());
        ((Capteur) c).algo="Fenetre";
        ICapteur.cm.ajoutCapteur(c);
        showMessage(c);
    }
    
    private void showMessage(ICapteur c) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("Capteur "+c.toString()+" ajouté !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void showErre() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("Le min doit être inférieur au max !");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
}
