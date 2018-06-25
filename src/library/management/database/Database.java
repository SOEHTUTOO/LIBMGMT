/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;

/**
 *
 * @author SPELL
 */
public class Database {
    
    private Connection conn;
    
    private static Database database;
    
    public Database() throws SQLException{
    
        connect();
        createDB();
        createTB();
    
    }
    
    public static Database getInstance() throws SQLException{
    
        if(database==null){
        
            database = new Database();
        
        }
    
        return database;
    }
    
    private void connect() throws SQLException{
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
        }
        
        Preferences pref = Preferences.userRoot().node("libdb");
        String host = pref.get("host", "localhost");
        int port = pref.getInt("port", 3306);
        String username = pref.get("username", "root");
        String password = pref.get("password", "");
        
        
        conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port,username,password);
    
    }
    
    private void disconnect() throws SQLException{
    
        if(conn!=null){
            conn.close();
        }
        
    }
    
    private void createDB() throws SQLException{

        String sql = "create database if not exists libdb";
        
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        
    }
    
    private void createTB() throws SQLException{
    
        String bookSql = "create table if not exists libdb.book (id varchar(10) primary key, title varchar(50), author varchar(50), publisher varchar(50), available boolean default true)";
        String memberSql = "create table if not exists libdb.member (id varchar(10) primary key, name varchar(50), mobile varchar(30), address varchar(225))";
        String issueSql = "create table if not exists libdb.issue (book_id varchar(10), foreign key (book_id) references book(id), member_id varchar(10), foreign key (member_id) references member(id), issue_date date, renew_count int)";
        Statement stmt = conn.createStatement();
        stmt.execute(bookSql);
        stmt.execute(memberSql);
        stmt.execute(issueSql);
    
    }
    
    
    public Connection getConnection(){
    
        return conn;
        
    }
    

    
    
}
