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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author linhphan
 */
public class Bill {
    
    private int billId ;
    private int userId;
    private int totalMoney;
    private String receiverName;
    private String address;
    private String payOption;
    private Timestamp time;

    
    public Bill() {
        
    }
    
    // CONTRUCTOR TAO HOA DON VA DEM VAO CSDL
    public Bill(int userId, int totalMoney, String receiverName, String address, String payOption, Timestamp time) throws SQLException, ClassNotFoundException {
        
        this.userId = userId;
        this.totalMoney = totalMoney;
        this.receiverName = receiverName;
        this.address = address;
        this.payOption = payOption;
        this.time = time;
        
        DbConnect connect = new DbConnect();
        String query = "insert into Bill(UserID, TotalMoney, ReceiverName, Address, PayOption, Date) values ('"+userId+"', '"+totalMoney+"', N'"+receiverName+"' , N'"+address+"', N'"+payOption+"' , ?)"  ;
        PreparedStatement prSt = connect.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        prSt.setTimestamp(1, time);
        prSt.executeUpdate();
        ResultSet keys = prSt.getGeneratedKeys();
        keys.next();
        this.billId = keys.getInt(1);
    }

    
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayOption() {
        return payOption;
    }

    public void setPayOption(String payOption) {
        this.payOption = payOption;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Bill bill = new Bill(1, 12230000, "Lê Thị Hiên","Nghệ An","Tiền mặt", new Timestamp(new Date().getTime()));
//            Timestamp time = new Timestamp(new Date().getTime());
//            System.out.println("TimeStamp: " + time);
//    }
    
    
}
