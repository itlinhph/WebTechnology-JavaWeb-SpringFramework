/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import otherAddOn.DbConnect;
import com.mysql.cj.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author linhphan
 */
public class Category {
    
    private String categoryID;
    private String categoryName;
 
    public Category() {
    }
 
    public Category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
 
    public String getCategoryID() {
        return categoryID;
    }
 
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
 
    public String getCategoryName() {
        return categoryName;
    }
 
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    

}
