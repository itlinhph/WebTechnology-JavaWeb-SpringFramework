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

/**
 *
 * @author linhphan
 */

public class BillDetail {

    private int billDetailId;
    private int billId;
    private String productId;
    private int Pprice;
    private int quantity;

    public BillDetail() {
        
    }

    // contructor dem bill detail vao csdl
    public BillDetail(int billId, String productId, int price, int quantity) throws SQLException, ClassNotFoundException {
        
        this.billId = billId;
        this.productId = productId;
        this.Pprice = Pprice;
        this.quantity = quantity;
        
        DbConnect connect = new DbConnect();
        String query = "insert into BillDetail(BillId, PID, Price, Quantify) values ('"+billId+"', '"+productId+"', '"+price+"' , "+quantity+")"  ;
        PreparedStatement prSt = connect.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        prSt.executeUpdate();
        ResultSet keys = prSt.getGeneratedKeys();
        keys.next();
        this.billDetailId = keys.getInt(1);
    }

   
    public int getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        this.billDetailId = billDetailId;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getPprice() {
        return Pprice;
    }

    public void setPprice(int Pprice) {
        this.Pprice = Pprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

}