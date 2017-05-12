/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import otherAddOn.DbConnect;

/**
 *
 * @author linhphan
 */
public class UserData {
    
    
    // thêm người dùng mới.
    public static User addUser(String username, String password, String fullName, String email, String address, int phone) throws SQLException, ClassNotFoundException {
        
        User user = new User() ;
        DbConnect connect = new DbConnect();
        try {
            String query = "insert into User(UserName, PassWord, FullName, Email, Address, Phone) values ('"+username+"',md5('"+password+"'), '"+fullName+"' , '"+email+"', N'"+address+"', "+phone+")"  ;
            PreparedStatement prSt = connect.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prSt.executeUpdate();
            ResultSet keys = prSt.getGeneratedKeys();
            keys.next();
            
            user.setUserID(keys.getInt(1));
            user.setUsername(username);
            user.setFullName(fullName);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);
            
            return user;

        } catch(SQLException e) {
            return null;
        }


    }
    // lay danh sach nguoi dung
    public static ArrayList<User> getUserList() throws SQLException, ClassNotFoundException {
        
        ArrayList<User> userList = new ArrayList<User>();
        DbConnect connect = new DbConnect();
        String query ="Select * from User";
        
        try {
            PreparedStatement prSt = (PreparedStatement)connect.con.prepareStatement(query);
            ResultSet rs = prSt.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUsername(rs.getNString("UserName"));
                user.setFullName(rs.getNString("FullName"));
                user.setEmail(rs.getNString("Email"));
                user.setAddress(rs.getNString("Address"));
                user.setPhone(rs.getInt("Phone"));
                userList.add(user);
            }
            connect.con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    
    // xóa người dùng đã có
    public static String deleteUser(String userId) throws SQLException {
        try {
            // khong kiem tra khoa ngoai de ko anh huong toi hoa don
             DbConnect connect = new DbConnect();
             String query1 = "SET FOREIGN_KEY_CHECKS=0;" ;
             PreparedStatement ps1 = connect.con.prepareStatement(query1);
             int rs1 = connect.st.executeUpdate(query1);
             
             String query2 = "DELETE FROM User WHERE UserID = '"+userId+"' " ;
             PreparedStatement ps2 = connect.con.prepareStatement(query2);
             int rs2 = connect.st.executeUpdate(query2);
             
             String query3 = "SET FOREIGN_KEY_CHECKS=1;" ;
             PreparedStatement ps3 = connect.con.prepareStatement(query3);
             int rs3 = connect.st.executeUpdate(query3);
             connect.con.close();
             return null;
             
        } catch (Exception e) {
             return e.getMessage().toString() ;
        }
    }
    
    // cập nhật người dùng 
    public static User updateUser(int userId,String address, int phone, String email, String name, String pass) throws SQLException {
        
        try {
             
             DbConnect connect = new DbConnect();
             String query = "UPDATE User SET Address = N'"+address+"' , Phone = '"+phone+"', Email= N'"+email+"',  FullName = N'"+name+"', PassWord = md5('"+pass+"')  WHERE (UserID='"+userId+"')" ;
             PreparedStatement ps = (PreparedStatement) connect.con.prepareStatement(query);
            
             int rs = connect.st.executeUpdate(query);
            
            User user = new User(userId);
            
            connect.con.close();
            return user;
        } 
        catch (Exception e) {
             return null;
        }
    }

//    public static void main(String[] args) throws SQLException {
//        String error = deleteUser("39");
//        System.out.println(error);
//    }
}
