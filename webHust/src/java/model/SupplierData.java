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
public class SupplierData {

    
    // cập nhật nhà cung cấp
    public static String updateSupplier(int SID, String newName, String newAddress) throws SQLException {
        
        try {
             
             DbConnect connect = new DbConnect();
             String query = "UPDATE Suppliers SET SName= N'"+newName+"', SAddress = N'"+newAddress+"' WHERE (SID='"+SID+"')" ;
             PreparedStatement ps = (PreparedStatement) connect.con.prepareStatement(query);
            
             int rs = connect.st.executeUpdate(query);
             connect.con.close();
             return null;
        } 
        catch (Exception e) {
             return e.getMessage();
        }
    }
    
    
    // thêm nhà cung cấp
    public static String addSupplier(String sName, String sAddress) throws SQLException {
        try {
             DbConnect connect = new DbConnect();

             String query = "INSERT INTO Suppliers(SName, SAddress) VALUE('"+sName+"', N'"+sAddress+"' )";
             
             PreparedStatement ps = connect.con.prepareStatement(query);
             int rs = connect.st.executeUpdate(query);
             connect.con.close();
             return null;
        } catch (Exception e) {
             return e.getMessage() ;
        }
    }
    
    // xóa nhà cung cấp
    public static String deleteSupplier(int sId) throws SQLException {
        try {

             // khong kiem tra khoa ngoai
             DbConnect connect = new DbConnect();
             String query1 = "SET FOREIGN_KEY_CHECKS=0;" ;
             PreparedStatement ps1 = connect.con.prepareStatement(query1);
             int rs1 = connect.st.executeUpdate(query1);
             
             String query = "DELETE FROM Suppliers WHERE SID = '"+sId+"' ";
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
    
    // Lấy danh sách nhà cung cấp
    public static ArrayList<Supplier> getSupplierList() throws SQLException, ClassNotFoundException {
        
        ArrayList<Supplier> list = new ArrayList<Supplier>();
        DbConnect connect = new DbConnect();
        String query ="Select * from Suppliers";
        
        try {
            PreparedStatement prSt = (PreparedStatement)connect.con.prepareStatement(query);
            ResultSet rs = prSt.executeQuery();
            while(rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setsId(Integer.parseInt(rs.getString("SID") ));
                supplier.setsName(rs.getNString("SName"));
                supplier.setsAddress(rs.getNString("SAddress"));
                
                list.add(supplier);
            }
            connect.con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static Supplier getSupplierById(int sId) throws SQLException, ClassNotFoundException {
        DbConnect connect = new DbConnect();
        String query ="Select * from Suppliers where SID ='"+sId+"'";
        try {
            PreparedStatement ps = connect.con.prepareStatement(query);
            ResultSet rs = connect.st.executeQuery(query);
            rs.next();
            
            String sName = rs.getNString("SName");
            String sAddress = rs.getNString("SAddress");
            
            connect.con.close();
            Supplier supplier = new Supplier(sId, sName, sAddress);
             return supplier ;
        } catch (Exception e) {
             return null ;
        }
    }
    
    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Supplier s = getSupplierList().get(1) ;
//        System.out.println(s.getsName());
//    }
}
