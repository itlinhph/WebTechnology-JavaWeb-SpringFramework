/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import otherAddOn.DbConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author linhphan
 */
public class ProductData {
    
    // Lấy thông tin sản phẩm từ ID sản phẩm
    public static Products getProductByID(String productID) throws SQLException, ClassNotFoundException {
        DbConnect connect = new DbConnect();
        String query = "SELECT PID, Img, PName, Products.CategoryId, CategoryName, InputPrice, PPrice, PDescription, SName, SAddress FROM Products, Category, Suppliers WHERE Products.SID = Suppliers.SID AND Category.CategoryId = Products.CategoryId AND  PID = '" + productID + "'" ;
        Products pro = new Products();
        try {
            PreparedStatement ps = (PreparedStatement) connect.con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                pro.setPID(rs.getNString("PID"));
                pro.setImg(rs.getNString("Img"));
                pro.setPName(rs.getNString("PName"));
                pro.setCategoryId(rs.getNString("CategoryId"));
                pro.setCategoryName(rs.getNString("CategoryName"));
                pro.setInputPrice(rs.getInt("InputPrice"));
                pro.setPPirce(rs.getInt("PPrice"));
                pro.setSupplyName(rs.getNString("SName"));
                pro.setPDescription(rs.getNString("PDescription"));
                pro.setSupplyAddress(rs.getNString("SAddress"));
            }
            connect.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return pro;
    }
    
