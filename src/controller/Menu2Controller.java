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
import Metier.CapteurManager;
import Metier.Fenetre;
import Metier.GenerationTemperature;
import Metier.ICapteur;
import static Metier.ICapteur.cm;
import Metier.MegaCapteur;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author bagandboeu
 */
public class Menu2Controller implements Initializable {
    
    @FXML
    private ListView listCapt;
    
    private CapteurManager cm;

    public Menu2Controller() {
        this.cm = ICapteur.cm;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	listCapt.itemsProperty().bind(cm.lesCapteurStringProperty());
        listCapt.getSelectionModel().select(1);
    }    
    
    @FXML
    private void newCapt() throws IOException{
        Stage stage = new Stage();
        FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/CapteurSimple.fxml"));
        Parent root = f.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void newMCapt() throws IOException{
        MegaCapteur mc = new MegaCapteur();
        
        MegaCapteurController2 mcc = new MegaCapteurController2(mc);
        Stage stage = new Stage();
        FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/MegaCapteur2.fxml"));
        f.setController(mcc);
        Parent root = f.load();
        Scene scene = new Scene(root);
        
        stage.setOnCloseRequest(event -> cm.ajoutCapteur(mc));
        
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void visualiser() throws Exception {
        MeteoWindowController mw = new MeteoWindowController(cm.FindCapteur(listCapt.getSelectionModel().getSelectedItem().toString()));
        Stage stage = new Stage();
        FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/MeteoWindow.fxml"));
        f.setController(mw);
        Parent root = f.load();
        stage.setOnCloseRequest(event -> mw.stop());
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
