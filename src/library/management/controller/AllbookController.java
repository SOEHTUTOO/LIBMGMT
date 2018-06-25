/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.management.database.Database;
import library.management.model.Book;
import library.management.model.BookDAO;

/**
 * FXML Controller class
 *
 * @author SPELL
 */
public class AllbookController implements Initializable {

    @FXML
    private TableView<Book> tbView;
    @FXML
    private TableColumn<Book, String> idCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, String> pubCol;
    @FXML
    private TableColumn<Book, Boolean> avaCol;
    @FXML
    private MenuItem deleteMenu;
    
    BookDAO bookDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bookDAO = new BookDAO();
        
        ObservableList list = FXCollections.observableArrayList();
        
        try {
            list = bookDAO.getAllBook();
        } catch (SQLException ex) {
        }
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        pubCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        avaCol.setCellValueFactory(new PropertyValueFactory<>("available"));
        
        tbView.getItems().addAll(list);
        
        
    }    

    @FXML
    private void deleteBook(ActionEvent event) throws SQLException {
        
        Book book = tbView.getSelectionModel().getSelectedItem();
        tbView.getItems().remove(book);
        
        bookDAO.removeBook(book.getId());
        
    }
    
}
