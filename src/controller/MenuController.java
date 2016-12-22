/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import Metier.Borne;
import Metier.Capteur;
import Metier.Fenetre;
import Metier.GenerationTemperature;
import Metier.ICapteur;
import Metier.MegaCapteur;

/**
 * FXML Controller class
 *
 * @author bagandboeu
 */
public class MenuController implements Initializable {
    
    @FXML
    private Spinner<Integer> min,max,init,fenetre,interDefault,interBorne,interFenetre;
    
    /**
     * Initializes the controller class.
     */
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
        
        MeteoWindowController mw = new MeteoWindowController(c);
        Stage stage = new Stage();
        FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/MeteoWindow.fxml"));
        f.setController(mw);
        Parent root = f.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void algo2() throws Exception{
        GenerationTemperature gt = new Borne(min.getValue(),max.getValue());
        ICapteur c= new Capteur(gt);
        
        MeteoWindowController mw = new MeteoWindowController(c);
        Stage stage = new Stage();
        FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/MeteoWindow.fxml"));
        f.setController(mw);
        Parent root = f.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void algo3() throws Exception{
        GenerationTemperature gt = new Fenetre(init.getValue(),fenetre.getValue());
        ICapteur c= new Capteur(gt);
        
        MeteoWindowController mw = new MeteoWindowController(c);
        Stage stage = new Stage();
        FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/MeteoWindow.fxml"));
        f.setController(mw);
        Parent root = f.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void newMega() throws IOException{
        ICapteur mc = new MegaCapteur();
        
        MegaCapteurController mcc= new MegaCapteurController((MegaCapteur) mc);
        Stage stage = new Stage();
        FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/MegaCapteur.fxml"));
        f.setController(mcc);
        Parent root = f.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
