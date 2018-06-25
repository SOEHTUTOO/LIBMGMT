/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import library.management.database.Database;

/**
 *
 * @author SPELL
 */
public class IssueDAO {

    public void issueBook(Issue issue) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();
        
        String sql = "insert into libdb.issue (book_id,member_id,issue_date,renew_count) values (?,?,curDate(),0)";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, issue.getBookID());
        stmt.setString(2, issue.getMemberID());
        stmt.execute();
    }

    public Issue searchIssue(String bookID) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();
        
        Issue issue = null;
        
        String sql = "select * from libdb.issue where book_id=?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, bookID);
        ResultSet result = stmt.executeQuery();
        
        while(result.next()){
        
            String memberID = result.getString("member_id");
            Date issueDate = result.getDate("issue_date");
            int renewCount = result.getInt("renew_count");
        
            issue = new Issue (bookID,memberID,issueDate,renewCount);
        }
        
        return issue;
    }

    public void submitBook(String bookID) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();
        
        String sql = "delete from libdb.issue where book_id=?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, bookID);
        stmt.execute();
        
    }

    public void renewIssue(String bookID) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();
        
        String sql = "update libdb.issue set renew_count=renew_count+1 where book_id=?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, bookID);
        stmt.execute();
        
    }

    
}
