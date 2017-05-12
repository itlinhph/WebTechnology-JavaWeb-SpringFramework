/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import otherAddOn.DbConnect;

/**
 *
 * @author linhphan
 */
public class CategoryData {
    
    // cập nhật danh mục đã có
    public static String updateCategory(String currentId, String newId, String newName) throws SQLException {
        try {
             DbConnect connect = new DbConnect();
//             String query = "UPDATE Category SET CategoryId = "+newId+", CategoryName = N'"+newName+"' WHERE CategoryId = "+c.getCategoryID();
             String query = "UPDATE Category SET CategoryId='"+newId+"', CategoryName = N'"+newName+"' WHERE (CategoryId='"+currentId+"')" ;
             PreparedStatement ps = (PreparedStatement) connect.con.prepareStatement(query);
            
             int rs = connect.st.executeUpdate(query);
             connect.con.close();
             return "Cập nhật thành công!";
        } 
        catch (Exception e) {
             return e.getMessage();
        }
    }
    
    
    // thêm danh mục mới
    public static String addCategory(String categoryId, String categoryName) throws SQLException {
        try {
             DbConnect connect = new DbConnect();

             String query = "INSERT INTO Category VALUE('"+categoryId+"', N'"+categoryName+"' )";
             
             PreparedStatement ps = connect.con.prepareStatement(query);
             int rs = connect.st.executeUpdate(query);
             connect.con.close();
             return "Thêm thành công!";
        } catch (Exception e) {
             return e.getMessage() ;
        }
    }
    
    // xóa danh mục đã có
    public static String deleteCategory(String categoryId) throws SQLException {
        try {

             // khong kiem tra khoa ngoai de ko anh huong toi san pham thuoc ve danh muc bi xoa
             DbConnect connect = new DbConnect();
             String query1 = "SET FOREIGN_KEY_CHECKS=0;" ;
             PreparedStatement ps1 = connect.con.prepareStatement(query1);
             int rs1 = connect.st.executeUpdate(query1);
             
             String query = "DELETE FROM Category WHERE categoryId = '"+categoryId+"' ";
             PreparedStatement ps = connect.con.prepareStatement(query);
             int rs = connect.st.executeUpdate(query);
             
             String query3 = "SET FOREIGN_KEY_CHECKS=1;" ;
             PreparedStatement ps3 = connect.con.prepareStatement(query3);
             int rs3 = connect.st.executeUpdate(query3);
             connect.con.close();
             connect.con.close();
             return null;
             
        } catch (Exception e) {
             return e.getMessage() ;
        }
    }
    
    // Lấy danh sách danh mục
    public static ArrayList<Category> getCategoryList() throws SQLException, ClassNotFoundException {
        
        ArrayList<Category> list = new ArrayList<Category>();
        DbConnect connect = new DbConnect();
        String query ="Select * from Category";
        
        try {
            PreparedStatement prSt = (PreparedStatement)connect.con.prepareStatement(query);
            ResultSet rs = prSt.executeQuery();
            while(rs.next()) {
                Category category = new Category();
                category.setCategoryID(rs.getNString("CategoryId"));
                category.setCategoryName(rs.getNString("CategoryName"));
                list.add(category);
            }
            connect.con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static String getCategoryNameById(String categoryId) throws SQLException, ClassNotFoundException {
        DbConnect connect = new DbConnect();
        String query ="Select * from Category where CategoryId='"+categoryId+"'";
        try {
            PreparedStatement ps = connect.con.prepareStatement(query);
             ResultSet rs = connect.st.executeQuery(query);
             rs.next();
             String categoryName = rs.getNString("CategoryName");
             connect.con.close();
             return categoryName ;
        } catch (Exception e) {
             return e.getMessage() ;
        }
    }
    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
////        Category c = new Category("dienmayxanh", "Điện máy xanh");
////        String x= updateCategory(c,"laptopnew","LapTopNew");
////        String x = addCategory(c);
////        String x = deleteCategory(c.getCategoryID());
//        String x= getCategoryNameById("laptop");
//        System.out.println(x);
//    }
}
