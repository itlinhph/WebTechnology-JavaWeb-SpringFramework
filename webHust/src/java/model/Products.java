/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author linhphan
 */
public class Products {
    private String PID;
    private String PName;
    private int inputPrice; // giá nhập vào 
    private int PPirce; // giá bán ra
    private String Img;
    private int SID;
    private String CategoryId;
    private String PDescription;
    private String supplyName;
    private String categoryName;
    private String supplyAddress;

    public Products(String PID, String PName, int inputPirce, int PPirce, String Img, int SID, String CategoryId, String PDescription) {
        this.PID = PID;
        this.PName = PName;
        this.inputPrice = inputPirce ;
        this.PPirce = PPirce;
        this.Img = Img;
        this.SID = SID;
        this.CategoryId = CategoryId;
        this.PDescription = PDescription;
    }
    public Products() {
        
    }

    public int getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(int inputPrice) {
        this.inputPrice = inputPrice;
    }

    
    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getSupplyAddress() {
        return supplyAddress;
    }

    public void setSupplyAddress(String supplyAddress) {
        this.supplyAddress = supplyAddress;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    

    public String getPID() {
        return PID;
    }

    public String getPName() {
        return PName;
    }

    public int getPPirce() {
        return PPirce;
    }

    public String getImg() {
        return Img;
    }

    public int getSID() {
        return SID;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public void setPPirce(int PPirce) {
        this.PPirce = PPirce;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public void setCategoryId(String CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getPDescription() {
        return PDescription;
    }

    public void setPDescription(String PDescription) {
        this.PDescription = PDescription;
    }
    
    

    
}
