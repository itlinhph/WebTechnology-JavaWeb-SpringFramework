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
public class BillData {
    
    // Lấy danh sách các hóa đơn
    public static ArrayList<Bill> getBillList() throws SQLException, ClassNotFoundException {
        
        ArrayList<Bill> billList = new ArrayList<Bill>();
        DbConnect connect = new DbConnect();
        String query ="Select * from Bill";
        
        try {
            PreparedStatement prSt = (PreparedStatement)connect.con.prepareStatement(query);
            ResultSet rs = prSt.executeQuery();
            while(rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("BillId"));
                bill.setUserId(rs.getInt("UserID"));
                bill.setTotalMoney(rs.getInt("TotalMoney"));
                bill.setReceiverName(rs.getNString("ReceiverName"));
                bill.setAddress(rs.getNString("Address"));
                bill.setPayOption(rs.getNString("PayOption"));
                bill.setTime(rs.getTimestamp("Date"));
                billList.add(bill);
            }
            connect.con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }
    
    // HỦy 1 hóa đơn 
    public static String deleteBill(String billId) throws SQLException {
        try {
          
             DbConnect connect = new DbConnect();
             
             String query = "DELETE FROM Bill WHERE BillId = '"+billId+"' " ;
             PreparedStatement ps = connect.con.prepareStatement(query);
             int rs = connect.st.executeUpdate(query);

             return null;
             
        } catch (Exception e) {
             return e.getMessage().toString() ;
        }
    }
}
