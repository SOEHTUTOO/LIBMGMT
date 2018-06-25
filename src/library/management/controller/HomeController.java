/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import library.management.model.Book;
import library.management.model.BookDAO;
import library.management.model.Issue;
import library.management.model.IssueDAO;
import library.management.model.Member;
import library.management.model.MemberDAO;

/**
 *
 * @author SPELL
 */
public class HomeController implements Initializable {
    
    @FXML
    private MenuItem closeItem;
    @FXML
    private JFXButton bookBtn;
    @FXML
    private JFXButton memberBtn;
    @FXML
    private JFXButton addBookBtn;
    @FXML
    private JFXButton addMemberBtn;
    @FXML
    private JFXTextField bookSearchField;
    @FXML
    private Label bookTitle;
    @FXML
    private Label bookAuthor;
    @FXML
    private Label bookPublisher;
    @FXML
    private Label bookStatus;
    
    BookDAO bookDAO;
    MemberDAO memberDAO;
    IssueDAO issueDAO;
    
    @FXML
    private JFXTextField memberSearchField;
    @FXML
    private Label memberName;
    @FXML
    private Label memberMobile;
    @FXML
    private Label memberAddress;
    @FXML
    private JFXButton issueBtn;
    @FXML
    private JFXTextField issueSearchField;
    @FXML
    private Label issueTitle;
    @FXML
    private Label issueAuthor;
    @FXML
    private Label issueDate;
    @FXML
    private Label issueName;
    @FXML
    private Label issueMobile;
    @FXML
    private Label issueCount;
    @FXML
    private JFXButton submitBtn;
    @FXML
    private JFXButton renewBtn;
    @FXML
    private MenuItem prefItem;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bookDAO = new BookDAO();
        memberDAO = new MemberDAO();
        issueDAO = new IssueDAO();
        
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) bookBtn.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void showAllBook(ActionEvent event) throws IOException {
        
        createWindow("/library/management/view/allbook.fxml");
   
    }

    @FXML
    private void showAllMember(ActionEvent event) throws IOException {
        
        createWindow("/library/management/view/allmember.fxml");
        
    }

    @FXML
    private void openAddBookWindow(ActionEvent event) throws IOException {
        
        createWindow("/library/management/view/addbook.fxml");
        
    }

    @FXML
    private void openAddMemberWindow(ActionEvent event) throws IOException {
        
        createWindow("/library/management/view/addmember.fxml");
        
    }
    
    public void createWindow(String url) throws IOException{
    
        Stage stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource(url));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
       
        stage.show();
    
    }

    @FXML
    private void searchBook(ActionEvent event) throws SQLException{
        
        String bookID = bookSearchField.getText();
        
        Book book = bookDAO.searchBook(bookID);
        
        if(book==null){
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Book Not Found");
            alert.show();
            
            return;
        
        }
        
        bookTitle.setText("Title\t\t\t-\t"+book.getTitle());
        bookAuthor.setText("Author\t\t-\t"+book.getAuthor());
        bookPublisher.setText("Publisher\t\t-\t"+book.getPublisher());
        String status = book.isAvailable()?"YES":"NO";
        
//        if(book.isAvailable()){
//            status = "YES";
//        }else{
//            status = "NO";
//        }

        bookStatus.setText("Available\t\t-\t"+status);
        
    }

    @FXML
    private void searchMember(ActionEvent event) throws SQLException {
        
        String memberID = memberSearchField.getText();
        
        Member member = memberDAO.searchMember(memberID);
        
        if(member==null){
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Member Not Found");
            alert.show();
            
            return;
        
        }
        
        memberName.setText("Name\t\t-\t"+member.getName());
        memberMobile.setText("Mobile\t\t-\t"+member.getMobile());
        memberAddress.setText("Address\t\t-\t"+member.getAddress());
        
    }

    @FXML
    private void issueBook(ActionEvent event){
        
        String bookID = bookSearchField.getText();
        String memberID = memberSearchField.getText();
        
        Issue issue = new Issue(bookID,memberID);
        
        try {
            issueDAO.issueBook(issue);
            bookDAO.updateBook(false, bookID);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Issued");
            alert.setHeaderText(null);
            alert.show();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error");
            alert.setHeaderText(null);
            alert.show();
        }
        
    }

    @FXML
    private void searchIssue(ActionEvent event) throws SQLException {
        
        String bookID = issueSearchField.getText();
        
        Issue issue = issueDAO.searchIssue(bookID);
        
        Book book = bookDAO.searchBook(bookID);
        
        Member member = memberDAO.searchMember(issue.getMemberID());
        
        issueTitle.setText("Title\t\t-\t"+book.getTitle());
        issueName.setText("Name\t-\t"+member.getName());
        issueAuthor.setText("Author\t-\t"+book.getAuthor());
        issueMobile.setText("Mobile\t-\t"+member.getMobile());
        issueCount.setText("Count\t-\t"+issue.getRenewCount());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMMM/YYYY");
        String dateStr = dateFormat.format(issue.getIssueDate());
        issueDate.setText("Date\t\t-\t"+dateStr);
        
    }

    @FXML
    private void submitBook(ActionEvent event) throws SQLException {
        
        String bookID = issueSearchField.getText();
        
        issueDAO.submitBook(bookID);
        bookDAO.updateBook(true, bookID);
        
    }

    @FXML
    private void renewBook(ActionEvent event) throws SQLException {
        
        String bookID = issueSearchField.getText();
        
        issueDAO.renewIssue(bookID);
        
    }

    @FXML
    private void openSetting(ActionEvent event) throws IOException {
        
        createWindow("/library/management/view/preference.fxml");
        
    }
    
}
