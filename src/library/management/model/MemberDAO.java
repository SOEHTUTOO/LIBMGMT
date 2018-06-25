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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.management.database.Database;

/**
 *
 * @author SPELL
 */
public class MemberDAO {
    
    public void saveMemberToDB(Member member) throws SQLException{
        
        Connection conn = Database.getInstance().getConnection();
    
        String sql = "insert into libdb.member (id,name,mobile,address) values (?,?,?,?)";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, member.getId());
        stmt.setString(2, member.getName());
        stmt.setString(3, member.getMobile());
        stmt.setString(4, member.getAddress());
        stmt.execute();
    
    }
    
    

    public ObservableList<Member> getAllMember() throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();
        
        String sql = "select * from libdb.member";
        
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        
        ObservableList<Member> memberList = FXCollections.observableArrayList();
        
        while(result.next()){
        
            String id = result.getString("id");
            String name = result.getString("name");
            String mobile = result.getString("mobile");
            String address = result.getString("address");
            
            Member member = new Member (id,name,mobile,address);
            
            memberList.add(member);
            
        }
    
        return memberList;
    }
    
    public void removeMember(String id) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();
        
        String sql = "delete from libdb.member where id=?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.execute();
        
    }

    public Member searchMember(String memberID) throws SQLException {
        
        Member member = null;
        
        Connection conn = Database.getInstance().getConnection();
            
        String sql = "select * from libdb.member where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, memberID);
        ResultSet result = stmt.executeQuery();
        
        while(result.next()){
        
            String name = result.getString("name");
            String mobile = result.getString("mobile");
            String address = result.getString("address");
            
            
            member = new Member(memberID,name,mobile,address);
        
        }

        return member;
    }
    
}
