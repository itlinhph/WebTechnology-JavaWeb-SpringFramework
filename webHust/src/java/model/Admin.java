/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import otherAddOn.DbConnect;
import com.mysql.cj.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author linhphan
 */
public class Admin implements Serializable{
    private String username;
    private String password;
    private String name;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Admin(String username, String password, String name) throws SQLException, ClassNotFoundException {
        this.username = username;
        this.password = password;
        this.name = name;
        
        DbConnect connect = new DbConnect();
        String query = "insert into admin(UserName, Password, Name) value ('"+username+"',md5("+password+"), '"+name+"')" ;
        PreparedStatement prSt = (PreparedStatement) connect.con.prepareStatement(query) ;
        prSt.executeUpdate();
    }
    
    public Admin(String username) throws SQLException, ClassNotFoundException {
        DbConnect connect = new DbConnect();
        String query = "Select UserName, PassWord, Name From admin WHERE UserName= N'"+username+"' ";
        ResultSet rs = connect.st.executeQuery(query);
        rs.next();
        this.username = username;
        this.password = rs.getString("PassWord");
        this.name = rs.getString("Name");
    }
    
//    public Admin (String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
    
    public static Admin CheckAdmin(String username, String password) throws SQLException, ClassNotFoundException {
        
        DbConnect connect = new DbConnect();
        String query ="Select UserName From admin Where UserName = N'"+username+"' AND PassWord = N'"+password+"' ";
        ResultSet rs = connect.st.executeQuery(query);
        if(rs.next()) {
             Admin ad = new Admin(username);
             return ad;
        }
        
        return null;
    }
    
//    public boolean isAdmin(String username, String password) {
//        return username.equals("admin") && password.equals("admin");
//    }
}
