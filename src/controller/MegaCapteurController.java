/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import Metier.MegaCapteur;
import javafx.scene.control.ComboBox;

/**
 *
 * @author bagandboeu
 */
public class MegaCapteurController implements Initializable{
    @FXML
    private Spinner<Integer> min,max,init,fenetre,dureeR;
    
    @FXML
    private ComboBox list;
    
    private MegaCapteur mc;
    private CapteurManager cm;
    
    public MegaCapteurController(MegaCapteur mc, CapteurManager cm){
        this.mc=mc;
        this.cm=cm;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        min.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        max.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        init.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        fenetre.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 40, 0));
        dureeR.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60,5));

        list.itemsProperty().bind(cm.lesCapteurStringProperty());
        list.getSelectionModel().select(1);
    }    
    
    @FXML
    private void ajout1() throws Exception{
        GenerationTemperature gt = new Borne();
        
        ICapteur c= new Capteur(gt);
        
        mc.ajouterC(c);
        
    }
    
    @FXML
    private void ajout2() throws Exception{
        GenerationTemperature gt = new Borne(min.getValue(),max.getValue());
        ICapteur c= new Capteur(gt);
        
        mc.ajouterC(c);
    }
    
    @FXML
    private void ajout3() throws Exception{
        GenerationTemperature gt = new Fenetre(init.getValue(),fenetre.getValue());
        ICapteur c= new Capteur(gt);
        
        mc.ajouterC(c);
    }
    
    @FXML
    private void ajoutExist() throws Exception{
        this.mc.ajouterC(cm.FindCapteur(list.getSelectionModel().getSelectedItem().toString()));
    }
    
    @FXML
    private void creer() throws Exception{
        //mc.setIntervalleRegeneration(dureeR.getValue());
        this.cm.ajoutCapteur(this.mc);
        MeteoWindowController mw = new MeteoWindowController(mc);
        Stage stage = new Stage();
        FXMLLoader f = new FXMLLoader(getClass().getResource("/meteoproject/MeteoWindow.fxml"));
        f.setController(mw);
        Parent root = f.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        fenetre.getScene().getWindow().hide();
        stage.show();
        
    }
}
