/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author linhphan
 */
public class Supplier {
    
    private int sId ;
    private String sName;
    private String sAddress;

    
    public Supplier() {
        
    }

    public Supplier(int sId, String sName, String sAddress) {
        this.sId = sId;
        this.sName = sName;
        this.sAddress = sAddress;
    }
    
    
    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }
    
    
}
