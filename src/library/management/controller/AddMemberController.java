/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import library.management.database.Database;
import library.management.model.Book;
import library.management.model.Member;
import library.management.model.MemberDAO;

/**
 * FXML Controller class
 *
 * @author SPELL
 */
public class AddMemberController implements Initializable {

    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField mobileField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton closeBtn;
    
    MemberDAO memberDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        memberDAO = new MemberDAO();
        
    }    

    @FXML
    private void saveToDB(ActionEvent event) throws SQLException {
        
        String id = idField.getText();
        String name = nameField.getText();
        String mobile = mobileField.getText();
        String address = addressField.getText();
        
        Member member = new Member(id,name,mobile,address);
        
        memberDAO.saveMemberToDB(member);
        
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        
    }
    
}
