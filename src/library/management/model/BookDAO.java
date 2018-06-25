/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.management.database.Database;

/**
 *
 * @author SPELL
 */
public class BookDAO {
    
    public void saveBookToDB(Book book) throws SQLException{
        
        Connection conn = Database.getInstance().getConnection();
    
        String sql = "insert into libdb.book (id,title,author,publisher) values (?,?,?,?)";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, book.getId());
        stmt.setString(2, book.getTitle());
        stmt.setString(3, book.getAuthor());
        stmt.setString(4, book.getPublisher());
        stmt.execute();
    
    }
    
    public ObservableList<Book> getAllBook() throws SQLException{
        
        Connection conn = Database.getInstance().getConnection();
    
        String sql = "select * from libdb.book";
        
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        
        while(result.next()){
        
            String id = result.getString("id");
            String title = result.getString("title");
            String author = result.getString("author");
            String publisher = result.getString("publisher");
            Boolean available = result.getBoolean("available");
            
            Book book = new Book (id,title,author,publisher,available);
            
            bookList.add(book);
            
        }
    
        return bookList;
    }
    
    public void removeBook(String id) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();
        
        String sql = "delete from libdb.book where id=?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.execute();
        
    }

    public Book searchBook(String bookID) throws SQLException{
        
        Book book=null;
        
        Connection conn = Database.getInstance().getConnection();
            
        String sql = "select * from libdb.book where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, bookID);
        ResultSet result = stmt.executeQuery();
        
        while(result.next()){
        
            String title = result.getString("title");
            String author = result.getString("author");
            String publisher = result.getString("publisher");
            boolean available = result.getBoolean("available");
            
            book = new Book(bookID,title,author,publisher,available);
        
        }

        return book;
    }

    public void updateBook(boolean b, String bookID) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();
        
        String sql = "update libdb.book set available=? where id=?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setBoolean(1, b);
        stmt.setString(2, bookID);
        stmt.execute();
        
    }
    
}