    // Trả về danh sách sản phẩm có trong csdl
    public static ArrayList<Products> getProductList() throws SQLException, ClassNotFoundException {
        
        ArrayList<Products> productList = new ArrayList<Products>();
        DbConnect connect = new DbConnect();
        String query = "SELECT PID, Img, PName, Products.CategoryId, CategoryName, InputPrice, PPrice, PDescription, SName, SAddress FROM Products, Category, Suppliers WHERE Products.SID = Suppliers.SID AND Category.CategoryId = Products.CategoryId";
        
        try {
            PreparedStatement prSt = (PreparedStatement)connect.con.prepareStatement(query);
            ResultSet rs = prSt.executeQuery();
            while(rs.next()) {
                Products pro = new Products();
                pro.setPID(rs.getNString("PID"));
                pro.setImg(rs.getNString("Img"));
                pro.setPName(rs.getNString("PName"));
                pro.setCategoryId(rs.getNString("CategoryId"));
                pro.setCategoryName(rs.getNString("CategoryName"));
                pro.setPDescription(rs.getNString("PDescription"));
                pro.setSupplyName(rs.getNString("SName"));
                pro.setSupplyAddress(rs.getNString("SAddress"));
                pro.setPPirce(rs.getInt("PPrice"));
                pro.setInputPrice(rs.getInt("InputPrice"));
                
                productList.add(pro);
            }
            connect.con.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage().toString());
        }
        return productList;
    }
    
    // trả về danh sách sản phảm được bán chạy nhất (cho trang chủ)
    public static ArrayList<Products> getMostProduct() throws SQLException, ClassNotFoundException {
         ArrayList<Products> productList = new ArrayList<Products>();
        DbConnect connect = new DbConnect();
        String query = "SELECT * FROM Products, BillDetail WHERE Products.PID  =  BillDetail.PID GROUP BY BillDetail.PID ORDER BY COUNT(BillDetail.PID) DESC" ;
        
        try {
            PreparedStatement prSt = (PreparedStatement)connect.con.prepareStatement(query);
            ResultSet rs = prSt.executeQuery();
            for(int i=1; i<=6; i++) {
                rs.next();
                Products pro = new Products();
                pro.setPID(rs.getNString("Products.PID"));
                pro.setImg(rs.getNString("Img"));
                pro.setPName(rs.getNString("PName"));
                pro.setCategoryId(rs.getNString("CategoryId"));
                pro.setCategoryName(CategoryData.getCategoryNameById(pro.getCategoryId()));
                pro.setPDescription(rs.getNString("PDescription"));
                pro.setSID(rs.getInt("SID"));
                Supplier s = SupplierData.getSupplierById(pro.getSID()) ;
                pro.setSupplyName(s.getsName());
                pro.setSupplyAddress(s.getsAddress());
                pro.setPPirce(rs.getInt("PPrice"));
                pro.setInputPrice(rs.getInt("InputPrice"));
                
                productList.add(pro);
            }
            connect.con.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage().toString());
        }
        
        return productList;
    }
    
    // trả về danh sách sản phẩm theo danh mục
    public static ArrayList<Products> getProductListByCategory(String categoryId) throws SQLException, ClassNotFoundException {
        ArrayList<Products> productList = new ArrayList<Products>();
        DbConnect connect = new DbConnect();
       String query = "SELECT PID, Img, PName, Products.CategoryId, CategoryName, PPrice, InputPrice, PDescription, SName, SAddress FROM Products, Category, Suppliers WHERE Products.SID = Suppliers.SID AND Category.CategoryId = Products.CategoryId AND Category.CategoryId = '" + categoryId + "'";
        
        try {
            PreparedStatement prSt = (PreparedStatement) connect.con.prepareStatement(query);
            ResultSet rs = prSt.executeQuery();
            
         while(rs.next()) {
            Products pro = new Products();
            pro.setPID(rs.getNString("PID"));
            pro.setImg(rs.getNString("Img"));
            pro.setPName(rs.getNString("PName"));
            pro.setCategoryId(rs.getNString("CategoryId"));
            pro.setCategoryName(rs.getNString("CategoryName"));
            pro.setPDescription(rs.getNString("PDescription"));
            pro.setSupplyName(rs.getNString("SName"));
            pro.setSupplyAddress(rs.getNString("SAddress"));
            pro.setPPirce(rs.getInt("PPrice"));
            pro.setInputPrice(rs.getInt("InputPrice"));

            productList.add(pro);
         }
         
         connect.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }
    
     // cập nhật thông tin sản phẩm
    public static String updateProduct(String pId, Products p) throws SQLException {
        
        try {
             
             DbConnect connect = new DbConnect();
             String query = "UPDATE Products SET PName= N'"+p.getPName()+"', InputPrice = '"+p.getInputPrice()+"' , PPrice = '"+p.getPPirce()+"',  Img = N'"+p.getImg()+"', SID = '"+p.getSID()+"', CategoryId = '"+p.getCategoryId()+"' , PDescription = '"+p.getPDescription()+"' WHERE (PID='"+pId+"')" ;
             PreparedStatement ps = (PreparedStatement) connect.con.prepareStatement(query);
            
             int rs = connect.st.executeUpdate(query);
             connect.con.close();
             return null;
        } 
        catch (Exception e) {
             return e.getMessage();
        }
    }
    
    
    // thêm sản phẩm mới
    public static String addProduct(Products p) throws SQLException {
        try {
             DbConnect connect = new DbConnect();

             String query = "INSERT INTO Products(PID, PName, InputPrice, PPrice, Img, SID, CategoryId, PDescription) VALUE( '"+p.getPID()+"', N'"+p.getPName()+"', '"+p.getInputPrice()+"' , '"+p.getPPirce()+"' , N'"+p.getImg()+"' , '"+p.getSID()+"' , '"+p.getCategoryId()+"' , N'"+p.getPDescription()+"' )";
             
             PreparedStatement ps = connect.con.prepareStatement(query);
             int rs = connect.st.executeUpdate(query);
             connect.con.close();
             return null;
        } catch (Exception e) {
             return e.getMessage() ;
        }
    }
    
    // xóa sản phẩm
    public static String deleteProduct(String pId) throws SQLException {
        try {

             // khong kiem tra khoa ngoai
             DbConnect connect = new DbConnect();
             String query1 = "SET FOREIGN_KEY_CHECKS=0;" ;
             PreparedStatement ps1 = connect.con.prepareStatement(query1);
             int rs1 = connect.st.executeUpdate(query1);
             
             String query = "DELETE FROM Products WHERE PID = '"+pId+"' ";
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
    
   
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<Products> arr = new ArrayList<Products>();
        arr = getMostProduct() ;
        System.out.println(arr.get(0).getPName());
//        Products pro = new Products("sas", "P2", 1, 32, "/fav", 1, "laptop", "mô tả" );
//        pro =getProductListByCategory("laptop").get(0) ;
//        pro = ProductData.getProductByID("laptop-apple");
//        arr = getProductListByCategory("laptop");
        
//        System.out.println(arr.get(0).getCategoryName());
//        String err = addProduct(pro) ;
//        String err = updateProduct("sas", pro) ;
//        String err = deleteProduct("sas") ;
//        System.out.println(err);
//        System.out.println(pro.getPDescription());
    }
//    
}
