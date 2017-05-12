/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import otherAddOn.DbConnect;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author linhphan
 */
public class User implements Serializable {
    private int userID;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String address;
    private int phone;
    

    
    public User() {
        
    }
    
    // contrustor dang ki nguoi dung moi vao csdl
    public User(String username, String password, String fullName, String email, String address, int phone) throws SQLException, ClassNotFoundException {
        
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        
//        DbConnect connect = new DbConnect();
//        String query = "insert into User(UserName, PassWord, FullName, Email, Address, Phone) values ('"+username+"',md5('"+password+"'), '"+fullName+"' , '"+email+"', N'"+address+"', "+phone+")"  ;
//        PreparedStatement prSt = connect.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//        prSt.executeUpdate();
//        ResultSet keys = prSt.getGeneratedKeys();
//        keys.next();
//        userID = keys.getInt(1);
    }
    
    // contructor lay tu csdl
    public User(int userID) throws SQLException, ClassNotFoundException{
        DbConnect connect = new DbConnect();
        String query = "select UserName, PassWord, FullName, Email, Address, Phone from User where UserID = "+userID;
        ResultSet rs = connect.st.executeQuery(query);
        rs.next();
        this.userID = userID;
        this.username = rs.getString("UserName");
        this.password = rs.getString("PassWord");
        this.fullName = rs.getString("FullName");
        this.email = rs.getString("Email");
        this.address = rs.getString("Address");
        this.phone = rs.getInt("Phone");
           
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    public static User CheckLogin(String username, String password) throws SQLException, ClassNotFoundException {

        DbConnect connect = new DbConnect() ;
         String query = "SELECT UserID FROM User WHERE UserName = N'"+username+"' AND PassWord = md5('"+password+"')" ;
         ResultSet rs = connect.st.executeQuery(query);
         if(rs.next()) {
             int userID = rs.getInt("UserID") ;
             User user = new User(userID);
             return user;
         }
         else {
             return null;
         }
    }
    
    public static boolean checkUsername(String username) throws SQLException, ClassNotFoundException {
        DbConnect connect = new DbConnect();
        String sql = "SELECT * FROM User WHERE UserName = '" + username + "'";
        
        ResultSet rs = connect.st.executeQuery(sql);
        while(rs.next()) {
            connect.con.close();
            return true;
        }
        
        return false;
        
    }
    
}

