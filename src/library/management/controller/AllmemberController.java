/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.management.model.Member;
import library.management.model.MemberDAO;

/**
 * FXML Controller class
 *
 * @author SPELL
 */
public class AllmemberController implements Initializable {

    @FXML
    private TableView<Member> tbView;
    @FXML
    private MenuItem deleteMenu;
    @FXML
    private TableColumn<Member, String> idCol;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> mobileCol;
    @FXML
    private TableColumn<Member, String> addressCol;
    
    MemberDAO memberDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        memberDAO = new MemberDAO();
        
        ObservableList list = FXCollections.observableArrayList();
        
        try {
            list = memberDAO.getAllMember();
        } catch (SQLException ex) {
        }
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        tbView.getItems().addAll(list);
        
    }    

    @FXML
    private void deleteMember(ActionEvent event) throws SQLException {
        
        Member member = tbView.getSelectionModel().getSelectedItem();
        tbView.getItems().remove(member);
        
        memberDAO.removeMember(member.getId());
        
    }
    
}
