/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.model;

import java.sql.Date;

/**
 *
 * @author SPELL
 */
public class Issue {
    
    private String bookID;
    private String memberID;
    private Date issueDate;
    private int renewCount;

    public Issue(String bookID, String memberID, Date issueDate, int renewCount) {
        this.bookID = bookID;
        this.memberID = memberID;
        this.issueDate = issueDate;
        this.renewCount = renewCount;
    }

    public Issue(String bookID, String memberID) {
        this.bookID = bookID;
        this.memberID = memberID;
    }
    
    

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getRenewCount() {
        return renewCount;
    }

    public void setRenewCount(int renewCount) {
        this.renewCount = renewCount;
    }
    
    
    
}
