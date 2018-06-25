/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SPELL
 */
public class PreferenceController implements Initializable {

    @FXML
    private TextField serverField;
    @FXML
    private Spinner<Integer> portSpinner;
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button closeBtn;
    
    private Preferences pref;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pref = Preferences.userRoot().node("libdb");
        String host = pref.get("host", "localhost");
        int port = pref.getInt("port", 3306);
        String username = pref.get("username", "root");
        String password = pref.get("password", "");
        
        
        SpinnerValueFactory<Integer> intFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3200, 3400, port);
        portSpinner.setValueFactory(intFactory);
        portSpinner.editableProperty().setValue(Boolean.TRUE);
        
        serverField.setText(host);
        userField.setText(username);
        passField.setText(password);
        
    }    

    @FXML
    private void savePref(ActionEvent event) {
        
        String host = serverField.getText();
        int port = portSpinner.getValue();
        String username = userField.getText();
        String password = passField.getText();
        
        pref.put("host", host);
        pref.putInt("port", port);
        pref.put("username", username);
        pref.put("password", password);
        //Here is 
        
        
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        
    }
    
}
